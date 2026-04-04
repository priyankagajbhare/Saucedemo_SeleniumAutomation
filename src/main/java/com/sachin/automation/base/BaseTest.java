package com.sachin.automation.base;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.sachin.automation.driver.DriverFactory;
import com.sachin.automation.utils.ConfigReader;

public class BaseTest {

	private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void setup(@Optional("chrome") String browser) {

		log.info("===== TEST SETUP START =====");
		log.info("Thread ID: {}", Thread.currentThread().getId());
		log.info("Running on browser: {}", browser);

		log.info("STEP 1: Initializing driver...");
		DriverFactory.initDriver(browser);

		WebDriver driver = DriverFactory.getDriver();

		if (driver == null) {
			throw new RuntimeException("Driver is NULL after initialization!");
		}

		log.info("STEP 2: Driver created: {}", driver);

		String url = ConfigReader.get("url");

		if (url == null || url.isEmpty()) {
			throw new RuntimeException("URL is NULL or EMPTY in ConfigReader");
		}

		log.info("STEP 3: Navigating to URL: {}", url);
		driver.get(url);

		log.info("===== TEST SETUP COMPLETE =====");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {

		log.info("Closing browser for thread: {}", Thread.currentThread().getId());

		String testName = result.getMethod().getMethodName();

		if (result.getStatus() == ITestResult.FAILURE) {
			log.error("TEST FAILED: {}", testName);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			log.info("TEST PASSED: {}", testName);
		} else {
			log.warn("TEST SKIPPED: {}", testName);
		}

		try {
			DriverFactory.quitDriver();
		} catch (Exception e) {
			log.error("Error while quitting driver", e);
		}
		log.info("===== END TEST: {} =====", testName);
	}
}