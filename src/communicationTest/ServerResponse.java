package communicationTest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class ServerResponse {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8000), 0);
        server.createContext("/", new DefaultHandler());	//http://localhost:8000/
        server.createContext("/test", new FirstHandler());	//obs allt efter /test gills, om det inte finns ett annat context för det t.ex /testfhdf gills.
        server.createContext("/test/hej", new NextHandler()); 
        server.setExecutor(null); //default executor
        server.start();
    }

    static class DefaultHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
        	String response = json("First","http://localhost:8000/test");
            
            ex.sendResponseHeaders(200, response.getBytes(Charset.forName("UTF-8")).length);
            OutputStream os = ex.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
        }
    }
    
    static class FirstHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            String response = "First";
            ex.sendResponseHeaders(200, response.getBytes(Charset.forName("UTF-8")).length);
            OutputStream os = ex.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
        }
    }
    
    static class NextHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            String response = "Next";
            ex.sendResponseHeaders(200, response.getBytes(Charset.forName("UTF-8")).length);
            OutputStream os = ex.getResponseBody();
            os.write(response.getBytes(Charset.forName("UTF-8")));
            os.close();
        }
    }

    public static String json(String type, String address) {
        JsonObject obj = new JsonObject(type, address);
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		json = json + "\n";
		return json;
    }
    
}