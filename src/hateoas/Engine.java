package hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class Engine extends ResourceSupport {

    // Set this state's static href
    private static Link link = new Link("/");
    private transient Screws screws;

    public Engine() {
        // Add self rel with this state's href
        this.add(new Link("/"));
        // Add screws rel with screws' href
        this.add(Screws.getLink());
        // Initiate the screws list
        this.screws = new Screws(5, link);
    }

    public Link getLink() {
        return this.link;
    }

    public Screws getScrews() {
        return this.screws;
    }

}
