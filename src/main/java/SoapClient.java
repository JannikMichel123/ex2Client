
import SoapInterface.SoapService;
import SoapInterface.SoapServiceImpService;

public class SoapClient {
	public static void main(String[] args) throws Exception{
		SoapServiceImpService soapservice = new SoapServiceImpService();
		SoapService soap = soapservice.getSoapServiceImpPort();
		try {
			FlightSelectionforSoap frame = new FlightSelectionforSoap(soap);
			frame.setVisible(true);
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
