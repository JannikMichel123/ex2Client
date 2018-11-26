

	import java.awt.EventQueue;
import java.io.IOException;

	import com.sun.jersey.api.client.Client;

	public class RestClient {

		public static void main(String[] args) throws IllegalArgumentException, IOException {
			System.out.println(
					  Client.create().resource( "http://localhost:8080/flight/flightlist" ).get( String.class ) );

					try {
						FlightSelection frame = new FlightSelection();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
		}
	}

