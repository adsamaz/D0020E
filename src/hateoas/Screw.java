package hateoas;

class Screw {
    int screwId;
    double Tightened; //1-100
    int status;
    String[] links;
     
    //Konstruktor f�r att se till s� att alla variabler �r satta korrekt
    public Screw(int Id, double newTighten) {
    	screwId = Id;
    	Tightened = newTighten; 
    	status = 0;
    	
    	//String[] links = ("screw/" + screwId + "/");
    	
    }
    
	public void changeTorque(int Id, double newTighten) {
	    	
	    	if(Id==screwId && (newTighten >= 0 && newTighten <= 100)) {
	    		Tightened = newTighten;
	    	}
	    	
	}

}