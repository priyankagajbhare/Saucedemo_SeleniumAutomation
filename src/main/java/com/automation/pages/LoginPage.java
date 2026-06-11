package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.base.BasePage;

public class LoginPage extends BasePage {

	private By username = By.id("user-name");
	private By password = By.id("password");
	private By loginBtn = By.id("login-button");
	private By errorMessage = By.cssSelector("[data-test='error']");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public LoginPage enterUsername(String user) {
		actionUtils.type(username, user, "Username Field");
		return this;
	}

	public LoginPage enterPassword(String pass) {
		actionUtils.type(password, pass, "Password Field");
		return this;
	}

	public InventoryPage clickLogin() {
		actionUtils.click(loginBtn, "Login Button");
		return new InventoryPage(driver);
	}

	public InventoryPage loginAsValidUser(String user, String pass) {
		return enterUsername(user).enterPassword(pass).clickLogin();
	}

	public String getErrorMessage() {
		return waitUtils.waitForElementVisible(errorMessage).getText();
	}

	public boolean isErrorDisplayed() {
		return getErrorMessage().contains("Epic sadface");
	}
}