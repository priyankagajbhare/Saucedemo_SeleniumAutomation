package com.sachin.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionUtils {

	private static final Logger log = LoggerFactory.getLogger(ActionUtils.class);

	private WaitUtils waitUtils;

	public ActionUtils(WebDriver driver) {
		this.waitUtils = new WaitUtils(driver);
	}

	public void type(By locator, String text, String fieldName) {
		try {
			WebElement element = waitUtils.waitForElementVisible(locator);
			element.clear();
			element.sendKeys(text);

			log.info("Typing '{}' into [{}]", text, fieldName);

		} catch (Exception e) {
			log.error("Failed to type into {}", fieldName, e);
			throw new RuntimeException(e);
		}
	}

	public void click(By locator, String elementName) {
		try {
			WebElement element = waitUtils.waitForElementClickable(locator);
			element.click();

			log.info("Clicked on {}", elementName);

		} catch (Exception e) {
			log.error("Failed to click on {}", elementName, e);
			throw new RuntimeException(e);
		}
	}
}