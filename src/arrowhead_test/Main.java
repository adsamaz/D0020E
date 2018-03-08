package arrowhead_test;

public class Main {
	public static void main(String[] args) {
		String serviceName = "provider";
		
        //String response = new ArrowheadConsumer().http("http://130.240.5.102:8045/servicediscovery/service", "Json"); //GET ALL SERVICES
    	String response = new ArrowheadConsumer().http("http://130.240.5.102:8045/servicediscovery/service/"+serviceName, "URI"); //GET SERVICE BY NAME
    	System.out.println(response);
    	
    	//String response = new ArrowheadProvider().http("http://130.240.5.102:8045/servicediscovery/publish", "publish"); //PUBLISH
    	//String response = new ArrowheadProvider().http("http://130.240.5.102:8045/servicediscovery/unpublish", "unpublish"); //UNPUBLISH
    }
}
