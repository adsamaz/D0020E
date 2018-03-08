package hateoas2;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;


public class Engine extends ResourceSupport {

    private static Link link = new Link("/");
    private transient Screws screws;
    
    public Engine (){   	  	
        this.add(new Link("/"));
        this.add(Screws.getLink());
        this.screws = new Screws(5, link);
    }
    
    public Link getLink() {
    	return this.link;
    }
    
    public Screws getScrews() {
    	return this.screws;
    }

}
