package arrowhead_test;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;

public class ArrowheadConsumer {
	
	
	public String http(String url, String responseType) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
        	//Get request
    		HttpGet request = new HttpGet(url);
    		request.addHeader("content-type", "application/xml");
    		
    		HttpResponse result = httpClient.execute(request);
    		String xmlResponse = EntityUtils.toString(result.getEntity(), "UTF-8");
    		
    		
    		if(responseType == "URI"){
    			JSONObject json = XML.toJSONObject(xmlResponse);
            	String host = json.getJSONObject("service").getString("host");
            	host = host.substring(0, host.length() - 1);
            	int port = json.getJSONObject("service").getInt("port");
            	//String path = json.getJSONObject("service").getJSONObject("properties").getJSONArray("property").getJSONObject(1).getString("value");
            	return host + ":" + port;
                //System.out.println(host);
                //System.out.println(port);
    		}
    		else{
    			System.out.println(xmlToJson(xmlResponse));
    		}
    		
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
	
	//for whole json stringc
	public static String xmlToJson(String xmlResponse){
		JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        return jsonPrettyPrintString;
	}
}
