package Utility;

public class URL {
	
	public static final String URL = "http://rest-api.upskills.in";
	
	public static String getEndPoint() {
		return URL;		
	}
	
	public static String getEndPoint(String resource) {
		return URL+resource;		
	}
	
	public static String getEndPoint(String resource,String parameter) {
		return URL+resource+parameter;		
	}
	
	public static String getEndPoint(String resource1,String resource2,String parameter1,String parameter2) {
		return URL+resource1+parameter1+resource2+parameter2;		
	}
}
