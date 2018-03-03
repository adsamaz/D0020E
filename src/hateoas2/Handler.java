package hateoas2;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

 class Handler implements HttpHandler {
	 private String json;
	 private String action;
	 
	 public Handler(String json, String action) {
		 this.json = json;
		 this.action = action;
	 }
	 public Handler(String json) {//Overloading, now it's possible to receive either 1 or 2 parameters
		 this(json, "default");
	 }
	 
    public void handle(HttpExchange ex) throws IOException {    	
    	if(action.equals("tighten")) {
    		//call tighten within the right screw
    		System.out.println("tighten");
    		
    		ex.getResponseHeaders().set("Location", "/screws/0"); //Rewrite response header
    		ex.sendResponseHeaders(302, -1);// 302 = found, it will redirect. -1, no response body length is specified it can't be written
    		
    		
    		//String get = ex.getRequestURI().getQuery();  If we want to handle get parameters. Example, /tighten?40
    	}
    	else if(action.equals("loosen")) {
    		//call loosen within the right screw
    	}
    	else if(action.equals("default")) {   	   		
    		ex.sendResponseHeaders(200, json.getBytes(Charset.forName("UTF-8")).length); //200 = status code ok, antal bytes i body
    		OutputStream os = ex.getResponseBody();
            os.write(json.getBytes(Charset.forName("UTF-8")));
            os.close();
    	}
    	else {
    		System.out.println("Something went wrong with the action in handler");
    	}
    }
}