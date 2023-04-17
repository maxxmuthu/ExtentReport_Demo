package com.BasePackage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ExtentManager.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;


	@BeforeSuite
	public void setExtent() {

		ExtentManager.setExtent();

	}

	@AfterSuite
	public void AfterSuite() {
		 ExtentManager.endReport();
	
	}

	@BeforeMethod
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://letcode.in");
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
	
		
		driver.close();
	}

	// Screenshot Method
	public static String screenShot(WebDriver driver, String filename) {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShot\\" + filename + "_" + dateName + ".png";

		File finalDestination = new File(destination);

		try {
			FileUtils.copyFile(source, finalDestination);

		} catch (Exception e) {

			e.getMessage();
		}

		return destination;
	}

	// Date Method for MyReport file name
	 public static String getCurrentTime() {  
	     String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());  
	     return currentDate;  
	 }  
}
