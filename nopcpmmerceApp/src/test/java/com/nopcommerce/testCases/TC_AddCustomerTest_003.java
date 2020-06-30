package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.Addcustomerpage;
import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws IOException, InterruptedException {

		logger.info("********** Starting TC_LoginTest_001**************");
		driver.get(configProObj.getProperty("baseURL"));
		LoginPage lp = new LoginPage(driver);

		logger.info("********** Providing user Data **************");
		lp.setUsername(configProObj.getProperty("useremail"));
		lp.setPassword(configProObj.getProperty("password"));
		lp.clickLogin();

		Addcustomerpage addcust = new Addcustomerpage(driver);
		
		
logger.info("***************   TC_AddCustomerTest_003 statred  *********** ");
		
		
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		addcust.clickOnAddnew();
Thread.sleep(3000);
		logger.info("***************  Providing customer details  *********** ");

		
		String email=randomString()+"@gmail.com";
		logger.info("***************  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX *********** ");
		addcust.setEmail(email);
		addcust.setPassword("test123");
		addcust.setFirstName("Pavan");
		addcust.setLastName("Kumar");
		addcust.setGender("Male");
		addcust.setDob("7/05/1985"); // Format: D/MM/YYY
		addcust.setCompanyName("busyQA");
		addcust.setCustomerRoles("Vendors");
		addcust.setManagerOfVendor("Vendor 2");
		Thread.sleep(3000);
		addcust.setAdminContent("This is for testing.........");
		addcust.clickOnSave();

		// validation
		if (addcust.verifyConfirmationMsg()) {
			logger.info("***************  Customer added succesfully *********** ");
			Assert.assertTrue(true);

		} else {
			logger.error("*************** Customer Not added succesfully *********** ");
			captureSCreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}

		logger.info("***************   TC_AddCustomerTest_003 Finished  *********** ");
		
	}
		
	}


