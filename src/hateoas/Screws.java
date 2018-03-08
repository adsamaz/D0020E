package hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class Screws extends ResourceSupport {
    // Set this state's static href
    private static Link link = new Link("/screws", "screws");
    private transient Link href;
    private transient Link baseLink;
    private transient Screw[] screws;
    private transient ScrewStatus status;

    public Screws(int numOfScrews, Link baseLink) {
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
        this.initScrews(numOfScrews);
        this.status = new ScrewStatus(this.screws, this.href);
        // Add screwStatus rel with screwStatus state's href
        this.add(new Link(this.status.getHref().getHref(), "screwStatus"));
        // Add all screws as observable by status
        Screw.addObs(status);
    }

    public static Link getLink() {
        return link;
    }

    private void initScrews(int numOfScrews) {
        this.screws = new Screw[numOfScrews];
        for (int i = 0; i < this.screws.length; i++) {
            // Init screw with correct href
            this.screws[i] = new Screw(this.href);
            // Add a rel for each screw with that screw's href
            this.add(new Link(this.screws[i].getHref().getHref(), Integer.toString(this.screws[i].getId2())));
        }
    }

    public Screw getScrew(int screwId) {

        for (int i = 0; i < this.screws.length; i++) {
            if (screwId == this.screws[i].id) {
                return this.screws[screwId];
            }
        }
        return null;
    }

    public Screw[] getScrewList() {
        return this.screws;
    }

    public ScrewStatus getStatus() {
        return status;
    }
}
