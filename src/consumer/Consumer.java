package consumer;

import org.json.JSONObject;

import communicationTest.ServerResponse;
import hateoas2.Server;

public class Consumer {
	
	String jsonString;
	JSONObject jsonObj;
	public Consumer(String json) {
		this.jsonString = json;
		jsonObj = new JSONObject(json);
	}
	
	public void parseRel(){
		String rel = jsonObj.getJSONArray("links").getJSONObject(0).getString("rel");
		System.out.println(rel);
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		Consumer consumer = new Consumer(server.testJson);
		consumer.parseRel();
		
	
	}
}