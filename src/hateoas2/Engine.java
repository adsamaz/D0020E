package hateoas2;

import org.springframework.hateoas.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;

import java.lang.reflect.Modifier;
import java.net.InetSocketAddress;

import static java.lang.reflect.Modifier.*;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;


public class Engine extends ResourceSupport {

    private transient GsonBuilder builder;
    private transient Gson gson;
    private static Link link = new Link("/");
    private transient Screws screws;
    
	
	 

    public Engine (){
    	
    	
        this.add(new Link("/"));
        this.add(Screws.getLink());

        this.initGSON();

        this.screws = new Screws(5, link);
        
        //System.out.println(gson.toJson(this));
        //System.out.println(gson.toJson(screws));
        //System.out.println(gson.toJson(screws.getScrew(2)));
        //screws.getScrew(2).tighten();
        //System.out.println(gson.toJson(screws.getStatus()));
        //System.out.println(gson.toJson(screws.getScrew(2)));
        //screws.getScrew(2).loosen();
        //System.out.println(gson.toJson(screws.getScrew(2)));


    }



    private void initGSON() {
        this.builder = new GsonBuilder();
        this.builder.setPrettyPrinting();
        this.gson = this.builder.create();
    }
    
    public String getJson(Object object) {
		return gson.toJson(object);	
    }
    
    public Link getLink() {
    	return this.link;
    }
    
    public Screws getScrews() {
    	return this.screws;
    }

}
