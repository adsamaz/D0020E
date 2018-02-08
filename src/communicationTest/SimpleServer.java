package communicationTest;

import java.net.*;
import java.nio.charset.Charset;
import java.io.*;

public class SimpleServer  {
	public static void main(String args[]) {
		String message = "";
		String txt = "Message Recieved";
		int port = 7;
		try {
			ServerSocket ss = new ServerSocket(port);
			while(true) {
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				
				byte[] data = new byte[100];
				int count = is.read(data);
				message = new String(data, "UTF-8");
				System.out.println(message);
				
				byte[] reply = txt.getBytes(Charset.forName("UTF-8"));
				os.write(reply);
				
				os.close();
				is.close();
				s.close();
			}
			
			
			//ss.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
