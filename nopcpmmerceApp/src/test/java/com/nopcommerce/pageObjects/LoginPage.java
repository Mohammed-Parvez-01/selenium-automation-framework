package com.nopcommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	  // Driver
		WebDriver ldriver;
		
		// constructor
		public LoginPage(WebDriver rdriver ){
			ldriver=rdriver;
			PageFactory.initElements(ldriver, this);
			
		}
		
		// locators
		
	@FindBy(id="Email") 
	@CacheLookup // performace of the code will be increase, where it temp store the locators and in next runs it wil take it fro here instad of looking from page
	WebElement txtEmail;
		
	@FindBy(name="Password")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(xpath= "//input[@value='Log in']")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(linkText= "Logout")
	@CacheLookup
	WebElement lnkLogout;

	// Action Methods

	public void setUsername(String uname) {
		txtEmail.clear();
		txtEmail.sendKeys(uname);
	}


	public void setPassword(String upwd) {
		txtPassword.clear();
		txtPassword.sendKeys(upwd);
	}

	public void clickLogin() {
		btnLogin.click();
	}

	public void clickLogout() {
		lnkLogout.click();
	}
	
}
