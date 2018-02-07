package hateoas;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;

public class GsonOther {
    public static void main(String[] args) {
        new GsonOther().http("http://130.240.5.102:8045/servicediscovery/service", " ");
    }

    public HttpResponse http(String url, String body) {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            //HttpPost request = new HttpPost(url);
            HttpGet request = new HttpGet(url);
            //StringEntity params = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            //request.setEntity(params);
            HttpResponse result = httpClient.execute(request);
            String xmlResponse = EntityUtils.toString(result.getEntity(), "UTF-8");
            JSONObject xmlJSONObj = XML.toJSONObject(xmlResponse);

            String jsonPrettyPrintString = xmlJSONObj.toString(4);

            //com.google.gson.Gson gson = new com.google.gson.Gson();
            //Response respuesta = gson.fromJson(jsonPrettyPrintString, Response.class);

            System.out.println(jsonPrettyPrintString);
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
