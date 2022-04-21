package com.employeeapi.testCases;
//Add new record in employees
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

public class TC003_Post_Employee_Record extends TestBase
{
	RequestSpecification httpRequest;
	Response response;
	
   String empName = RestUtils.empName();
   String empSalary=RestUtils.empSal();
   String empAge=RestUtils.empAge();
	
	@BeforeClass
	void createEmployee() throws InterruptedException
	{
		logger.info("***************Started TC003_Post_Employee_Record**********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		response=httpRequest.request(Method.POST, "/create");
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		String resposneBody=response.getBody().asString();
		Assert.assertEquals(resposneBody.contains(empName), true);
		Assert.assertEquals(resposneBody.contains(empSalary), true);
		Assert.assertEquals(resposneBody.contains(empAge), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode=response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkContentType()
	{
		String contentType=response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}
}
