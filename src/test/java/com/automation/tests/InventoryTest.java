package com.automation.tests;

import com.automation.pages.ProductDetailsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
// import com.automation.pages.CartPage;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

import java.util.Arrays;
import java.util.Collections;

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

	@Test
	public void sortProductsByPriceLowToHigh() {
		String[] productList = {
				"Sauce Labs Onesie",
				"Sauce Labs Bike Light",
				"Sauce Labs Bolt T-Shirt",
				"Test.allTheThings() T-Shirt (Red)",
				"Sauce Labs Backpack",
				"Sauce Labs Fleece Jacket"
		};
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
		inventoryPage.sortProducts("Price (low to high)");
		inventoryPage.verifyProductList(productList);
	}

	@Test
	public void sortProductsByNameZA() {
		String[] productList = {
				"Sauce Labs Backpack",
				"Sauce Labs Bike Light",
				"Sauce Labs Bolt T-Shirt",
				"Sauce Labs Fleece Jacket",
				"Sauce Labs Onesie",
				"Test.allTheThings() T-Shirt (Red)"
		};
		Collections.reverse(Arrays.asList(productList));
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
		inventoryPage.sortProducts("Name (Z to A)");
		inventoryPage.verifyProductList(productList);
	}

	@Test
	public void verifyProductDetails() {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
		inventoryPage.openProductDetails("Sauce Labs Backpack");
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(DriverFactory.getDriver());
		productDetailsPage.verifyProductDetails("Sauce Labs Backpack", "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.", "$29.99");
	}

	@Test
	public void verifySingleProductCanBeAddedToCart() {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
		inventoryPage.addToCart("Sauce Labs Backpack");
		inventoryPage.verifyCartCount("1");
	}

	@Test
	public void verifySingleProductCanBeRemovedFromCart() {
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
		inventoryPage.addToCart("Sauce Labs Backpack");
		inventoryPage.removeFromCart("Sauce Labs Backpack");
		inventoryPage.verifyNoItemsInCart();
	}

	@Test
	public void verifyMultipleProductsCanBeAddedToCart() {
		String[] productList = {
				"Sauce Labs Backpack",
				"Sauce Labs Bike Light",
				"Sauce Labs Bolt T-Shirt"
		};
		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
		for (String product : productList) {
			inventoryPage.addToCart(product);
		}
		inventoryPage.verifyCartCount("3");
	}
}
