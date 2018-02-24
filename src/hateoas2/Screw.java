package hateoas2;

import java.util.Observable;
import java.util.Observer;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class Screw extends ResourceSupport {
    private static int nextID = 0;
    private static Observable2 observable = new Observable2();
    protected int id;
    protected double appliedTorqueNM;
    
    private transient Link link;

    public Screw() {
        this.id = nextID++;
        this.appliedTorqueNM = 0;

        this.link = new Link("/" + this.id, "" + this.id);
        updateLinks();
    }
    
    public void tighten() {
    	if (this.appliedTorqueNM != 100) {
        	this.appliedTorqueNM = 100;
        	updateLinks();
        	this.observable.setChanged2();
        	this.observable.notifyObservers();
    	}

    }
    
    public void loosen() {
    	if (this.appliedTorqueNM != 0) {
        	this.appliedTorqueNM = 0;
        	updateLinks();
        	this.observable.setChanged2();
        	this.observable.notifyObservers();
    	}

    }
    
    private void updateLinks() {
		this.removeLinks();
        this.add(new Link("/" + this.id));
        
    	if (this.appliedTorqueNM < 100) {
    		this.add(new Link("tighten", "tighten"));
    	} 
    	
    	if (this.appliedTorqueNM > 0) {
    		this.add(new Link("loosen", "loosen"));
    	}
    }

    public Link getLink() {
        return this.link;
    }
    
    public static void addObs(Observer obs) {
    	observable.addObserver(obs);
    }
}
