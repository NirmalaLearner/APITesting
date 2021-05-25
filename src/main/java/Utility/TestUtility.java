package Utility;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class TestUtility {
	
	public static String getResponseString(Response response) {
		String strRes = response.getBody().asString();
		return strRes;
		
	}

	public static JsonPath jsonparser(String response) {
		JsonPath jsonRes = new JsonPath(response);
		return jsonRes;	
	}
	
	public static XmlPath xmlparser(String response) {
		XmlPath xmlres = new XmlPath(response);
		return xmlres;	
	}
	
	
	public static int getStatusCode(Response response) {
		int status = response.getStatusCode();
		return status;	
	}
	
	public static String getStatusMessage(Response response) {
		String message = response.getStatusLine();
		return message;
	}
	
}
