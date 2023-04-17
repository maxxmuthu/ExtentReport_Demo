package com.Listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

import com.BasePackage.BaseClass;
import com.ExtentManager.ExtentManager;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ListnerClass extends ExtentManager implements ITestListener {

	// Below methods will add automatically once we implement ITestListener
    // If user implement ListnerClass, then test case needs to be run through testng.xml else error will be occurred
	
	@Override
	public void onTestStart(ITestResult result) {

		// This method is called before each test method starts	
		test=extent.createTest("Beforelogin");
		System.out.println("Test Method started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		// This method is called when a test method succeeds	
	  if (result.getStatus() == ITestResult.SUCCESS) {
			   test.log(Status.PASS, "Pass Test case is: " + result.getName());
	}	
}

	@Override
	public void onTestFailure(ITestResult result) {

		// This method is called when a test case fails		
		// ITestResult - this class describe the results of the test
		// result.getName() - will get the name of test case
		
		
		if (result.getStatus() == ITestResult.FAILURE) {
			   test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			   test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			// Adding the screenshot for failed test cases	
			   String pathString = BaseClass.screenShot(BaseClass.driver, result.getName());
			   
			   try {
			    test.addScreenCaptureFromPath(pathString);
			   }  
			     catch (IOException e) {
			    e.printStackTrace();
			   }
			}		

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		// This method is called when a test method is skipped
		 if (result.getStatus() == ITestResult.SKIP) {
			   test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
	   }
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		// This method is called when a test method fails within the success percentage
		System.out.println("Test Method failed but within success percentage: " + result.getName());

	}

	@Override
	public void onStart(ITestContext context) {

		// This method is called before the test suite starts
		System.out.println("Test Suite started: " + context.getName());

	}

	@Override
	public void onFinish(ITestContext context) {

		// This method is called after the test suite finishes
		System.out.println("Test Suite finished: " + context.getName());

	}

}
