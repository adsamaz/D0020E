package consumer;

import arrowhead_test.ArrowheadConsumer;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Consumer {
	String serverAddress;
	ArrowheadConsumer arrowheadConsumer;
	String provider;
	JSONObject json;
	//  Rel     href
	Map<String, String> relHrefs;
	
	public Consumer() {
		arrowheadConsumer = new ArrowheadConsumer();
		provider = "provider"; // Only used with ArrowheadConsumer
		//serverAddress = "http://" + arrowheadConsumer.http("http://130.240.5.102:8045/servicediscovery/service/"+provider, "URI"); // RUN With Arrowhead
		serverAddress = "http://localhost:8001"; // RUN Locally
		
		System.out.println(serverAddress);
		json = getServerResponse(serverAddress);
	}

	public JSONObject getServerResponse(String url){
		// Configure request
		HttpGet request = new HttpGet(url);
		request.addHeader("content-type", "application/json");
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			// Send request
	        String responseBody = httpClient.execute(request, responseHandler);
	        // JSON from response
	        JSONObject result = new JSONObject(responseBody);
			return result;
			
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
		return null;
	}

	// Get map<rel,href> from server/provider response
	public Map<String, String> parseRel(JSONObject jsonObj){
		relHrefs = new HashMap<String, String>();

		// Loop and add all rel-href values in JSON response
		for (int i = 0; i < jsonObj.getJSONArray("links").length(); i++) {
			String rel = jsonObj.getJSONArray("links").getJSONObject(i).getString("rel");
			String href = jsonObj.getJSONArray("links").getJSONObject(i).getString("href");
			relHrefs.put(rel, href);
		}

		return relHrefs;
	}

	// Print the console menu, then get choice from input, then get JSON response from server for that choice
	private void chooseAndPerformAction() {
		printMenu();
		String input = getNextInput();	// Here the choice is made
		json = getServerResponse(serverAddress + relHrefs.get(input)); 
	}

	// Read and return console input
	private String getNextInput() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}

	private void printMenu() {
		Iterator<String> keys = json.keys();

		while( keys.hasNext() ) {
		    String key = keys.next();
		    if ( !(json.get(key) instanceof JSONArray) ) {
		    	System.out.println(key + ": " + json.get(key));
		    }
		}
		System.out.println("\nType the choice you want.");	// Print all available choices from the HATEOAS link array
		relHrefs = parseRel(json);
		// Loop through and print all rel-href values in map
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