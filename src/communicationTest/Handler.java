package communicationTest;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

 class Handler implements HttpHandler {
	 String type;
	 String href;
	 
	 public Handler(String type, String href) {
		 this.type = type;
		 this.href = href;
	 }
	 
    public void handle(HttpExchange ex) throws IOException {
    	String response = json(type, href);
        
        ex.sendResponseHeaders(200, response.getBytes(Charset.forName("UTF-8")).length);
        OutputStream os = ex.getResponseBody();
        os.write(response.getBytes(Charset.forName("UTF-8")));
        os.close();
    }
    
    private static String json(String type, String address) {
        JsonObject obj = new JsonObject(type, address);
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		json = json + "\n";
		return json;
    }
}