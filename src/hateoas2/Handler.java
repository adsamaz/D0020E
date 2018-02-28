package hateoas2;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class Handler implements HttpHandler {

	 Server server;
	 private transient GsonBuilder builder;
	 private transient Gson gson;
	 
	 
	 
	 public Handler(Server server) {
		 this.server = server;
		 
	 	this.builder = new GsonBuilder();
        this.builder.setPrettyPrinting();
        this.gson = this.builder.create();
	 }
	 
    public void handle(HttpExchange ex) throws IOException {
    	//Gson gson = new Gson();
		//String response = gson.toJson(server);
    	String response = gson.toJson(server);
        
        ex.sendResponseHeaders(200, response.getBytes(Charset.forName("UTF-8")).length);
        OutputStream os = ex.getResponseBody();
        os.write(response.getBytes(Charset.forName("UTF-8")));
        os.close();
    }

}