package com.automation.base;

import org.openqa.selenium.WebDriver;

import com.automation.utils.ActionUtils;
import com.automation.utils.WaitUtils;

public class BasePage {

	protected WebDriver driver;
	protected ActionUtils actionUtils;
	protected WaitUtils waitUtils;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.actionUtils = new ActionUtils(driver);
		this.waitUtils = new WaitUtils(driver);
	}
}