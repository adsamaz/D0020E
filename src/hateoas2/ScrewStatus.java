package hateoas2;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class ScrewStatus extends ResourceSupport implements Observer {
	
	private static Link link = new Link("/screwstatus", "screwstatus");
	
	private transient Screw[] screwList;
	//private double[] torque;
	private Map<Integer, Double> torque2;
	
	public ScrewStatus(Screw[] screwsArray) {
		this.add(new Link("/screwstatus"));
		this.add(new Link("/screws", "parent"));
		screwList = screwsArray;
		//torque = new double[this.screwList.length];
		torque2 = new HashMap<Integer, Double>();
		updateResults();
	}
	
	public void updateResults () {
		for(int i = 0; i < this.screwList.length; i++) {
			//this.torque[i] = this.screwList[i].appliedTorqueNM;
			this.torque2.put(this.screwList[i].id, this.screwList[i].appliedTorqueNM);
		}
		
	}
	
	public static Link getLink() {
        return link;
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		updateResults();
		
	}
	

}
