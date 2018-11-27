import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import SoapInterface.SoapService;
import SoapInterface.SoapServiceImpService;

public class SoapClient {
	public static void main(String[] args) throws Exception{
		SoapServiceImpService soapservice = new SoapServiceImpService();
		SoapService soap = soapservice.getSoapServiceImpPort();
//		ArrayList<Flight> ar = new Gson().fromJson(soap.getFlightlist(),new TypeToken<ArrayList<Flight>>(){}.getType());
		try {
			FlightSelectionforSoap frame = new FlightSelectionforSoap(soap);
			frame.setVisible(true);
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
