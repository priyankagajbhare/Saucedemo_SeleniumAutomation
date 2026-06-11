package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

public class LoginTest extends BaseTest {

	@Test(groups = { "smoke", "regression", "login" })
	public void shouldLoginSuccessfullyWithValidCredentials() {
		InventoryPage inventoryPage = new LoginPage(DriverFactory.getDriver())
				.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));
		Assert.assertTrue(inventoryPage.isPageLoaded(), "Inventory page not loaded");
	}

	@Test(groups = { "regression", "login", "negative" })
	public void shouldShowErrorForInvalidCredentials() {

		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());

		loginPage.enterUsername("invalid_user").enterPassword("wrong_pass").clickLogin();

		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
	}
} 