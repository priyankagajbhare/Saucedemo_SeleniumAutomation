package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutCompletePage;
import com.automation.pages.CheckoutStepOnePage;
import com.automation.pages.CheckoutStepTwoPage;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

public class CheckoutCompleteTest extends BaseTest {

	@Test(groups = { "smoke", "regression", "checkout" })
	public void shouldReturnToInventoryAfterSuccessfulCheckout() {

		LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
		InventoryPage inventoryPage = loginPage.loginAsValidUser(ConfigReader.get("username"),
				ConfigReader.get("password"));

		inventoryPage.addDefaultItemsToCart();

		CartPage cartPage = inventoryPage.clickCartIcon();

		CheckoutStepOnePage stepOne = cartPage.proceedToCheckout();

		CheckoutStepTwoPage stepTwo = stepOne.enterFirstName("Sachin").enterLastName("Rathod").enterPostalCode("222")
				.clickContinueButton();

		CheckoutCompletePage completePage = stepTwo.completeCheckout();

		InventoryPage finalPage = completePage.clickBackToProducts();

		Assert.assertTrue(finalPage.isPageLoaded(), "Inventory page not loaded");
	}
}