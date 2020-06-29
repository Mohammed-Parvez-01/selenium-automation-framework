package com.nopcommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
public WebDriver driver;
public Properties configProObj;	
public Logger logger = LogManager.getLogger(this.getClass());
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws IOException {
		// code for loading config.properties file
		
		configProObj = new Properties();
		// open file in read mode
		FileInputStream configfile = new FileInputStream(System.getProperty("user.dir")+"\\Configuration\\config.properties");
		// load the above remode mode file
		configProObj.load(configfile);
		
		// -- completed loading config,properties file
	    // open browser
		if(br.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();			
		}
		
		else if (br.equals("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	} else if(br.equals("ie")) {
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
	}
	}
	
	@AfterClass
	public void tearDown() {
		
		driver.quit();
	}
	
	// capture screenshot
	public void captureSCreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"\\Screenshots\\"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken");
	}
	
	public String randomString() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);		
	return generatedString1;
	}
	
	public String randomNumber() {
		String generatedNumber2 = RandomStringUtils.randomNumeric(4); 
		return generatedNumber2;
	}
	
	
}
