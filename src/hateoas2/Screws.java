package hateoas2;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class Screws extends ResourceSupport {
    private static Link link = new Link("/screws", "screws");
    private transient Link href;
    private transient Link baseLink;
    private transient Screw[] screws;
    
    private transient ScrewStatus status;

    public Screws(int numOfScrews, Link baseLink) {
    	this.baseLink = baseLink;
    	if(baseLink.getHref().equals("/")) {
    		this.href = new Link(this.link.getHref());
    	} else {
    		this.href = new Link(baseLink.getHref() + this.link.getHref());
    	}
    	this.add(this.href);
		this.add(new Link(this.baseLink.getHref(), "parent"));
        this.initScrews(numOfScrews);
        this.status = new ScrewStatus(this.screws, this.href);
        Screw.addObs(status);
    }

    private void initScrews(int numOfScrews) {
        this.screws = new Screw[numOfScrews];
        for (int i = 0; i < this.screws.length; i++) {
            this.screws[i] = new Screw(this.href);
            this.add(new Link(this.screws[i].getHref().getHref(), Integer.toString(this.screws[i].getId2())));
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
    
    public ScrewStatus getStatus() {
    	return status;
    }
}
