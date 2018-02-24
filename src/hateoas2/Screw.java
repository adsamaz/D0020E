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
        this.add(new Link("/" + this.id));
        this.add(new Link("tighten"));
        this.add(new Link("loosen"));
    }
    
    public void tighten() {
    	this.appliedTorqueNM = 100;
    	this.observable.setChanged2();
    	this.observable.notifyObservers();
    }
    
    public void loosen() {
    	this.appliedTorqueNM = 0;
    	this.observable.setChanged2();
    	this.observable.notifyObservers();
    }

    public Link getLink() {
        return this.link;
    }
    
    public static void addObs(Observer obs) {
    	observable.addObserver(obs);
    }
}
