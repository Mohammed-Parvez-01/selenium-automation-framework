package com.nopcommerce.testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.Addcustomerpage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.pageObjects.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchCustomerByEmail_004 extends BaseClass
{
	
	@Test(priority=1)
	public void searchCustomerbyEmail() throws IOException, InterruptedException
	{
		logger.info("************** Starting TC_SearchCustomerByEmail_004  *************");
		
		driver.get(configProObj.getProperty("baseURL"));
		
		LoginPage lp=new LoginPage(driver);
		lp.setUsername(configProObj.getProperty("useremail"));
		lp.setPassword(configProObj.getProperty("password"));
		lp.clickLogin();
		
		//Goto Search Page
		Addcustomerpage addcust=new Addcustomerpage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
			
		//Provide EMAIL in Search Page
			

		SearchCustomerPage searchcust = new SearchCustomerPage(driver);
		
		searchcust.setEmail("victoria_victoria@nopCommerce.com");

		
		searchcust.clickSearch();
		Thread.sleep(5000);
		
		boolean status=searchcust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
		
		logger.info("************** Finished TC_SearchCustomerByEmail_004  *************");
		
	}
	
}