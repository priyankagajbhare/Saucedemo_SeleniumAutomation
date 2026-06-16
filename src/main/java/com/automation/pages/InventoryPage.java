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
	private By cartIcon = By.cssSelector(".shopping_cart_badge");
	private By inventoryItems = By.cssSelector(".inventory_item_name");
	private By sortDropdown = By.cssSelector("select.product_sort_container");

	public InventoryPage(WebDriver driver) {
		super(driver);

	}
	
	private By productNameLink (String productName) {
		return By.xpath("//a[.//div[contains(@class,'inventory_item_name') and normalize-space()='" + productName + "']]");
	}

	private By itemAddToCartButton (String productName) {
		return By.xpath("//div[contains(text(),'" + productName + "')]/ancestor::div[@class='inventory_item_label']/following-sibling::div//button[contains(text(),'Add to cart')]");
	}

	private By itemRemoveFromCartButton (String productName) {
		return By.xpath("//div[contains(text(),'" + productName + "')]/ancestor::div[@class='inventory_item_label']/following-sibling::div//button[contains(text(),'Remove')]");
	}

	public void addToCart(String productName) {
		actionUtils.click(itemAddToCartButton(productName), "Add to cart for " + productName);
	}

	public void removeFromCart(String productName) {
		actionUtils.click(itemRemoveFromCartButton(productName), "Remove from cart for " + productName);
	}

	public void openProductDetails(String productName) {
		actionUtils.click(productNameLink(productName), "Open product details for " + productName);
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

	public void sortProducts(String sortOption) {
		actionUtils.selectOption(sortDropdown, sortOption);
	}

	public void verifyCartCount(String expectedCount) {
		Assert.assertEquals(actionUtils.getText(cartIcon, "Cart count"), expectedCount);
	}

	public void verifyNoItemsInCart() {
		Assert.assertFalse(actionUtils.isElementVisible(cartIcon, "Cart icon"));
	}



}