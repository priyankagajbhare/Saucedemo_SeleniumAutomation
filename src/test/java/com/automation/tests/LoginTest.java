package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

public class LoginTest extends BaseTest {

	@Test
	public void loginWithValidCredentials() {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
		Assert.assertTrue(inventoryPage.isPageLoaded(), "Inventory page not loaded");
	}

	@Test
	public void loginWithInvalidPassword() {

		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

		loginPage.enterUsername(ConfigReader.get("username"));
		loginPage.enterPassword("wrong_pass");
		loginPage.clickLogin();

		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match any user in this service"), "Error message not displayed");
	}

	@Test
	public void loginWithLockedOutUser() {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername(ConfigReader.get("locked_username"));
		loginPage.enterPassword(ConfigReader.get("password"));
		loginPage.clickLogin();
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Sorry, this user has been locked out."), "Error message not displayed");
	}

	@Test
	public void loginWithBlankUsername() {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername("");
		loginPage.enterPassword(ConfigReader.get("password"));
		loginPage.clickLogin();
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), "Error message not displayed");
	}

	@Test
	public void loginWithBlankPassword() {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername(ConfigReader.get("username"));
		loginPage.enterPassword("");
		loginPage.clickLogin();
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"), "Error message not displayed");
	}

	@Test
	public void loginWithBlankUsernameAndPassword() {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername("");
		loginPage.enterPassword("");
		loginPage.clickLogin();
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"), "Error message not displayed");
	}

	@Test
	public void loginWithSpacesInCredentials() {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.enterUsername(ConfigReader.get("username")+" ");
		loginPage.enterPassword(ConfigReader.get("password")+" ");
		loginPage.clickLogin();
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match any user in this service"), "Error message not displayed");
	}

} 