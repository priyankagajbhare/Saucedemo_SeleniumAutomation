package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
import com.automation.pages.CheckoutStepOnePage;
import com.automation.pages.CheckoutStepTwoPage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

public class CheckoutStepOneTest extends BaseTest {

	@Test(groups = { "smoke", "regression", "checkout" })
	public void shouldProceedToCheckoutStepTwoWithValidDetails() {

		CheckoutStepOnePage checkoutStepOne = goToCheckoutStepOne();

		CheckoutStepTwoPage checkoutStepTwo = checkoutStepOne.enterFirstName("Sachin").enterLastName("Rathod")
				.enterPostalCode("222").clickContinueButton();

		Assert.assertTrue(checkoutStepTwo.isPageLoaded(), "Checkout Step Two not loaded");
	}

	@Test(groups = { "regression", "checkout", "negative" })
	public void shouldShowErrorWhenFirstNameMissing() {

		CheckoutStepOnePage checkoutStepOne = goToCheckoutStepOne();

		checkoutStepOne.enterLastName("Rathod").enterPostalCode("222").clickContinueButton();

		Assert.assertTrue(checkoutStepOne.isErrorDisplayed(), "Error not shown for missing first name");
	}

	private CheckoutStepOnePage goToCheckoutStepOne() {
		return new LoginPage(DriverFactory.getDriver())
				.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password")).addDefaultItemsToCart()
				.clickCartIcon().proceedToCheckout();
	}
}
