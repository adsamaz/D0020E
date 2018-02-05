package hateoas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
 * This is the Server which will store the status of the screwdriver and its screws.
 * converts it's information into a JSON which the client then parses.
 */

public class Server {
	double screw1;
	GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    
	public Server (){
		
	}
	
	public void tighten(int screw, double force){
		
	}
	
	public void loosen(int screw, double force){
			
	}
	
	public double status(int screwId){
		return 1;
		
        gson.toJson(albums);
	}
	


}



class screw {
    public int screw;
    public double tightened; //1-100
}
