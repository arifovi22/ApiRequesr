package com.Requests.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Api.Request.com.Request.RestClient;
import com.Request.Base.TestBase;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.Users;


public class PostApiTest  extends TestBase {

	String serviceurl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse response;

		
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
		public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
			restClient = new RestClient();
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
			
			//jackson API:
			ObjectMapper mapper = new ObjectMapper();
			Users users = new Users("morpheus", "leader"); //expected users obejct
			
			//object to json file:
			mapper.writeValue(new File("C:\\Users\\Arif\\eclipse project\\com.Api.Request\\Utility\\users.json"), users);
			
			//java object to json in String:
			String usersJsonString = mapper.writeValueAsString(users);
			System.out.println(usersJsonString);
			
			response = restClient.post(url, usersJsonString, headerMap); //call the API
			
			//validate response from API:
			//1. status code:
			int statusCode = response.getStatusLine().getStatusCode();
			Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201,"Not Found!!!");
			
			//2. JsonString:
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			JSONObject responseJson = new JSONObject(responseString);
			System.out.println("The response from API is:"+ responseJson);
			
			//json to java object:
			Users usersResObj = mapper.readValue(responseString, Users.class); //actual users object
			System.out.println(usersResObj);
			
			Assert.assertTrue(users.getName().equals(usersResObj.getName()));
			
			Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
			
			System.out.println(usersResObj.getId());
			System.out.println(usersResObj.getCreatedAt());
			
		}
		
		
		
		
		
		
		

	}


