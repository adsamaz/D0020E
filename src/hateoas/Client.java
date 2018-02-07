package hateoas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * The client receives the JSON which stores the index of available options for the client.
 * The client parses the json and chooses a specific keyword that acts as a server request of
 * some sort.
 */

public class Client {
	
	

	public static void main(String[] args) {
		
		Server server = new Server();
		server.connectToProvider();
		
		
	}

}
