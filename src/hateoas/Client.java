package hateoas;

import org.springframework.hateoas.Link;

/*
 * The client receives the JSON which stores the index of available options for the client.
 * The client parses the json and chooses a specific keyword that acts as a server request of
 * some sort.
 */

public class Client {
	
	Link link = new Link("http://localhost:8080/something");

	

	public static void main(String[] args) {

		
	}

}
