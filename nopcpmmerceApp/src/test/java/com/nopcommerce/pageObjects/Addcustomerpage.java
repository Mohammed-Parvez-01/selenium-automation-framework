package com.nopcommerce.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Addcustomerpage {

	public WebDriver ldriver;
	WebElement listitem;
	
	public Addcustomerpage(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
		ldriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Implit wait
			}
	// By is an inbuilt class in selenium
	By lnkCustomers_menu=By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
	By lnkCustomers_menuitem=By.xpath("//span[@class='menu-item-title'][contains(text(),'Customers')]");
	
	By btnAddnew=By.xpath("//a[@class='btn bg-blue']"); //Add new
	

		
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	
	By txtcustomerRoles=By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
	
	By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");
	
	
	By drpmgrOfVendor=By.xpath("//*[@id='VendorId']");
	By rdMaleGender=By.id("Gender_Male");
	By rdFeMaleGender=By.id("Gender_Female");
	
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	
	By txtCompanyName=By.xpath("//input[@id='Company']");
		
	By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave=By.xpath("//button[@name='save']");
	
	By txtmsg=By.xpath("//div[@class='alert alert-success alert-dismissable']");
	
	// Action Method
	
	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu).click();
	}

	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddnew() {
		ldriver.findElement(btnAddnew).click();
	}
	
	public void setEmail(String email)
	{
		ldriver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	
	
	public void setCustomerRoles(String role) throws InterruptedException 
		{
		
			ldriver.findElement(txtcustomerRoles).click();
		
			Thread.sleep(3000);
			
			if(role.equals("Registered"))
			{
				listitem=ldriver.findElement(lstitemRegistered); 
			}
			else if(role.equals("Administrators"))
			{
				listitem=ldriver.findElement(lstitemAdministrators); 
			}
			else if(role.equals("Guests"))
			{
				// Here user can be Registered (or) Guest, only one
				ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click(); //delete registered
				listitem=ldriver.findElement(lstitemGuests);
			}
			else if(role.equals("Vendors"))
			{
				listitem=ldriver.findElement(lstitemVendors);
			}
			else
			{
				listitem=ldriver.findElement(lstitemGuests);
			}
					
			//listitem.click();
			// when .click does not work, we can use javascript executor 
			JavascriptExecutor js = (JavascriptExecutor)ldriver;
			js.executeScript("arguments[0].click();", listitem);
	}

	public void setManagerOfVendor(String value)
	{
		Select drp=new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFeMaleGender).click();
		}
		else
		{
			ldriver.findElement(rdMaleGender).click();//Default
		}
		
	}
	
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setDob(String dob)
	{
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String comname)
	{
		ldriver.findElement(txtCompanyName).sendKeys(comname);
	}
	
	public void setAdminContent(String content)
	{
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickOnSave()
	{
		ldriver.findElement(btnSave).click();
	}
	
	public boolean verifyConfirmationMsg()
	{
		String msg=ldriver.findElement(txtmsg).getText();
		if (msg.contains("The new customer has been added successfully"))
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
}
