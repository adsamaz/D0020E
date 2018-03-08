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

    // Get prettified JSON from XLM
    public static String xmlToJson(String xmlResponse) {
        JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        return jsonPrettyPrintString;
    }

    // Get host:port to service from Arrowhead Service Registry
    public String http(String url, String responseType) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            // Configure request
            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/xml");

            // Send request
            HttpResponse result = httpClient.execute(request);

            // XML from response
            String xmlResponse = EntityUtils.toString(result.getEntity(), "UTF-8");

            if (responseType.equals("URI")) {
                // JSON from XML
                JSONObject json = XML.toJSONObject(xmlResponse);
                String host = json.getJSONObject("service").getString("host");
                // Remove last char from host string
                host = host.substring(0, host.length() - 1);
                int port = json.getJSONObject("service").getInt("port");
                return host + ":" + port;
            } else {
                System.out.println(xmlToJson(xmlResponse));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
