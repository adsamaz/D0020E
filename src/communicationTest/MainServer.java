package communicationTest;

public class MainServer {
	
	public static void main(String[] args) {
		//http://localhost:8000/
		ServerResponse s = new ServerResponse("localhost", 8000);	
		//För att undvika att ha main i ServerResponse, vill inte ha static metoder/variabler.
	}	
}
