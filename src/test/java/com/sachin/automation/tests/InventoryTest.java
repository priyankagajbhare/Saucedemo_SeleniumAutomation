package com.sachin.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sachin.automation.base.BaseTest;
import com.sachin.automation.driver.DriverFactory;
import com.sachin.automation.pages.CartPage;
import com.sachin.automation.pages.InventoryPage;
import com.sachin.automation.pages.LoginPage;
import com.sachin.automation.utils.ConfigReader;

public class InventoryTest extends BaseTest {

	@Test(groups = { "smoke", "regression", "inventory" })
	public void shouldAddItemsToCartAndNavigateToCartPage() {

		InventoryPage inventoryPage = new LoginPage(DriverFactory.getDriver())
				.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		inventoryPage.addDefaultItemsToCart();
		CartPage cartPage = inventoryPage.clickCartIcon();

		Assert.assertTrue(cartPage.isPageLoaded(), "Cart page not loaded");
	}

	@Test(groups = { "regression", "inventory" })
	public void shouldDisplayInventoryItems() {

		InventoryPage inventoryPage = new LoginPage(DriverFactory.getDriver()).loginAsValidUser(ConfigReader.get("username"),
				ConfigReader.get("password"));

		Assert.assertTrue(inventoryPage.getItemCount() > 0, "No inventory items found");
	}
}
