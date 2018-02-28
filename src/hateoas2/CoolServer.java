package hateoas2;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.hateoas.Link;

import com.sun.net.httpserver.HttpServer;

public class CoolServer {
	
	HttpServer server2;
	Server server;
	
	public CoolServer() {
		
		
		this.server = new Server();
    	try {
    		server2 = HttpServer.create(new InetSocketAddress("localhost", 8000), 0);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
       	
    	createContext(server.link); //Första addressen skapar json meddelande som hänvisar till nästa address 		
    	//createContext("/second", "StartPoint", "http://localhost:8000/"); //obs allt efter /second gills, om det inte finns ett annat context för det t.ex /secondfhdf gills.
    	server2.setExecutor(null); //default executor	
    	server2.start();
    	
	}
	
    private void createContext(Link path) {
    	System.out.println(path.getHref());
    	server2.createContext(path.getHref(), new Handler(server));
    }
    
    public static void main(String[] args) {
        CoolServer coolserver = new CoolServer();
    }
    

}
