package hateoas2;

import org.springframework.hateoas.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;

import java.lang.reflect.Modifier;
import java.net.InetSocketAddress;

import static java.lang.reflect.Modifier.*;

import java.io.IOException;

public class Server extends ResourceSupport {

    private transient GsonBuilder builder;
    private transient Gson gson;
    private static Link link = new Link("/");
    private transient Screws screws;
    
    HttpServer httpserver;
    

    public Server (){
    	
    	 
    	
    	try {
    		httpserver = HttpServer.create(new InetSocketAddress("localhost", 7), 0);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
	
    	

    	
        this.add(new Link("/"));
        this.add(Screws.getLink());

        this.initGSON();

        this.screws = new Screws(5, link);
        
        System.out.println(screws.getScrew(1).getLink().getHref());
        //System.out.println(gson.toJson(screws));
        //System.out.println(gson.toJson(screws.getScrew(2)));
        //screws.getScrew(2).tighten();
        //System.out.println(gson.toJson(screws.getStatus()));
        //System.out.println(gson.toJson(screws.getScrew(2)));
        //screws.getScrew(2).loosen();
        //System.out.println(gson.toJson(screws.getScrew(2)));
    	System.out.println(gson.toJson(this));

        //createPaths();

        //httpserver.setExecutor(null); //default executor	
        //httpserver.start();
    }

    public static void main(String[] args) {
        Server server = new Server();
    }

    private void initGSON() {
        this.builder = new GsonBuilder();
        this.builder.setPrettyPrinting();
        this.gson = this.builder.create();
    }
    
    private void createContext(String path, String json) {
    	httpserver.createContext(path, new Handler(json));
    }
    
    private void createPaths() {
    	System.out.println(gson.toJson(this));
    	//createContext(this.link.getHref(), gson.toJson(this));
    	//createContext("/second", "StartPoint"); 
    }
}
