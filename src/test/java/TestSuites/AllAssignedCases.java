package TestSuites;
import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;
import TestBase.RestCalls;
import Utility.TestUtility;
import Utility.URL;
import Utility.payLoadConverter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resource.ReadExcelData;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class AllAssignedCases {
	static Response response;
	static String baseToken = "dXBza2lsbHNfcmVzdF9hZG1pbl9vYXV0aF9jbGllbnQ6dXBza2lsbHNfcmVzdF9hZG1pbl9vYXV0aF9zZWNyZXQ=";
	static String accToken;
	
		@BeforeTest
		public static String generateToken() throws IOException {
			String endUrl = URL.getEndPoint("/api/rest_admin/oauth2/token/client_credentials");
			response = RestCalls.PostRequestWBaseHeader(endUrl,baseToken);  
			String resp = response.getBody().asString();
			JsonPath jsonres = new JsonPath(resp);
			accToken = jsonres.getString("data.access_token");
			TestBase.Assertions.verifyStatusCode(response, 200);
			System.out.println("Status Assertion is pass in generatetoken service");
			return accToken;
	}
	
		@Test(priority = 0)
		public void LoginAsAdmin() throws IOException {
			String endUrl = URL.getEndPoint("/api/rest_admin/login");
			String loginBody = payLoadConverter.generatePayLoadString("login.json");
			JsonPath jreq = TestUtility.jsonparser(loginBody);
			response = RestCalls.PostRequestWBodyBearHeader(endUrl,loginBody,accToken);	
			String EuserName = jreq.getString("data.username");
			String resp = TestUtility.getResponseString(response);
			JsonPath jsonres = TestUtility.jsonparser(resp);
			String Ausername = jsonres.getString("data.username");
			TestBase.Assertions.verifyStatusCode(response, 200);
			System.out.println("Status Assertion is pass in login as admin service");
			TestBase.Assertions.verifyValueInResponse(response,EuserName,Ausername);
			System.out.println("Username validated successfully in response");
	}	

		@Test(dataProvider="data",priority = 1,enabled=false)
		public void AddNewCustomer(String reqBody) throws IOException{
			String endUrl = URL.getEndPoint("/api/rest_admin/customers");
			String AddCusBody = reqBody.toString();
			//JsonPath jreq = TestUtility.jsonparser(AddCusBody);
			response = RestCalls.PostRequestWBodyBearHeader(endUrl,AddCusBody,accToken);
			//String resp = TestUtility.getResponseString(response);
			//JsonPath jsonres = TestUtility.jsonparser(resp);
			TestBase.Assertions.verifyStatusCode(response, 200);
			System.out.println("Status Assertion is pass in customer service");
			System.out.println("Added customer successfully!");	
		}
		
		@Test(priority = 2)
		public void FilterCusFromTillToDate() throws IOException{
			String endUrl = URL.getEndPoint("/api/rest_admin/customers/added_from/", "/added_to/", "2020-01-01", "2021-04-01");
			response = RestCalls.GetRequestWithHeader(endUrl, accToken);
			String resp = TestUtility.getResponseString(response);
			JsonPath jsonres = TestUtility.jsonparser(resp);
			ArrayList<Integer> customerList = new ArrayList<>();
			customerList = jsonres.get("data.customer_id");
			System.out.println(customerList);
			ArrayList<String> dateList = new ArrayList<>();
			dateList = jsonres.get("data.date_added");
			System.out.println(dateList);
			ArrayList<String> emailList = new ArrayList<>();
			emailList = jsonres.get("data.email");
			System.out.println(emailList);
			String path = "C:\\Users\\Nimmi\\OneDrive\\Documents\\FullStackTraining\\APILearning\\CASESTUDYSOAP\\RestCaseStudyDataC.xlsx";
			for(int i =0;i<customerList.size();i++) {	
						ReadExcelData.setCellData(path, "DataFromExecution", i, 0, customerList.get(i).toString());	
						ReadExcelData.setCellData(path, "DataFromExecution", i, 1, dateList.get(i).toString());					
						ReadExcelData.setCellData(path, "DataFromExecution", i, 2, emailList.get(i).toString());
					}					
		}
		
		@DataProvider(name="data")
		String [][] getPostData() throws IOException{
			String path = "C:\\Users\\Nimmi\\OneDrive\\Documents\\FullStackTraining\\APILearning\\CASESTUDYSOAP\\RestCaseStudyDataC.xlsx";
			int rows=ReadExcelData.getRowCount(path, "DataReq");
			int colos=ReadExcelData.getCellCount(path, "DataReq", 1);
			String data[][]=new String[rows][colos];
			for(int i=1;i<=rows;i++) {
					data[i-1][0]=ReadExcelData.getCellData(path, "DataReq", i, 0);
				}
			return data;
		}

}
