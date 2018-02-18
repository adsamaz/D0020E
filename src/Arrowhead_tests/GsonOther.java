package Arrowhead_tests;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;

import java.io.IOException;

public class GsonOther {
    public static void main(String[] args) {
    	
    	//Strängen som publishar simpleservicediscovery
    	String bodyPublish = "<service>"+
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
    	"</service>";
    	//Gson gson = new Gson();
    	//TestService testService = new TestService();
    	//String bodyPublish = gson.toJson(testService);
    	//System.out.println(bodyPublish);
    	
    	//Strängen som unpublishar simpleservicediscovery
    	String bodyUnPublish = 
    	 "<service>"+
    	 "<name>simpleservicediscovery._ssd-s-ws-http._tcp.srv.docker.ahf.</name>"+
    	 "</service>";
    	
    	String serviceName = "simpleservicediscovery";
    	
        new GsonOther().http("http://130.240.5.102:8045/servicediscovery/service", " ", "get"); //GET ALL SERVICES
    	// new GsonOther().http("http://130.240.5.102:8045/servicediscovery/service/"+serviceName, "", "get"); //GET SERVICE BY NAME
       // new GsonOther().http("http://130.240.5.102:8045/servicediscovery/publish", bodyPublish, "post"); //PUBLISH
       //new GsonOther().http("http://130.240.5.102:8045/servicediscovery/unpublish", bodyUnPublish, "post"); //UNPUBLISH
       
    }

    public HttpResponse http(String url, String body, String reqType) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
        	
        	//Get request
        	if(reqType == "get") {
        		HttpGet request = new HttpGet(url);
        		request.addHeader("content-type", "application/xml");
        		
        		 HttpResponse result = httpClient.execute(request);
                 
                 String xmlResponse = EntityUtils.toString(result.getEntity(), "UTF-8");
                 JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);

                 String jsonPrettyPrintString = xmlJSONObj.toString(4);
                 System.out.println(jsonPrettyPrintString);
        	}
        	//Post request
        	else {
        		HttpPost request = new HttpPost(url);
        		request.addHeader("content-type", "application/xml");
        		StringEntity params = new StringEntity(body);
                request.setEntity(params);
                
                HttpResponse result = httpClient.execute(request);
                
                String xmlResponse = EntityUtils.toString(result.getEntity(), "UTF-8");
                JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);

                String jsonPrettyPrintString = xmlJSONObj.toString(4);
                System.out.println(jsonPrettyPrintString);
        	}

            //com.google.gson.Gson gson = new com.google.gson.Gson();
            //Response respuesta = gson.fromJson(jsonPrettyPrintString, Response.class);

            
            // System.out.println(respuesta.getExample());
            //System.out.println(respuesta.getFr());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public class Response{

        private String example;
        private String fr;

        public String getExample() {
            return example;
        }
        public void setExample(String example) {
            this.example = example;
        }
        public String getFr() {
            return fr;
        }
        public void setFr(String fr) {
            this.fr = fr;
        }
    }
}
