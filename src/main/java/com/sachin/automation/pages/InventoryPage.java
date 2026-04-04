package com.sachin.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sachin.automation.base.BasePage;

public class InventoryPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(InventoryPage.class);

	private By backpackAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
	private By boltTshirtAddToCartButton = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
	private By cartIcon = By.xpath("//a[@class='shopping_cart_link']");
	private By inventoryItems = By.cssSelector(".inventory_item");

	public InventoryPage(WebDriver driver) {
		super(driver);

	}

	public boolean isPageLoaded() {
		log.info("Validating Inventory Page");
		return driver.getCurrentUrl().contains("inventory");
	}

	public InventoryPage addDefaultItemsToCart() {
		actionUtils.click(backpackAddToCartButton, "Sauce Labs Backpack add to cart");
		actionUtils.click(boltTshirtAddToCartButton, "Sauce Labs Bolt T-Shirt add to cart");

		return this;
	}

	public CartPage clickCartIcon() {
		actionUtils.click(cartIcon, "Cart icon");

		return new CartPage(driver);
	}

	public int getItemCount() {
		return waitUtils.waitForAllElementsVisible(inventoryItems).size();
	}
}