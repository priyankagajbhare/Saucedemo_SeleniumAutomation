package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

public class LoginTest extends BaseTest {

	private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

	@Test
	public void loginWithValidCredentials() {
		log.info("Starting test: loginWithValidCredentials");
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
		log.info("Validating user is redirected to inventory page");
		Assert.assertTrue(inventoryPage.isPageLoaded(), "Inventory page not loaded");
	}

	@Test
	public void loginWithInvalidPassword() {
		log.info("Starting test: loginWithInvalidPassword");

		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

		loginPage.enterUsername(ConfigReader.get("username"));
		loginPage.enterPassword("wrong_pass");
		loginPage.clickLogin();

		log.info("Validating error for invalid password");
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match any user in this service"), "Error message not displayed");
	}

	@Test
	public void loginWithLockedOutUser() {
		log.info("Starting test: loginWithLockedOutUser");
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername(ConfigReader.get("locked_username"));
		loginPage.enterPassword(ConfigReader.get("password"));
		loginPage.clickLogin();
		log.info("Validating locked out user error message");
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Sorry, this user has been locked out."), "Error message not displayed");
	}

	@Test
	public void loginWithBlankUsername() {
		log.info("Starting test: loginWithBlankUsername");
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername("");
		loginPage.enterPassword(ConfigReader.get("password"));
		loginPage.clickLogin();
		log.info("Validating blank username error message");
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), "Error message not displayed");
	}

	@Test
	public void loginWithBlankPassword() {
		log.info("Starting test: loginWithBlankPassword");
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername(ConfigReader.get("username"));
		loginPage.enterPassword("");
		loginPage.clickLogin();
		log.info("Validating blank password error message");
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"), "Error message not displayed");
	}

	@Test
	public void loginWithBlankUsernameAndPassword() {
		log.info("Starting test: loginWithBlankUsernameAndPassword");
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername("");
		loginPage.enterPassword("");
		loginPage.clickLogin();
		log.info("Validating blank username and password error message");
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), "Error message not displayed");
	}

	@Test
	public void loginWithSpacesInCredentials() {
		log.info("Starting test: loginWithSpacesInCredentials");
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername(ConfigReader.get("username")+" ");
		loginPage.enterPassword(ConfigReader.get("password")+" ");
		loginPage.clickLogin();
		log.info("Validating error for credentials containing trailing spaces");
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match any user in this service"), "Error message not displayed");
	}

} 