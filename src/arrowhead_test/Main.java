package arrowhead_test;

import java.io.IOException;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Main {
	public static void main(String[] args) {
		
		//Strängen som publishar provider
		String bodyPublish = "<service>"+
		 "<domain>docker.ahf.</domain>"+
		 "<host>130.240.113.2</host>"+
		 "<name>provider</name>"+
		 "<port>8045</port>"+
		 "<properties>"+
		    "<property>"+
		      "<name>version</name>"+
		       "<value>1.0</value>"+
		    "</property>"+
		    "<property>"+
		       "<name>path</name>"+
		       "<value>/provider</value>"+
		    "</property>"+
		 "</properties>"+
		 "<type>_pvd-s-ws-http._tcp</type>"+
		"</service>";
    	
    	//Strängen som unpublishar simpleservicediscovery
    	String bodyUnPublish = 
    	 "<service>"+
    	 "<name>provider</name>"+
    	 "</service>";
    	
    	String serviceName = "provider";
    	
        //String response = new ArrowheadConsumer().http("http://130.240.5.102:8045/servicediscovery/service", " ", "get"); //GET ALL SERVICES
    	String response = new ArrowheadConsumer().http("http://130.240.5.102:8045/servicediscovery/service/"+serviceName, "", "get"); //GET SERVICE BY NAME
    	//String response = new ArrowheadProvider().http("http://130.240.5.102:8045/servicediscovery/publish", bodyPublish, "post"); //PUBLISH
    	//String response = new ArrowheadProvider().http("http://130.240.5.102:8045/servicediscovery/unpublish", bodyUnPublish, "post"); //UNPUBLISH
       
    	JSONObject json = XML.toJSONObject(response);
    	
    	String host = json.getJSONObject("service").getString("host");
    	int port = json.getJSONObject("service").getInt("port");
    	String path = json.getJSONObject("service").getJSONObject("properties").getJSONArray("property").getJSONObject(1).getString("value");
    	String uri = host + ":" + port + path;
        System.out.println(uri);
        
    	//System.out.println(xmlToJson(response));
    }
	
	public static String xmlToJson(String xmlResponse){
		JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        return jsonPrettyPrintString;
	}
	
}
