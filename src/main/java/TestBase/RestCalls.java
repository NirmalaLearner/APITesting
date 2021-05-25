package TestBase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestCalls {
	
	public static Response GetRequest(String uRI) {
		RequestSpecification requestspecification = RestAssured.given();
		requestspecification.contentType(ContentType.JSON);
		Response response  = requestspecification.get(uRI);
		return response;	
	}
	
	public static Response GetRequestWithHeader(String uRI,String token) {
		RequestSpecification requestspecification = RestAssured.given();
		requestspecification.contentType(ContentType.JSON);
		requestspecification.header("Authorization","bearer "+token);
		Response response  = requestspecification.get(uRI);
		return response;	
	}
	
	public static Response PutRequest(String uRI) {
		RequestSpecification requestspecification = RestAssured.given();
		requestspecification.contentType(ContentType.JSON);
		Response response  = requestspecification.put(uRI);
		return response;		
	}
	
	public static Response PostRequestWOBody(String uRI) {
		RequestSpecification requestspecification = RestAssured.given();
		requestspecification.contentType(ContentType.JSON);
		Response response  = requestspecification.post(uRI);
		return response;	
	}
	
	public static Response PostRequestWBody(String uRI,String bodyContent) {
		RequestSpecification requestspecification = RestAssured.given();
		requestspecification.contentType(ContentType.JSON);
		Response response  = requestspecification.post(uRI);
		return response;	
	}
	
	public static Response PostRequestWBodyBearHeader(String uRI,String bodyContent, String token) {
		RequestSpecification requestspecification = RestAssured.given().body(bodyContent);
		requestspecification.contentType(ContentType.JSON);
		requestspecification.header("Authorization","bearer "+token);
		Response response  = requestspecification.post(uRI);
		return response;	
	}
	
	public static Response PostRequestWBaseHeader(String uRI,String token) {
		RequestSpecification requestspecification = RestAssured.given();
		requestspecification.contentType(ContentType.JSON);
		requestspecification.header("Authorization","Basic "+token);
		Response response  = requestspecification.post(uRI);
		return response;	
	}
	
	public static Response PostRequestWBearHeader(String uRI,String token) {
		RequestSpecification requestspecification = RestAssured.given();
		requestspecification.contentType(ContentType.JSON);
		requestspecification.header("Authorization","bearer "+token);
		Response response  = requestspecification.post(uRI);
		return response;	
	}
	
	public static Response DeleteRequest(String uRI) {
		RequestSpecification requestspecification = RestAssured.given();
		requestspecification.contentType(ContentType.JSON);
		Response response  = requestspecification.delete(uRI);
		return response;		
	}
}
