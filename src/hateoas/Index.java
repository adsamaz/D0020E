package hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class Index extends ResourceSupport {
	
	public Index() {
	    this.add(new Link("http://myhost/screws"));
	}

}
