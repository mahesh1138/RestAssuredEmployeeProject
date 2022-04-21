package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;			//look and feel of report
	public ExtentReports extent;					//extent=sending common information to report
	public ExtentTest test;							//sending pass or failure message to report 
	
	public void onStart(ITestContext testContext)
	{
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/MyReport.html");  //specify the location
		
		htmlReporter.config().setDocumentTitle("Automation Report");  //Title of the report
		htmlReporter.config().setReportName("RestAssured API Testing");  //Name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		//extent.attachReporter(htmlReporter); //
		extent.setSystemInfo("Host name", "localhost");		//extent=sending common information to report
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Mahesh");		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName());	//create new entry in the report
		
		test.log(Status.PASS, "Test Case Passes IS " + result.getName());
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		
		test.log(Status.FAIL, "Test Case Failed IS " + result.getName());
		test.log(Status.FAIL, "Test Case Failed IS " + result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());	//create new entry in the report
		test.log(Status.SKIP, "Test Case Skipped IS " + result.getName());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	
}
