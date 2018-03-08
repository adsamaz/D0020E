package hateoas2;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ScrewStatus extends ResourceSupport implements Observer {
	
	private static Link link = new Link("/screwstatus", "screwstatus");
    private transient Link href;
    private transient Link baseLink;
	private transient Screw[] screwList;
	private Map<Integer, Double> torque;
	
	public ScrewStatus(Screw[] screwsArray, Link baseLink) {
		this.baseLink = baseLink;
    	if(baseLink.getHref().equals("/")) {
    		this.href = new Link(this.link.getHref());
    	} else {
    		this.href = new Link(baseLink.getHref() + this.link.getHref());
    	}
    	this.add(this.href);
    	
		this.add(new Link(this.baseLink.getHref(), "parent"));
		
		screwList = screwsArray;
		torque = new HashMap<Integer, Double>();
		updateResults();
	}
	
	public void updateResults () {
		for(int i = 0; i < this.screwList.length; i++) {
			this.torque.put(this.screwList[i].id, this.screwList[i].appliedTorqueNM);
		}
		
	}
	
	public static Link getLink() {
        return link;
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		updateResults();
	}
	
	public Link getHref() {
        return this.href;
    }

	

}
