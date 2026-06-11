package com.automation.tests;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutStepOnePage;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

public class CartPageTest extends BaseTest {

	@Test(groups = { "smoke", "regression", "cart" })
	public void shouldNavigateToCheckoutWhenCartHasItems() {

		CartPage cartPage = new LoginPage(DriverFactory.getDriver())
				.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password")).addDefaultItemsToCart()
				.clickCartIcon();

		CheckoutStepOnePage checkoutStepOnePage = cartPage.proceedToCheckout();

		Assert.assertTrue(checkoutStepOnePage.isPageLoaded(), "Checkout Step One not loaded");
	}

	@Test(groups = { "regression", "cart", "negative" }, enabled = false)
	public void shouldNotProceedToCheckoutWhenCartIsEmpty() {

		// Do not add items to the cart because we want the cart to remain empty.
		CartPage cartPage = new LoginPage(DriverFactory.getDriver())
				.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password")).clickCartIcon();

		CheckoutStepOnePage checkoutStepOnePage = cartPage.proceedToCheckout();

		Assert.assertFalse(checkoutStepOnePage.isPageLoaded(), "Checkout Step One page loaded");
	}

	@Test(groups = { "regression", "cart" })
	public void shouldShowCorrectItemCountInCart() {

		InventoryPage inventoryPage = new LoginPage(DriverFactory.getDriver())
				.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

		inventoryPage.addDefaultItemsToCart();

		CartPage cartPage = inventoryPage.clickCartIcon();

		Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart item count mismatch");
	}
}
