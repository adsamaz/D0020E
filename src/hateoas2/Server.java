package hateoas2;

import org.springframework.hateoas.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import static java.lang.reflect.Modifier.*;

public class Server extends ResourceSupport {

    private transient GsonBuilder builder;
    private transient Gson gson;
    private static Link link = new Link("/");
    private transient Screws screws;
    public transient String testJson;

    

    public Server (){
        this.add(new Link("/"));
        this.add(Screws.getLink());

        this.initGSON();

        this.screws = new Screws(5, link);
        this.testJson = gson.toJson(this);
        //System.out.println(gson.toJson(this));
        //System.out.println();
        //System.out.println(gson.toJson(screws));
        //System.out.println();
        //System.out.println(gson.toJson(screws.getScrew(2)));
        //screws.getScrew(2).tighten();
        //System.out.println(gson.toJson(screws.getStatus()));
        //System.out.println(gson.toJson(screws.getScrew(2)));
        //screws.getScrew(2).loosen();
        //System.out.println(gson.toJson(screws.getScrew(2)));


    }

    public static void main(String[] args) {
        Server server = new Server();
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
