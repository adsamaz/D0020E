package arrowhead_test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;

public class ArrowheadProvider {
	
	//Strängen som publishar simpleservicediscovery
	String bodyPublish = "<service>"+
	 "<domain>docker.ahf</domain>"+
	 "<host>130.240.113.2</host>"+
	 "<name>Screwdriver_Provider</name>"+
	 "<port>8092</port>"+
	 "<properties>"+
	    "<property>"+
	      "<name>version</name>"+
	       "<value>1.0</value>"+
	    "</property>"+
	    "<property>"+
	       "<name>path</name>"+
	       "<value>/root</value>"+
	    "</property>"+
	 "</properties>"+
	 "<type>TestProvider</type>"+
	"</service>";
	
	public String http(String url, String reqType) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
        	//Post request
    		HttpPost request = new HttpPost(url);
    		request.addHeader("content-type", "application/xml");
    		StringEntity params = new StringEntity(bodyPublish);
            request.setEntity(params);
            
            HttpResponse result = httpClient.execute(request);
            
            String xmlResponse = EntityUtils.toString(result.getEntity(), "UTF-8");
			return xmlResponse;
        }

	    catch (IOException ex) {
	        ex.printStackTrace();
	    }
		return null;
	}
}
