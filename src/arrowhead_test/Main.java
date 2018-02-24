package arrowhead_test;

import java.io.IOException;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Main {
	public static void main(String[] args) {
		String serviceName = "provider";
		
        //String response = new ArrowheadConsumer().http("http://130.240.5.102:8045/servicediscovery/service", "Json"); //GET ALL SERVICES
    	String response = new ArrowheadConsumer().http("http://130.240.5.102:8045/servicediscovery/service/"+serviceName, "URI"); //GET SERVICE BY NAME
    	//String response = new ArrowheadProvider().http("http://130.240.5.102:8045/servicediscovery/publish", "publish"); //PUBLISH
    	//String response = new ArrowheadProvider().http("http://130.240.5.102:8045/servicediscovery/unpublish", "unpublish"); //UNPUBLISH
    }
}
