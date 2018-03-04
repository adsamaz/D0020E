package hateoas2;

import java.io.IOException;
import java.net.InetSocketAddress;
import org.springframework.hateoas.Link;
import com.sun.net.httpserver.HttpServer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Server {
	
	HttpServer httpServer;
	Engine engine;

    private transient GsonBuilder builder;
    private transient Gson gson;
    private static Link link = new Link("/");
    private transient Screws screws;
    public transient String testJson;
	
	public Server() {

        this.initGSON();

        this.screws = new Screws(5, link);
        this.testJson = gson.toJson(this);
		
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
	
	private void createContext(String path, Object object, String action) {
    	httpServer.createContext(path, new Handler(object, action));
    }
    
    private void createContext(String path, Object object) { //Method overloading, now it's possible to send the optional parameter "action"
    	createContext(path, object, "default");
    }
    
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

    private void initGSON() {
        this.builder = new GsonBuilder();
        this.builder.setPrettyPrinting();
        this.gson = this.builder.create();
    }
    
    public String testGetStatusOfScrews() {
    	return gson.toJson(screws.getStatus());
    }
    
    public String testGetScrews() {
    	return gson.toJson(screws);
    }
    
    public String testTightenScrew(int i) {
    	screws.getScrew(i).tighten();
    	return gson.toJson(screws.getScrew(i));
    }
    
    public String testLoosenScrew(int i) {
    	screws.getScrew(i).loosen();
    	return gson.toJson(screws.getScrew(i));
    }
}
