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

public class ArrowheadConsumer {
	
	public String http(String url, String body, String reqType) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
        	//Get request
    		HttpGet request = new HttpGet(url);
    		request.addHeader("content-type", "application/xml");
    		
    		HttpResponse result = httpClient.execute(request);
    		String xmlResponse = EntityUtils.toString(result.getEntity(), "UTF-8");
 			return xmlResponse;


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
