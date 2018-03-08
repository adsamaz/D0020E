package hateoas2;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.Observer;

public class Screw extends ResourceSupport {
    private static int nextID = 0;
    private static Observable2 observable = new Observable2();
    protected int id;
    protected double appliedTorqueNM;
    private transient Link href;
    private transient Link baseLink;   
    private transient Link link;

    public Screw(Link baseLink) {
        this.id = nextID++;
        this.appliedTorqueNM = 0;
        this.link = new Link("/" + this.id, "" + this.id);
        this.baseLink = baseLink;    	
    	this.href = new Link(baseLink.getHref() + this.link.getHref());

        updateLinks();
    }
    
    public void tighten() {
    	if (appliedTorqueNM != 100) {
        	appliedTorqueNM = 100;
        	updateLinks();
    	}
    }
    
    public void tighten(int add) {
    	if (appliedTorqueNM != 100) {
        	appliedTorqueNM += add;
        	if(appliedTorqueNM > 100) {
        		appliedTorqueNM = 100;
        	}
        	updateLinks();
    	}
    }

    public void loosen() {
    	if (this.appliedTorqueNM != 0) {
    		appliedTorqueNM = 0;
        	updateLinks();
    	}
    }
    
    public void loosen(int sub) {
    	if (this.appliedTorqueNM != 0) {
    		appliedTorqueNM -= sub;
        	if(appliedTorqueNM < 0) {
        		appliedTorqueNM = 0;
        	}
        	updateLinks();
    	}
    }
    
    private void updateLinks() {
		this.removeLinks();
		this.add(this.href);
		this.add(new Link(this.baseLink.getHref(), "parent"));
        
    	if (this.appliedTorqueNM < 100) {
    		this.add(new Link(this.href.getHref() + "/tighten", "tighten"));
    	} 
    	
    	if (this.appliedTorqueNM > 0) {
    		this.add(new Link(this.href.getHref() + "/loosen", "loosen"));

    	}
    	this.observable.setChanged2();
    	this.observable.notifyObservers();
    }

    public Link getLink() {
        return this.link;
    }
    
    public Link getHref() {
        return this.href;
    }
    
    public int getId2() {
    	return this.id;
    }
    
    public static void addObs(Observer obs) {
    	observable.addObserver(obs);
    }
}
