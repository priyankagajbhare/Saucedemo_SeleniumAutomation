package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
import com.automation.pages.CheckoutCompletePage;
import com.automation.pages.CheckoutStepTwoPage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

public class CheckoutStepTwoTest extends BaseTest {

	@Test(groups = { "smoke", "regression", "checkout" })
	public void shouldCompleteCheckoutSuccessfully() {

		CheckoutStepTwoPage checkoutStepTwo = goToCheckoutStepTwo();

		CheckoutCompletePage completePage = checkoutStepTwo.completeCheckout();

		Assert.assertTrue(completePage.isPageLoaded(), "Checkout complete page not loaded");
	}

	@Test(groups = { "regression", "checkout" })
	public void shouldDisplayOrderSummary() {

		CheckoutStepTwoPage stepTwo = goToCheckoutStepTwo();

		Assert.assertTrue(stepTwo.isSummaryDisplayed(), "Order summary not visible");
	}

	private CheckoutStepTwoPage goToCheckoutStepTwo() {

		return new LoginPage(DriverFactory.getDriver()).loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"))
				.addDefaultItemsToCart().clickCartIcon().proceedToCheckout().enterFirstName("Sachin")
				.enterLastName("Rathod").enterPostalCode("222").clickContinueButton();
	}
}