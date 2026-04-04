package com.sachin.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sachin.automation.base.BaseTest;
import com.sachin.automation.driver.DriverFactory;
import com.sachin.automation.pages.InventoryPage;
import com.sachin.automation.pages.LoginPage;
import com.sachin.automation.utils.ConfigReader;

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