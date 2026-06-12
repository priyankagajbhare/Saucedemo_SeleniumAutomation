package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
// import com.automation.pages.CartPage;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

public class InventoryTest extends BaseTest {

	@Test
	public void verifyProductlistLoadsAfterLogin() {
		String[] productList = {
			"Sauce Labs Backpack",
			"Sauce Labs Bike Light",
			"Sauce Labs Bolt T-Shirt",
			"Sauce Labs Fleece Jacket",
			"Sauce Labs Onesie",
			"Test.allTheThings() T-Shirt (Red)"
		};

		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
		inventoryPage.verifyProductList(productList);
	}

	// @Test(groups = { "regression", "inventory" })
	// public void shouldDisplayInventoryItems() {

	// 	LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
	// 	loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

	// 	InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
	// 	Assert.assertTrue(inventoryPage.getItemCount() > 0, "No inventory items found");
	// }
}
