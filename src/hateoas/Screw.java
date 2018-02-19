package hateoas;
import org.springframework.hateoas.*;


public class Screw extends ResourceSupport {
    public int screwId;
    public double Tightened;
     
    //Konstruktor för att se till så att alla variabler är satta korrekt
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