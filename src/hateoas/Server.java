package hateoas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * This is the Server which will snewTorquee the status of the screwdriver and its screws.
 * converts it's information into a JSON which the client then parses.
 */

public class Server {
	double screw1;
	Screw screw = new Screw(1, 2.7);
	GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    
	public Server (){
		
	}
	
	public void tighten(int screw, double force){
		
	}
	
	public void loosen(int screw, double force){
			
	}
	
	public String send(int screwId){
        return gson.toJson(screw);
	}
	


}
