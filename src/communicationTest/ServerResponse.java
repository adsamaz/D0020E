package communicationTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class ServerResponse {
	 HttpServer server;
	 
	 public ServerResponse (String address, int port) {
    	try {
			server = HttpServer.create(new InetSocketAddress(address, port), 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	createContext("/", "SecondPoint", "http://localhost:8000/second"); //F�rsta addressen skapar json meddelande som h�nvisar till n�sta address 		
    	createContext("/second", "StartPoint", "http://localhost:8000/"); //obs allt efter /second gills, om det inte finns ett annat context f�r det t.ex /secondfhdf gills.
    	server.setExecutor(null); //default executor	
        server.start();
	 }
    
    private void createContext(String path, String type, String href) {
    	server.createContext(path, new Handler(type, href));
    }
    
}