package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC004_PUT_Employee_Record extends TestBase
{
	RequestSpecification httpRequest;
	Response response;
	
	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	void updateEmployee() throws InterruptedException
	{
		logger.info("*****************Started TC004_PUT_Employee_Record*************");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		JSONObject requestParams=new JSONObject();
		requestParams.put("name", "empName");
		requestParams.put("salary", "empSalary");
		requestParams.put("age", "empAge");
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		response=httpRequest.request(Method.PUT, "/update/" +empID);		//PUT means, Existing data will be update
		Thread.sleep(5000);	
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody=response.getBody().asString();
		logger.info("*********ResponseBody is ==>" +responseBody);
		Assert.assertEquals(responseBody.contains("empName"), true);
		Assert.assertEquals(responseBody.contains("empSalary"), true);
		Assert.assertEquals(responseBody.contains("empAge"), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode=response.statusCode();
		logger.info("*********Status Code is ==>" +statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkServerType()
	{
		String serverType=response.header("Server");
		logger.info("*********Server Type is ==>" +serverType);
		Assert.assertEquals(serverType, "nginx");
	}
	
	@Test
	void checkContentEncoding()
	{											
		String contentEncoding=response.header("Content-Encoding");
		logger.info("*********Content Encoding is ==>" +contentEncoding);
		Assert.assertEquals("gzip", contentEncoding);
		
	}
}
