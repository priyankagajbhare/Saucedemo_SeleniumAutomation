package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.base.BasePage;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class InventoryPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(InventoryPage.class);

	private By backpackAddToCartButton = By.id("add-to-cart-sauce-labs-backpack");
	private By boltTshirtAddToCartButton = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
	private By cartIcon = By.xpath("//a[@class='shopping_cart_link']");
	private By inventoryItems = By.cssSelector(".inventory_item_name");

	public InventoryPage(WebDriver driver) {
		super(driver);

	}

	public boolean isPageLoaded() {
		log.info("Validating Inventory Page");
		return driver.getCurrentUrl().contains("inventory");
	}

	public void addDefaultItemsToCart() {
		actionUtils.click(backpackAddToCartButton, "Sauce Labs Backpack add to cart");
		actionUtils.click(boltTshirtAddToCartButton, "Sauce Labs Bolt T-Shirt add to cart");
	}

	public int getItemCount() {
		return waitUtils.waitForAllElementsVisible(inventoryItems).size();
	}

	public List<String> getAllProductNames() {
		return actionUtils.getAllTextContents(inventoryItems, "Inventory items");
	}

	public void verifyProductList(String[] productList) {
		List<String> actualProductNames = getAllProductNames();
		Assert.assertEquals(actualProductNames, Arrays.asList(productList));
	}
}