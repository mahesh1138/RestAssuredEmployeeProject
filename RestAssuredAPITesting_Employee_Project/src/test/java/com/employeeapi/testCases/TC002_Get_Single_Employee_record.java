package com.employeeapi.testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_Get_Single_Employee_record extends TestBase
{
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET, "/employee/"+empID);
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empID), true);   //hard codeded empID in base class
	}
	
	@Test
	void checkStatusCode()
	{
		int statuscode=response.getStatusCode();
		Assert.assertEquals(200, statuscode);
	}
	
	@Test
	void checkResponseTime()
	{	
		long responseTime=response.getTime();
		Assert.assertTrue(responseTime<6000);
	}
	
}
