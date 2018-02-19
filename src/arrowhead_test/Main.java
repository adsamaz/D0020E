package arrowhead_test;

import java.io.IOException;

import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;

public class Main {
	public static void main(String[] args) {
    	
    	//Strängen som publishar simpleservicediscovery
    	/*String bodyPublish = "<service>"+
    	 "<domain>docker.ahf.</domain>"+
    	 "<host>simpleservicediscovery.docker.ahf.</host>"+
    	 "<name>simpleservicediscovery</name>"+
    	 "<port>8045</port>"+
    	 "<properties>"+
    	    "<property>"+
    	      "<name>version</name>"+
    	       "<value>1.0</value>"+
    	    "</property>"+
    	    "<property>"+
    	       "<name>path</name>"+
    	       "<value>/servicediscovery</value>"+
    	    "</property>"+
    	 "</properties>"+
    	 "<type>_ssd-s-ws-http._tcp</type>"+
    	"</service>";*/
    	
    	//Strängen som unpublishar simpleservicediscovery
    	String bodyUnPublish = 
    	 "<service>"+
    	 "<name>simpleservicediscovery._ssd-s-ws-http._tcp.srv.docker.ahf.</name>"+
    	 "</service>";
    	
    	String serviceName = "simpleservicediscovery";
    	
        //String response = new ArrowheadConsumer().http("http://130.240.5.102:8045/servicediscovery/service", " ", "get"); //GET ALL SERVICES
    	//String response = new ArrowheadConsumer().http("http://130.240.5.102:8045/servicediscovery/service/"+serviceName, "", "get"); //GET SERVICE BY NAME
    	String response = new ArrowheadProvider().http("http://130.240.5.102:8045/servicediscovery/publish", "post"); //PUBLISH
    	//String response = new ArrowheadProvider().http("http://130.240.5.102:8045/servicediscovery/unpublish", bodyUnPublish, "post"); //UNPUBLISH
       
        System.out.println(xmlToJson(response));
    }
	
	public static String xmlToJson(String xmlResponse){
		JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        return jsonPrettyPrintString;
	}
	
}
