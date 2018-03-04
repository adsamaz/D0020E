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
import org.apache.http.client.methods.HttpGet;
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
		serverAddress = "localhost:8000"; //RUN Locally
		provider = "provider";
		json = getServerResponse(serverAddress);
	 }
	
	public JSONObject getServerResponse(String url){
		HttpGet request = new HttpGet(url);
		request.addHeader("content-type", "application/json");
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			HttpResponse result = httpClient.execute(request);
			return new JSONObject(result);
			
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
}