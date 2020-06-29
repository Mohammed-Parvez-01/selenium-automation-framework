package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_Login_Test_001 extends BaseClass {

	
	
	@Test
	public void login() throws IOException {
		logger.info("********** Starting TC_LoginTest_001**************");
		driver.get(configProObj.getProperty("baseURL"));
		LoginPage lp = new LoginPage(driver);
		
		logger.info("********** Providing user Data **************");
		lp.setUsername(configProObj.getProperty("useremail"));
		lp.setPassword(configProObj.getProperty("password"));
		lp.clickLogin();
		
		logger.info("********** Verifying Login **************");
		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		if (exp_title.equals(act_title)) {
			logger.info("********** Login Passed **************");
			Assert.assertTrue(true);
			
		}else {
			
			captureSCreen(driver, "login");// call this method on failure
			logger.warn("********** Login Failed **************");
			Assert.assertTrue(false);
		
		}
		logger.warn("********** Finieshed TC_LoginTest_001 **************");
	}
	
}
