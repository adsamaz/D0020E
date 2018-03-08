package hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;


public class Screw extends ResourceSupport {
    public int screwId;
    public double Tightened;
     
    //Konstruktor f�r att se till s� att alla variabler �r satta korrekt
    public Screw(int Id) {


    	screwId = Id;
    	Tightened = 0.0; 
    	this.add(new Link("/" + Id));

    	
    	//String[] links = ("screw/" + screwId + "/");
    	
    }
    
	public void changeTorque(int Id, double newTighten) {
	    	/*
	    	if(Id==screwId && (newTighten >= 0 && newTighten <= 100)) {
	    		tightened = newTighten;
	    	}
	    	*/
	    	
	}

}