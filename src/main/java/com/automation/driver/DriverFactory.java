package com.automation.driver;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	private static final Logger log = LoggerFactory.getLogger(DriverFactory.class);

	public static void initDriver(String browser) {

		WebDriver driverInstance;
		boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));

		try {
			switch (browser.toLowerCase()) {
			case "chrome":
				driverInstance = initChrome();
				break;

			case "firefox":
				driverInstance = initFirefox();
				break;

			case "edge":
				driverInstance = initEdge();
				break;

			default:
				throw new RuntimeException("Invalid browser: " + browser);
			}

			log.info("Launching browser: {} | Thread: {} | Session starting", browser, Thread.currentThread().getId());

			if (!isHeadless) {
				driverInstance.manage().window().maximize();
			}
			driverInstance.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			tlDriver.set(driverInstance);

		} catch (Exception e) {
			log.error("Driver initialization failed for: {}", browser, e);
			throw new RuntimeException(e);
		}
	}

	public static WebDriver getDriver() {
		WebDriver driver = tlDriver.get();
		if (driver == null) {
			throw new IllegalStateException("Driver is not initialized for this thread");
		}
		return driver;
	}

	public static void quitDriver() {
		WebDriver driver = tlDriver.get();

		if (driver != null) {
			driver.quit();
			tlDriver.remove();
			System.out.println("Driver closed for thread: " + Thread.currentThread().getId());
		} else {
			System.out.println("Driver already null for thread: " + Thread.currentThread().getId());
		}
	}

	private static WebDriver initChrome() {

		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-extensions");

		options.setExperimentalOption("prefs", Map.of("credentials_enable_service", false,
				"profile.password_manager_enabled", false, "profile.password_manager_leak_detection", false));

		if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
			options.addArguments("--headless=new");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
		}

		return new ChromeDriver(options);
	}

	private static WebDriver initFirefox() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();

		if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
			options.addArguments("-headless");
		}

		return new FirefoxDriver(options);
	}

	private static WebDriver initEdge() {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();

		if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
			options.addArguments("--headless=new");
			options.addArguments("--disable-gpu");
		}

		return new EdgeDriver(options);
	}
}