package com.employeeapi.testCases;
//RequestSpecification is an interface that allows you to specify how the request will look like. This interface has readymade methods to define base URL, base path, headers, etc. We need to use given() method of RestAssured class to get a reference for RequestSpecification.
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC001_Get_All_Employees extends TestBase
{
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("**************Started TC001_Get_All_Employee**************");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httprequest=RestAssured.given();
		response=httprequest.request(Method.GET, "/employees");
		Thread.sleep(5000);
	}
	
	
	@Test
	void checkResponseBody()
	{
		logger.info("*************Checking Response Body**************");
		
		String responseBody=response.getBody().asString();
	    logger.info("*********Response Body==>" +responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("*************Checking Status Code**************");
		int statuscode=response.getStatusCode();
		logger.info("*********Status Code==>" +statuscode);
		Assert.assertEquals(200, statuscode);
	
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("*************Checking Response Time**************");
		long responseTime=response.getTime();
		logger.info("*********Response Time is ==>" +responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
			Assert.assertTrue(responseTime<2000);		
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("*************Checking  check StatusLine**************");
		String statusLine=response.getStatusLine();
		logger.info("*********StatusLine is==>" +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 Ok");	
	}
	
	@Test
	void checkContentType()
	{
		logger.info("*************Checking  Content Type**************");
		String contentType=response.header("Content-Type");
		logger.info("*********Content Type is==>" +contentType);
		Assert.assertEquals(contentType, "application/json");
		
	}
	
	@Test
	void checkServerType()
	{
		logger.info("*************Checking ServerType**************");
		String serverType=response.header("Server");
		logger.info("*********Server Type is ==>" +serverType);
		Assert.assertEquals(serverType, "nginx");
	}
	
	@Test
	void checkContentEncoding()
	{
		logger.info("*************Checking ContentEncoding**************");
		String contentEncoding=response.header("Content-Encoding");
		logger.info("*********Content Encoding is ==>" +contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");	
	}
	
	@Test
	void checkConentLength()
	{
		logger.info("************checking contentLength**********");
		String contentLength=response.header("Content-Length");
		logger.info("Content Length is ==>"+contentLength);
		
		if(Integer.parseInt(contentLength)<100)				//ContentLength is in string, so we need to Convert it into number
			logger.warn("Content Length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	@Test
	void checkCookies()
	{
		logger.info("**********Checking Cookies****************");
		String cookies=response.getCookie("PHPSESSID");
	}
	
	void tearDown()
	{
		logger.info("************Finished TC001_Get_Empoyees**********");
	}
}
