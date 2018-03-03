package consumer;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.http.StatusLine;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;

import arrowhead_test.ArrowheadConsumer;
import communicationTest.ServerResponse;
import hateoas2.Server;

public class Consumer {
	Server testServer;
	String serverAddress;
	ArrowheadConsumer arrowheadConsumer;
	public String provider = "provider";
	
	public Consumer() {
		testServer = new Server();
		arrowheadConsumer = new ArrowheadConsumer();
		//serverAddress = arrowheadConsumer.http("http://130.240.5.102:8045/servicediscovery/service/"+provider, "URI"); //RUN With arrowhead
		serverAddress = "localhost:8000"; //RUN Locally
		JSONObject json = new JSONObject(IOUtils.toString(new URL("https://graph.facebook.com/me"), Charset.forName("UTF-8")));
	}
	
	public Map<String, String> parseRel(JSONObject jsonObj){
		Map<String, String> relHrefs = new HashMap<String, String>();
		
		for (int i = 0; i < jsonObj.getJSONArray("links").length(); i++) {
			String rel = jsonObj.getJSONArray("links").getJSONObject(i).getString("rel");
			String href = jsonObj.getJSONArray("links").getJSONObject(i).getString("href");
			relHrefs.put(rel, href);
		}

		return relHrefs;
	}

	private void chooseAndPerformAction() {
		printMenu();
		String input = getNextInput();
		
		switch (input) {
		case "1":
			tightenAllScrews();
			break;
		case "2":
			loosenAllScrews();
			break;
		case "3":
			statusOfScrews();
			break;
		case "4":
			tightenScrew();
			break;
		case "5":
			loosenScrew();
			break;

		default:
			System.out.println("Input must be 1,2,3,4,5");
			break;
		}
	}

	private String getNextInput() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}

	private void printMenu() {
		System.out.println("\nType number of the choice you want.");
		System.out.println("1: Tighten all screws.");
		System.out.println("2: Loosen all screws.");
		System.out.println("3: Status of screws.");
		System.out.println("4: Tighten screw...");
		System.out.println("5: Loosen screw...");
		System.out.print("Choice: ");
	}
	
	private void tightenAllScrews() {
		String json = testServer.testGetScrews();
		//System.out.println(json);
		JSONObject jsonObj = new JSONObject(json);

		Map<String, String> relHrefs = parseRel(jsonObj);
		//System.out.println(relHrefs);

		statusOfScrews();
		
		for (String relHref : relHrefs.keySet()) {
			if (!relHref.equals("parent") && !relHref.equals("self")) {
				try {
					json = testServer.testTightenScrew(Integer.parseInt(relHref));
					//System.out.println(json);
				} catch (Exception e) {
					System.out.println("ERROR INT PARSE: " + relHref);
					e.printStackTrace();
				}
			}
		}
		
		statusOfScrews();
	}
	
	private void loosenAllScrews() {
		String json = testServer.testGetScrews();
		//System.out.println(json);
		JSONObject jsonObj = new JSONObject(json);

		Map<String, String> relHrefs = parseRel(jsonObj);
		//System.out.println(relHrefs);

		statusOfScrews();
		
		for (String relHref : relHrefs.keySet()) {
			if (!relHref.equals("parent") && !relHref.equals("self")) {
				try {
					json = testServer.testLoosenScrew(Integer.parseInt(relHref));
					//System.out.println(json);
				} catch (Exception e) {
					System.out.println("ERROR INT PARSE: " + relHref);
					e.printStackTrace();
				}
			}
		}
		
		statusOfScrews();
	}
	
	private void statusOfScrews() {
		String json = testServer.testGetStatusOfScrews();
		//System.out.println(json);
		JSONObject jsonObj = new JSONObject(json);
		//Map<String, String> relHrefs = parseRel(jsonObj);
		
		String torque = jsonObj.getJSONObject("torque").toString();
		torque = torque.replace("{", "").replace("}", "");
		torque = torque.replace(",", "\n");
		
		System.out.println("Status:\n" + torque);
	}
	
	private void tightenScrew() {
		int screw = chooseScrew();
		
		String json = testServer.testTightenScrew(screw);
		//System.out.println(json);
		statusOfScrews();
	}
	
	private void loosenScrew() {
		int screw = chooseScrew();
		
		String json = testServer.testLoosenScrew(screw);
		//System.out.println(json);
		statusOfScrews();
	}

	private int chooseScrew() {
		String json = testServer.testGetScrews();
		//System.out.println(json);
		JSONObject jsonObj = new JSONObject(json);

		Map<String, String> relHrefs = parseRel(jsonObj);
		//System.out.println(relHrefs);
		
		int screw;
		while (true) {
			System.out.println("Type number of the screw you want.");
			for (String relHref : relHrefs.keySet()) {
				if (!relHref.equals("parent") && !relHref.equals("self"))
					System.out.println(relHref);
			}
			System.out.print("Choice: ");
			String input = getNextInput();
			
			try {
				screw = Integer.parseInt(input);
				if (relHrefs.containsKey(input))
					break;
			} catch (Exception e) {
				System.out.println("Input must be a number and one of the choices.");
			}
		}
		return screw;
	}

	public static void main(String[] args) {
		Consumer consumer = new Consumer();

		while (true) {
			consumer.chooseAndPerformAction();
		}
	}
}