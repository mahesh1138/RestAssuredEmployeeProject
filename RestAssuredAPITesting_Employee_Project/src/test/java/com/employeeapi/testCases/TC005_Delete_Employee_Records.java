package com.employeeapi.testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

//TC005_Delete_Employee_Records: Passed

public class TC005_Delete_Employee_Records extends TestBase
{
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void deleteEmployee() throws InterruptedException
	{
		logger.info("*****************Started TC005_Delete_Employee_Record*************");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httprequest=RestAssured.given();
		
		response=httprequest.request(Method.GET, "/employees");
		
		JsonPath jsonPathEvaluator =response.jsonPath();
		
		String empID=jsonPathEvaluator.get("[0].id");
		response=httprequest.request(Method.DELETE, "/delete/"+empID);
		Thread.sleep(5000);
	}
	
	@Test
	void checkresponseBody()
	{
		String responseBody=response.getBody().asString();
		logger.info("*********Response Body is ==>" +responseBody);
		Assert.assertEquals(responseBody.contains("Successfully! Record has been deleted"), true);
	}
	
	@Test
	void checkStatusCode()
	{
		int statusCode=response.getStatusCode();
		logger.info("*********Status Code is ==>" +statusCode);
		Assert.assertEquals(200, statusCode);
	}
}
