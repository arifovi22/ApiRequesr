package com.Api.Request.com.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient {

	//get method without header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);// http get request
		CloseableHttpResponse response = client.execute(get);// hit the http and get all the data
		return response;
	}

	//get method with header
	public CloseableHttpResponse get(String url, HashMap<String, String> header)throws ClientProtocolException, IOException {
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);// http get request
		for (Map.Entry<String, String> entry : header.entrySet()) {
			get.addHeader(entry.getKey(), entry.getValue());	
		}
		
		CloseableHttpResponse response = client.execute(get);// hit the http and get all the data
		return response;
	}
	
	//post method with header
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> header)throws ClientProtocolException, IOException {
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);// http get request
	post.setEntity(new StringEntity(entityString)); //for payload

		
		for (Map.Entry<String, String> entry : header.entrySet()) {
			
			post.addHeader(entry.getKey(), entry.getValue());	
		}
		
		CloseableHttpResponse response = client.execute(post);// hit the http and get all the data
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
