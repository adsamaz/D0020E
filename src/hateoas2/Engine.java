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

    private static Link link = new Link("/");
    private transient Screws screws;
    
    public Engine (){   	  	
        this.add(new Link("/"));
        this.add(Screws.getLink());
        this.screws = new Screws(5, link);
    }
    
    public Link getLink() {
    	return this.link;
    }
    
    public Screws getScrews() {
    	return this.screws;
    }

}
