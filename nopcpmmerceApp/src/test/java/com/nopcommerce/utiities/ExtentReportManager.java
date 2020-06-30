package com.nopcommerce.utiities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportManager implements ITestListener {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public String suiteName;

	public void onStart(ITestContext testContext) {
	
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// timestamp
		String repName = "Test-Report-"+timeStamp+".html";
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/Reports/"+repName);//specify location of the report
		
		htmlReporter.config().setDocumentTitle("nopCommerce Automation Report"); // TiTle of report
		htmlReporter.config().setReportName("nopCommerce Functional Testing"); // name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","QA");
		extent.setSystemInfo("os","Windows10");
		extent.setSystemInfo("Browser name","Chrome,Firefox");
	}

	
	public void onTestStart(ITestResult result) {
		
		
	}

	
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getName()); // create new entry in th report
		test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
	}

	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName()); // create new entry in th report
		test.log(Status.FAIL, "Test Case Failed IS " + result.getName());
		
		test.log(Status.FAIL, "Test Case Failed IS " + result.getThrowable()); // add error or exception
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
	try {
		test.addScreenCaptureFromPath(screenshotPath);// adding screenshot
		
	}catch(IOException e) {
		e.printStackTrace();
	}
	
	}

	
	public void onTestSkipped(ITestResult result) {
		
		test=extent.createTest(result.getName()); // create new entry in th report
		test.log(Status.SKIP, "Test Case Skipped IS " + result.getName());
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

		
	public void onFinish(ITestContext context) {
		
		extent.flush();
	}
	
}
