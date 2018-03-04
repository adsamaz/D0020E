package consumer;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;

import arrowhead_test.ArrowheadConsumer;
import hateoas2.Server;

public class Consumer {
	String serverAddress;
	ArrowheadConsumer arrowheadConsumer;
	public String provider;
	JSONObject json;
	Map<String, String> relHrefs;
	
	public Consumer() {
		arrowheadConsumer = new ArrowheadConsumer();
		//serverAddress = arrowheadConsumer.http("http://130.240.5.102:8045/servicediscovery/service/"+provider, "URI"); //RUN With arrowhead
		serverAddress = "http://localhost:8000"; //RUN Locally
		provider = "provider";
		json = getServerResponse(serverAddress);
	 }
	
	public JSONObject getServerResponse(String url){
		HttpGet request = new HttpGet(url);
		request.addHeader("content-type", "application/json");
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
	        String responseBody = httpClient.execute(request, responseHandler);
	        JSONObject result = new JSONObject(responseBody);
			return result;
			
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
		return null;
	}
	public Map<String, String> parseRel(JSONObject jsonObj){
		relHrefs = new HashMap<String, String>();
		
		
		for (int i = 0; i < jsonObj.getJSONArray("links").length(); i++) {
			String rel = jsonObj.getJSONArray("links").getJSONObject(i).getString("rel");
			String href = jsonObj.getJSONArray("links").getJSONObject(i).getString("href");
			relHrefs.put(rel, href);
		}

		return relHrefs;
	}
	
	private void chooseAndPerformAction() {
		printMenu();
		String input = getNextInput();	//Here the choice is made
		json = getServerResponse(serverAddress + relHrefs.get(input)); 
	}

	private String getNextInput() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}

	private void printMenu() {
		Iterator<String> keys = json.keys();

		while( keys.hasNext() ) {	//first, print all json keys/values
		    String key = keys.next();
		    if ( json.get(key) instanceof JSONObject ) {
		         System.out.println(key + ": " + json.getJSONObject(key));
		    }
		}
		System.out.println("\nType the choice you want.");	//Print all available choices from the HATEOAS link array
		relHrefs = parseRel(json);
		for (String relHref : relHrefs.keySet()) {
			System.out.println("Rel: " + relHref + ". Link: " + relHrefs.get(relHref));
		}
	}
	public static void main(String[] args) {
		Consumer consumer = new Consumer();

		while (true) {
			consumer.chooseAndPerformAction();
		}
	}
}