package communicationTest;

import java.net.*;
import java.io.*;

class JsonObject{
	private String type;
	private String rel;
	
	public JsonObject(String t, String r){
		this.type = t;
		this.rel = r;
	}
}