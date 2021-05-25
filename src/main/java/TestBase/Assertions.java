package TestBase;

import org.testng.Assert;

import Utility.TestUtility;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Assertions {
	
	public static void verifyStatusCode(Response response,int Status) {
		Assert.assertEquals(TestUtility.getStatusCode(response), Status);
	}

	public static void verifyValueInResponse(Response response,String eValue, String tagPath) {
		String resp = TestUtility.getResponseString(response);
		JsonPath jres = TestUtility.jsonparser(resp);
		String tagValue = jres.getString(tagPath);
		Assert.assertEquals(tagValue, eValue);
	}
}
