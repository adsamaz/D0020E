package hateoas2;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

 class Handler implements HttpHandler {
	 String json;
	 
	 public Handler(String json) {
		 this.json = json;
	 }
	 
    public void handle(HttpExchange ex) throws IOException {
    	System.out.println("BALLS");
        ex.sendResponseHeaders(200, json.getBytes(Charset.forName("UTF-8")).length);
        OutputStream os = ex.getResponseBody();
        os.write(json.getBytes(Charset.forName("UTF-8")));
        os.close();
    }
    
}