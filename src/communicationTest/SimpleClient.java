package communicationTest;

import java.net.*;
import java.nio.charset.Charset;

import com.google.gson.Gson;

import java.io.*;

public class SimpleClient {
	public static void main(String args[]){
		
		String txt = "Test Msg";
		String Recieved = "";
		int count = 0;
		
		testObject obj = new testObject("Request", "Startaddress");
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		byte[] message = json.getBytes(Charset.forName("UTF-8"));
		
		try{
			Socket s = new Socket("localhost", 7);
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			os.write(message);
			
			byte[] data = new byte[100];
			while(count == 0) {
				count = is.read(data);
			}
			Recieved = new String(data, "UTF-8");
			System.out.println(Recieved);
						
			os.close();
			is.close();
			s.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
}