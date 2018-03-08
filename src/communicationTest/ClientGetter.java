package communicationTest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ClientGetter {
	public static void main(String args[]){
		connect("http://localhost:8000/");
	}
	
	public static void connect(String address) {
		HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(address);
		
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            
            System.out.println(content);
            //parse();
        } catch (IOException e) {
            e.printStackTrace();
        }		
	}
	
	private void parse(String s) {
		
	}
}