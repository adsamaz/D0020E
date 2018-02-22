package hateoas2;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class Screw extends ResourceSupport {
    private static int nextID = 0;
    protected int id;
    protected double appliedTorqueNM;
    
    private transient ScrewStatus status;

    private transient Link link;

    public Screw(ScrewStatus status) {
        this.id = nextID++;
        this.appliedTorqueNM = 0;
        this.status = status;

        this.link = new Link("/" + this.id, "" + this.id);
        this.add(new Link("/" + this.id));
        this.add(new Link("tighten"));
        this.add(new Link("loosen"));
    }
    
    public void tighten() {
    	this.appliedTorqueNM = 100;
    	this.status.updateResults();
    }
    
    public void loosen() {
    	this.appliedTorqueNM = 0;
    	this.status.updateResults();
    }

    public Link getLink() {
        return this.link;
    }
}
