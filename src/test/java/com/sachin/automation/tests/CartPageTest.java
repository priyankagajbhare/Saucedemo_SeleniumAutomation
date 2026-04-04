package com.sachin.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sachin.automation.base.BaseTest;
import com.sachin.automation.driver.DriverFactory;
import com.sachin.automation.pages.CartPage;
import com.sachin.automation.pages.CheckoutStepOnePage;
import com.sachin.automation.pages.InventoryPage;
import com.sachin.automation.pages.LoginPage;
import com.sachin.automation.utils.ConfigReader;

public class CartPageTest extends BaseTest {

	@Test(groups = { "smoke", "regression", "cart" })
	public void shouldNavigateToCheckoutWhenCartHasItems() {

		CartPage cartPage = new LoginPage(DriverFactory.getDriver())
				.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password")).addDefaultItemsToCart()
				.clickCartIcon();

		CheckoutStepOnePage checkoutStepOnePage = cartPage.proceedToCheckout();

		Assert.assertTrue(checkoutStepOnePage.isPageLoaded(), "Checkout Step One not loaded");
	}

	@Test(groups = { "regression", "cart", "negative" })
	public void shouldNotProceedToCheckoutWhenCartIsEmpty() {

		// Do not add items to the cart because we want the cart to remain empty.
		CartPage cartPage = new LoginPage(DriverFactory.getDriver())
				.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password")).clickCartIcon();

		CheckoutStepOnePage checkoutStepOnePage = cartPage.proceedToCheckout();

		Assert.assertFalse(checkoutStepOnePage.isPageLoaded(), "Checkout Step One page loaded");
	}

	@Test(groups = { "regression", "cart" })
	public void shouldShowCorrectItemCountInCart() {

		InventoryPage inventoryPage = new LoginPage(DriverFactory.getDriver()).loginAsValidUser(ConfigReader.get("username"),
				ConfigReader.get("password"));

		inventoryPage.addDefaultItemsToCart();

		CartPage cartPage = inventoryPage.clickCartIcon();

		Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart item count mismatch");
	}
}
