package hateoas2;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.hateoas.Link;

import com.sun.net.httpserver.HttpServer;

public class CoolServer {
	
	HttpServer httpServer;
	Server server;
	
	public CoolServer() {
		
		
		this.server = new Server();
    	try {
    		httpServer = HttpServer.create(new InetSocketAddress("localhost", 8000), 0);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
       	
    	createContext(server.link); //Första addressen skapar json meddelande som hänvisar till nästa address 		
    	//createContext("/second", "StartPoint", "http://localhost:8000/"); //obs allt efter /second gills, om det inte finns ett annat context för det t.ex /secondfhdf gills.
    	httpServer.setExecutor(null); //default executor	
    	httpServer.start();
    	
	}
	
    private void createContext(Link path) {
    	httpServer.createContext(path.getHref(), new Handler(server));
    }
    
    public static void main(String[] args) {
        CoolServer coolserver = new CoolServer();
    }
    

}
