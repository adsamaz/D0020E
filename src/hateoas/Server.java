package hateoas;
import java.util.ArrayList;

import org.springframework.hateoas.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * This is the Server which will snewTorquee the status of the screwdriver and its screws.
 * converts it's information into a JSON which the client then parses.
 */

public class Server {
	
	/*
	double screw1;
	Screw screw = new Screw(1, 2.7);
	GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    boolean Connected = false;
    */
	
	ArrayList<Screw> screws;
	

    
	public Server (int numberOfScrews){
		
		
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
	    Gson gson = builder.create();
	    
	    Index index = new Index();
		System.out.println(gson.toJson(index));

		screws = new ArrayList<Screw>();
		
		for (int i = 1; i <= numberOfScrews; i++) {
			screws.add(new Screw(i));
		}
		
		for (int i = 0; i < screws.size(); i++) {
			System.out.println(gson.toJson(screws.get(i)));
		}




	}
	
	
	public static void main(String[] args) {

		Server server = new Server(5);
	}

	/*
	public String connectToProvider() {
		//Connected = true;
		//return gson.toJson(screw);
		
	}
	*/
	
	public void tighten(int screw, double force){
		
	}
	
	public void loosen(int screw, double force){
			
	}
	
	/*
	public String send(int screwId){
        //return gson.toJson(screw);
	}
	*/
	


}
