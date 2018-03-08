package hateoas2;

import java.util.Observable;

// Was used to bypass limit on 'extends' for Screw class when it "had" to be observable.
// Was made to allow "external" use of the setChanged method.
public class Observable2 extends Observable {

    // Makes setChanged publicly accessible
    public void setChanged2() {
        this.setChanged();
    }

}


