package com.automation.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.support.ui.Select;

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

	public String getText(By locator, String elementName) {
		try {
			WebElement element = waitUtils.waitForElementVisible(locator);
			return element.getText();
		} catch (Exception e) {
			log.error("Failed to get text from {}", elementName, e);
			throw new RuntimeException(e);
		}
	}

	public List<String> getAllTextContents(By locator, String elementName) {
		try {
			List<WebElement> elements = waitUtils.waitForAllElementsVisible(locator);
			return elements.stream().map(WebElement::getText).collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Failed to get all text contents from {}", elementName, e);
			throw new RuntimeException(e);
		}
	}

	public void selectOption(By locator, String option) {
		try {
			WebElement element = waitUtils.waitForElementVisible(locator);
			Select select = new Select(element);
			select.selectByVisibleText(option);
		} catch (Exception e) {
			log.error("Failed to select option from {}", locator, e);
			throw new RuntimeException(e);
		}
	}

	public boolean isElementVisible(By locator, String elementName) {
		try {
			return waitUtils.waitForElementVisible(locator).isDisplayed();
		} catch (Exception e) {
			log.error("Failed to check if element is visible", e);
			return false;
		}
	}
}