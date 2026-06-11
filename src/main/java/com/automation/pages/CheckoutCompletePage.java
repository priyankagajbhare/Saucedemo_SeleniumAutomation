package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.base.BasePage;

public class CheckoutCompletePage extends BasePage {

	private By backToProductsButton = By.id("back-to-products");

	public CheckoutCompletePage(WebDriver driver) {
		super(driver);
	}

	public boolean isPageLoaded() {
		return driver.getCurrentUrl().contains("checkout-complete");
	}

	public InventoryPage clickBackToProducts() {
		actionUtils.click(backToProductsButton, "Back to products button");
		return new InventoryPage(driver);
	}
}