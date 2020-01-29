package com.Requests.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Api.Request.com.Request.RestClient;
import com.Request.Base.TestBase;

import util.TestUtil;



public class GetRequestTest extends TestBase {

	String serviceurl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse response;

	// TestBase base;
	@BeforeMethod
	public void baseConnection() throws IOException {
		// base = new TestBase();
		serviceurl = properties.getProperty("url");
		apiUrl = properties.getProperty("ApiUrl");
		url = serviceurl + apiUrl;
		restClient = new RestClient();
		restClient.get(url);
	}

	@Test
	public void test() throws ClientProtocolException, IOException {
		
			response =restClient.get(url);
			
			//a. Status Code:
			int statusCode = response.getStatusLine().getStatusCode();
			
			System.out.println("Status Code: " + statusCode);
			Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
			
			//b. Json String:
			String entity = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject object = new JSONObject(entity);
			System.out.println("Json entity: "+object);
			
			
			//single value assertion:
			//per_page:
			String perPageValue = TestUtil.getValueByJPath(object, "/per_page");
			System.out.println("value of per page is-->"+ perPageValue);
			Assert.assertEquals(Integer.parseInt(perPageValue), 6);
			
			//total:
			String totalValue = TestUtil.getValueByJPath(object, "/total");
			System.out.println("value of total is-->"+ totalValue);		
			Assert.assertEquals(Integer.parseInt(totalValue), 12);
			
			//get the value from JSON ARRAY:
			String lastName = TestUtil.getValueByJPath(object, "/data[0]/last_name");
			String id = TestUtil.getValueByJPath(object, "/data[0]/id");
			String avatar = TestUtil.getValueByJPath(object, "/data[0]/avatar");
			String firstName = TestUtil.getValueByJPath(object, "/data[0]/first_name");

			System.out.println("Last name: " + lastName);
			System.out.println("Id is: " + id);
			System.out.println("Avatar: " + avatar);
			System.out.println("First name: " + firstName);
			
			

			//c.get headers
			Header[] headers = response.getAllHeaders();
			HashMap<String, String> allHeaders = new HashMap<String, String>();
			
			for (Header header:headers) {
				allHeaders.put(header.getName(), header.getValue());
				
			}
			System.out.println("Headers Array: "+allHeaders);			
	}

	@Test
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
//		headerMap.put("username", "test@amazon.com");
//		headerMap.put("password", "test213");
//		headerMap.put("Auth Token", "12345");

		
		response = restClient.get(url,headerMap);
		
		//a. Status Code:
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("Status Code--->"+ statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		//b. Json String:
		String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		JSONObject object = new JSONObject(responseString);
		System.out.println("Response JSON from API---> "+ object);
		
		//single value assertion:
		//per_page:
		String perPageValue = TestUtil.getValueByJPath(object, "/per_page");
		System.out.println("value of per page is-->"+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//total:
		String totalValue = TestUtil.getValueByJPath(object, "/total");
		System.out.println("value of total is-->"+ totalValue);		
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		//get the value from JSON ARRAY:
		String lastName = TestUtil.getValueByJPath(object, "/data[2]/last_name");
		String id = TestUtil.getValueByJPath(object, "/data[2]/id");
		String avatar = TestUtil.getValueByJPath(object, "/data[2]/avatar");
		String firstName = TestUtil.getValueByJPath(object, "/data[2]/first_name");

		System.out.println("Last name: " + lastName);
		System.out.println("Id is: " + id);
		System.out.println("Avatar: " + avatar);
		System.out.println("First name: " + firstName);
		
		
		//c. All Headers
		Header[] headersArray =  response.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();	
		for(Header header : headersArray){
			allHeaders.put(header.getName(), header.getValue());
		}	
		System.out.println("Headers Array-->"+allHeaders);
		
		
		
	}
	
	
	
	
	
	
	
	
}
