package arrowhead;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ArrowheadProvider {

    //Str�ngen som publishar provider
    String bodyPublish = "<service>" +
            "<domain>docker.ahf.</domain>" +
            "<host>130.240.5.102</host>" +
            "<name>provider</name>" +
            "<port>8001</port>" +
            "<properties>" +
            "<property>" +
            "<name>version</name>" +
            "<value>1.0</value>" +
            "</property>" +
            "<property>" +
            "<name>path</name>" +
            "<value>/</value>" +
            "</property>" +
            "</properties>" +
            "<type>_pvd-s-ws-http._tcp</type>" +
            "</service>";

    //Str�ngen som unpublishar simpleservicediscovery
    String bodyUnPublish =
            "<service>" +
                    "<name>provider</name>" +
                    "</service>";

    // Publish or unpublish the Arrowhead provider to the Arrowhead Service Registry
    public String http(String url, String action) {
        String body;
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            if (action.equals("publish"))
                body = bodyPublish;
            else if (action.equals("unpublish"))
                body = bodyUnPublish;
            else
                body = "";

            // Configure request
            HttpPost request = new HttpPost(url);
            request.addHeader("content-type", "application/xml");
            StringEntity params = new StringEntity(body);
            request.setEntity(params);

            // Send request
            HttpResponse result = httpClient.execute(request);

            // XML from response
            String xmlResponse = EntityUtils.toString(result.getEntity(), "UTF-8");
            return xmlResponse;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
