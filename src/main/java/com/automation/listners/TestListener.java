package com.automation.listners;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.automation.driver.DriverFactory;
import com.automation.reporting.ExtentManager;
import com.automation.reporting.ExtentTestManager;
import com.automation.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

	private static final Logger log = LoggerFactory.getLogger(TestListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getMethod().getMethodName();

		ExtentTest test = ExtentManager.getInstance().createTest(testName);
		ExtentTestManager.setTest(test);

		log.info("STARTED: {}", testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().pass("Test passed");
		ExtentManager.getInstance().flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String testName = result.getMethod().getMethodName();

		log.error("TEST FAILED: {}", testName);

		ExtentTestManager.getTest().fail(result.getThrowable());

		WebDriver driver = null;

		try {
			driver = DriverFactory.getDriver();
		} catch (Exception e) {
			log.error("Driver not available for screenshot");
		}

		if (driver != null) {
			try {
				String path = ScreenshotUtils.capture(driver, testName);
				ExtentTestManager.getTest().addScreenCaptureFromPath(path);
			} catch (Exception e) {
				log.error("Screenshot failed");
			}
		}
		ExtentManager.getInstance().flush();
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.getInstance().flush();
	}
}