package com.Request.Base;

import java.io.FileInputStream;

import java.util.Properties;


public class TestBase {
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_201 = 201;

	
	
 public Properties properties;

	
	public TestBase() {
		properties = new Properties();
		FileInputStream fi;
		try {
			fi = new FileInputStream("C:\\Users\\Arif\\eclipse project\\com.Api.Request\\src\\test\\resources\\com\\Api\\Request\\resources\\Config.properties");
			properties.load(fi);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
