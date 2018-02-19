package hateoas;
import org.springframework.hateoas.*;

public class Index extends ResourceSupport {
	
	public Index() {
	    this.add(new Link("http://myhost/screws"));
	}

}
