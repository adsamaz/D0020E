package hateoas2;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class Screw extends ResourceSupport {
    private static int nextID = 0;
    protected int id;
    protected double appliedTorqueNM;

    private transient Link link;

    public Screw() {
        this.id = nextID++;
        this.appliedTorqueNM = 0;

        this.link = new Link("/" + this.id, "" + this.id);
        this.add(new Link("/" + this.id));
    }

    public Link getLink() {
        return this.link;
    }
}
