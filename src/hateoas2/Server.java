package hateoas2;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
	
	HttpServer httpServer;
	Engine engine;

	// Currently only hosts local server. Make some changes to use ArrowheadProvider if server should register itself with the Arrowhead Service Registry
	public Server() {
		this.engine = new Engine();
    	try {
    		httpServer = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
       	createPath();
    	httpServer.setExecutor(null); //default executor	
    	httpServer.start();
    	
	}
	
	private void createContext(String path, Object object, String action) {
    	httpServer.createContext(path, new Handler(object, action));
    }
    
    private void createContext(String path, Object object) { //Method overloading, now it's possible to send the optional parameter "action"
    	createContext(path, object, "default");
    }

    // Create all paths to all engine states
    private void createPath() {
        createContext(engine.getLink().getHref(), engine);
        createContext(engine.getScrews().getLink().getHref(), engine.getScrews());
        createContext(engine.getScrews().getStatus().getHref().getHref(), engine.getScrews().getStatus());

        for (Screw i : engine.getScrews().getScrewList()) {
            createContext(i.getHref().getHref(), i);
            createContext(i.getHref().getHref() + "/tighten", i, "tighten");
            createContext(i.getHref().getHref() + "/loosen", i, "loosen"); //B�r �ndra texten till variabler
        }
    }
    
    public static void main(String[] args) {
    	Server coolserver = new Server();
    }
}
