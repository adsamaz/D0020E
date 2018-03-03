package hateoas2;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.springframework.hateoas.Link;

import com.sun.net.httpserver.HttpServer;

public class Server {
	
	HttpServer httpServer;
	Engine engine;
	
	public Server() {
		
		
		this.engine = new Engine();
    	try {
    		httpServer = HttpServer.create(new InetSocketAddress("localhost", 8000), 0);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
       	createPath();
    	httpServer.setExecutor(null); //default executor	
    	httpServer.start();
    	
	}
	
    private void createContext(String path, String json, String action) {
    	httpServer.createContext(path, new Handler(json, action));
    }
    
    private void createContext(String path, String json) { //Metod overloading, now it's possible to send the optional parameter "action"
    	createContext(path, json, "default");
    }
    
    private void createPath() {
    	createContext(engine.getLink().getHref(), engine.getJson(engine));	
    	createContext(engine.getScrews().getLink().getHref(), engine.getJson(engine.getScrews()));
    	createContext(engine.getScrews().getStatus().getHref().getHref(), engine.getJson(engine.getScrews().getStatus()));
    	System.out.println(engine.getScrews().getStatus().getLink().getHref());
    	int index = 0;
    	for (Screw i : engine.getScrews().getScrewList()){
        	createContext(i.getHref().getHref(), engine.getJson(i));
        	
        	//example with new parameter
        	createContext(i.getHref().getHref() + "/tighten", "You are now tightening the screw: " + index++, "tighten");
    	}


    }
    
    public static void main(String[] args) {
    	Server coolserver = new Server();
    }
    

}
