package com.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.BasePackage.BaseClass;
import com.ExtentManager.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LetsCode extends BaseClass {
	
/*	 
We have to run through testng.xml (or) here from this class to generate report
If user implement ListnerClass, then test case needs to be run through testng.xml else error will be occurred
*/	
	 @Test(priority=0)
		public void Beforelogin() throws IllegalArgumentException, InterruptedException { 
		 	
		WebElement image = driver.findElement(By.xpath("//img[@alt='letcode']"));
	    
		ExtentManager.test.createNode("Validation1");   // createNode will help us to understand which validation is passed in Extent Report. This will be useful when test case have multiple validation.
		Assert.assertTrue(image.isDisplayed());
		       
	    Thread.sleep(5000);
	    	  

	}
	
	@Test(priority=1)
	public void login() throws IllegalArgumentException, InterruptedException { 
	    
       driver.findElement(By.linkText("Log in")).click(); 
           
       driver.findElement(By.name("email")).sendKeys("koushik350@gmail.com");

       driver.findElement(By.name("password")).sendKeys("Pass123$");  

       driver.findElement(By.xpath("//button[.='LOGIN']")).click();  
       
       String title = driver.getTitle(); 
       System.out.println("Title:" + " "  + title);     
       
       ExtentManager.test.createNode("Validation1");
       Assert.assertEquals(title, "LetCode with Koushik");
       
       // Below create node are dummy ones just to check 'list of Validations' in ExtentManager
       ExtentManager.test.createNode("Validation2");
       Assert.assertTrue(true);
       ExtentManager.test.createNode("Validation3");
       Assert.assertTrue(true);
       
       Thread.sleep(5000);
    	  
}

}
