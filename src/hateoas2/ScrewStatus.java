package hateoas2;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class ScrewStatus extends ResourceSupport implements Observer {
    // Set this state's static href
    private static Link link = new Link("/screwstatus", "screwstatus");
    private transient Link href;
    private transient Link baseLink;
    private transient Screw[] screwList;
    //          screwID  torque
    private Map<Integer, Double> torque;

    public ScrewStatus(Screw[] screwsArray, Link baseLink) {
        // Link from parent state
        this.baseLink = baseLink;
        // Used to prevent this.href from having a double slash at start e.g. '//screws' instead of '/screws'
        if (baseLink.getHref().equals("/")) {
            this.href = new Link(this.link.getHref());
        } else {
            this.href = new Link(baseLink.getHref() + this.link.getHref());
        }
        // Add self rel with this state's href
        this.add(this.href);
        // Add parent rel with parent state's href
        this.add(new Link(this.baseLink.getHref(), "parent"));

        screwList = screwsArray;
        torque = new HashMap<Integer, Double>();
        // Update torque map first time
        updateResults();
    }

    public static Link getLink() {
        return link;
    }

    // Update torque map with current values from each screw
    public void updateResults() {
        for (int i = 0; i < this.screwList.length; i++) {
            this.torque.put(this.screwList[i].id, this.screwList[i].appliedTorqueNM);
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // Should be run each time a screw changes the torque of itself
        updateResults();
    }

    public Link getHref() {
        return this.href;
    }
}
