package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utiities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{

	
	
	@Test(dataProvider= "LoginData")
	public void loginTest(String user, String pwd, String exp) throws IOException, InterruptedException {
		logger.info("********** Starting TC_LoginDDT_002**************");
		driver.get(configProObj.getProperty("baseURL"));
		LoginPage lp = new LoginPage(driver);
		
		logger.info("********** Providing user Data **************");
		lp.setUsername(user);
		lp.setPassword(pwd);
		lp.clickLogin();
		Thread.sleep(5000);
		String exp_title = "Dashboard / nopCommerce administration";
		String act_title = driver.getTitle();
		
		if (exp_title.equals(act_title)) {
			
			if(exp.equals("pass")) {
				logger.info("********LoginTest is Passed*********");
				lp.clickLogout();
				Assert.assertTrue(true);
			}else if (exp.equals("fail")) {
				logger.warn("********** LoginTest is Failed*********");
				lp.clickLogout();
				Assert.assertTrue(false);
			}
		}else if (!exp_title.equals(act_title)) {
			if (exp.equals("pass")) {
				
				logger.warn("***********LoginTest is Failed***********");
			    Assert.assertTrue(false);
			
			}else if (exp.equals("fail")) {
				logger.info("*****************LoginTest is Passed");
				Assert.assertTrue(true);
			}
			
		}
		logger.info("************* Finished TC_LoginDDt_002**********");
	}
	
	
	
	@DataProvider(name="LoginData")
	public String [][]getData() throws IOException{
		String path = System.getProperty("user.dir")+"/TestData/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "sheet1");
		int colcount = XLUtils.getCellCount(path, "sheet1",1);
		String logindata[][] = new String[rownum][colcount];
		
		for (int i = 1;i<=rownum;i++) {
			
			for (int j = 0; j<colcount; j++) {
				
				logindata [i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);//1,0
			}
			
			
		}
		
		
		return logindata;
	}
	
	
}
