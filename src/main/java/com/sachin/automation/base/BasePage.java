package com.sachin.automation.base;

import org.openqa.selenium.WebDriver;

import com.sachin.automation.utils.ActionUtils;
import com.sachin.automation.utils.WaitUtils;

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