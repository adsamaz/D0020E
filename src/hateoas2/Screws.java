package hateoas2;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class Screws extends ResourceSupport {
    private static Link link = new Link("/screws", "screws");

    private transient Screw[] screws;

    public Screws(int numOfScrews) {
    	this.add(new Link("/screws"));
        this.initScrews(numOfScrews);
    }

    private void initScrews(int numOfScrews) {
        this.screws = new Screw[numOfScrews];
        for (int i = 0; i < this.screws.length; i++) {
            this.screws[i] = new Screw();
            this.add(this.screws[i].getLink());
        }
    }

    public static Link getLink() {
        return link;
    }
    
    public Screw getScrew (int screwId) {
    	
    	for (int i = 0; i < this.screws.length; i++) {
    		if (screwId == this.screws[i].id) {
    			return this.screws[screwId];

    		}
    	}
    	
    	return null;
    	
    }
}