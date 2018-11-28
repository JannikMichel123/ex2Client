import java.util.ArrayList;

public class Flight {
	String flightNumber;
	String type;
	String destination;
	String departureDate;
	ArrayList<Seat> UFSeats = new ArrayList<Seat>();
	ArrayList<Seat> EPSeats = new ArrayList<Seat>();
	ArrayList<Seat> ESeats = new ArrayList<Seat>();
	String basePrice = "";

	public Flight(String flightNumber, String type, String destination, String departureDate) {
		super();
		this.flightNumber = flightNumber;
		this.type = type;
		this.destination = destination;
		this.departureDate = departureDate;

		if (type.equals("Airbus 319")) {
			char c[] = {'A','B','E','F'};
			for (int j = 0; j < c.length; j++) {
				for (int i = 0; i < 2; i++) {
					UFSeats.add(new Seat(c[j]+"", "" + i + 1));
				}
			}
			char ep[]  = {'A','B','C','D','E','F'};
			for (int j = 0; j < ep.length; j++) {
				for (int i = 0; i < 7; i++) {
					EPSeats.add(new Seat(ep[j]+"", "" + i + 1));
				}
			}
			char e[]  = {'A','B','C','D','E','F'};
			for (int j = 0; j < e.length; j++) {
				for (int i = 0; i < 13; i++) {
					ESeats.add(new Seat(e[j]+"", "" + i + 1));
				}
			}
		}
		if (type.equals("Boing 737-900")) {
			char c[] = {'A','B','E','F'};
			for (int j = 0; j < c.length; j++) {
				for (int i = 0; i < 5; i++) {
					UFSeats.add(new Seat(c[j]+"", "" + i + 1));
				}
			}
			char ep[]  = {'A','B','C','D','E','F'};
			for (int j = 0; j < ep.length; j++) {
				for (int i = 0; i < 7; i++) {
					EPSeats.add(new Seat(ep[j]+"", "" + i + 1));
				}
			}
			char e[]  = {'A','B','C','D','E','F'};
			for (int j = 0; j < e.length; j++) {
				for (int i = 0; i < 19; i++) {
					ESeats.add(new Seat(e[j]+"", "" + i + 1));
				}
			}
			ESeats.add(new Seat("D", "" + 19));
			ESeats.add(new Seat("E", "" + 19));
			ESeats.add(new Seat("F", "" + 19));
		}
	}
	public String[] toArray() {
		String[] s = {this.departureDate,this.flightNumber,this.destination,this.type,this.basePrice};
		return s;
	}

}
