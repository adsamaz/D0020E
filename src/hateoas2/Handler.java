package hateoas2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

 class Handler implements HttpHandler {
	 private String json;
	 private String action;
	 private Object object;
	 
	 private GsonBuilder builder;
	 private Gson gson;
	 
	 public Handler(Object object, String action) {
		 this.object = object;
		 this.action = action;
		 
		 initGSON();
	 }
	 public Handler(Object object) {//Overloading, now it's possible to receive either 1 or 2 parameters
		 this(object, "default");
	 }
	 
    public void handle(HttpExchange ex) throws IOException {   	
    	if(action.equals("tighten")) {  		
    		String get = ex.getRequestURI().getQuery(); // If there is a parameter. Example, /tighten?40
    		System.out.println("Get: " + get);
    		
    		if(get == null) {	//Need this in case of null, it doesn't reach else
    			((Screw) object).tighten();
    		}    		
    		else if(get.matches("\\d+")) {
    			((Screw) object).tighten(Integer.parseInt(get));
    		}
    		else {
    			((Screw) object).tighten();
    		}
    		
    		ex.getResponseHeaders().set("Location", ((Screw) object).getHref().getHref()); //Rewrite response header
    		ex.sendResponseHeaders(302, -1);// 302 = found, it will redirect. -1, no response body length is specified it can't be written
    	}
    	else if(action.equals("loosen")) {
    		String get = ex.getRequestURI().getQuery();
    		System.out.println("Get: " + get);
    		
    		if(get == null) {	//Need this in case of null, it doesn't reach else
    			((Screw) object).loosen();
    		}    		
    		else if(get.matches("\\d+")) {
    			((Screw) object).loosen(Integer.parseInt(get));
    		}
    		else {
    			((Screw) object).loosen();
    		}
    		
    		ex.getResponseHeaders().set("Location", ((Screw) object).getHref().getHref());
    		ex.sendResponseHeaders(302, -1);
    	}
    	else if(action.equals("default")) {
    		
    		json = getJson(object);
    		ex.sendResponseHeaders(200, json.getBytes(Charset.forName("UTF-8")).length); //200 = status code ok, antal bytes i body
    		OutputStream os = ex.getResponseBody();
            os.write(json.getBytes(Charset.forName("UTF-8")));
            os.close();
    	}
    	else {
    		System.out.println("Something went wrong with the action in handler");
    	}
    }
    
    public String getJson(Object object) {
		return gson.toJson(object);	
    }
    
    private void initGSON() {
        this.builder = new GsonBuilder();
        this.builder.setPrettyPrinting();
        this.gson = this.builder.create();
    }
}