package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.base.BasePage;

public class LoginPage extends BasePage {

	private By username = By.id("user-name");
	private By password = By.id("password");
	private By loginBtn = By.id("login-button");
	private By errorMessage = By.cssSelector("[data-test='error']");
	private By menuButton = By.id("react-burger-menu-btn");
	private By logoutButton = By.id("logout_sidebar_link");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void enterUsername(String user) {
		actionUtils.type(username, user, "Username Field");
	}

	public void enterPassword(String pass) {
		actionUtils.type(password, pass, "Password Field");
	}

	public void clickLogin() {
		actionUtils.click(loginBtn, "Login Button");
	}

	public void loginAsValidUser(String user, String pass) {
		enterUsername(user);
		enterPassword(pass);
		clickLogin();
	}

	public String getErrorMessage() {
		return waitUtils.waitForElementVisible(errorMessage).getText();
	}

	public boolean isErrorDisplayed() {
		return getErrorMessage().contains("Epic sadface");
	}

	public void clickMenuButton() {
		actionUtils.click(menuButton, "Menu Button");
	}

	public void clickLogoutButton() {
		actionUtils.click(logoutButton, "Logout Button");
	}

	public void logoutFromApplication() {
		clickMenuButton();
		clickLogoutButton();
	}

	public boolean isPageLoaded() {
		return waitUtils.waitForElementVisible(loginBtn).isDisplayed();
	}
}