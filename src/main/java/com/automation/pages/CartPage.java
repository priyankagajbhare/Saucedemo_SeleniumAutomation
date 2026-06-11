package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.base.BasePage;

public class CartPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(CartPage.class);

	private By checkoutButton = By.id("checkout");
	private By cartItems = By.cssSelector(".cart_item");

	public CartPage(WebDriver driver) {
		super(driver);
	}

	public boolean isPageLoaded() {
		log.info("Validating Cart Page");
		return driver.getCurrentUrl().contains("cart");
	}

	public CheckoutStepOnePage proceedToCheckout() {
		actionUtils.click(checkoutButton, "Checkout button");
		return new CheckoutStepOnePage(driver);
	}

	public int getCartItemCount() {
		return waitUtils.waitForAllElementsVisible(cartItems).size();
	}
}