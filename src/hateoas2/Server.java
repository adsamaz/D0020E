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
    	//createContext("/second", "StartPoint", "http://localhost:8000/"); //obs allt efter /second gills, om det inte finns ett annat context för det t.ex /secondfhdf gills.
    	httpServer.setExecutor(null); //default executor	
    	httpServer.start();
    	
	}
	
    private void createContext(String path, String json) {
    	httpServer.createContext(path, new Handler(json));
    }
    
    private void createPath() {
    	createContext(engine.getLink().getHref(), engine.getJson(engine));	
    	createContext(engine.getScrews().getLink().getHref(), engine.getJson(engine.getScrews()));
    	createContext(engine.getScrews().getStatus().getLink().getHref(), engine.getJson(engine.getScrews().getStatus()));
    	
    	for (Screw i : engine.getScrews().getScrewList()){
    		
        	System.out.println(i.getLink().getHref());
        	createContext(i.getLink().getHref(), engine.getJson(i));
    	}


    }
    
    public static void main(String[] args) {
    	Server coolserver = new Server();
    }
    

}
