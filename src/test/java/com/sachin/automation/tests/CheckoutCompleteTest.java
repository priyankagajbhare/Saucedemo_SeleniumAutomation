package com.sachin.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sachin.automation.base.BaseTest;
import com.sachin.automation.driver.DriverFactory;
import com.sachin.automation.pages.CartPage;
import com.sachin.automation.pages.CheckoutCompletePage;
import com.sachin.automation.pages.CheckoutStepOnePage;
import com.sachin.automation.pages.CheckoutStepTwoPage;
import com.sachin.automation.pages.InventoryPage;
import com.sachin.automation.pages.LoginPage;
import com.sachin.automation.utils.ConfigReader;

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