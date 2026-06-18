package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.base.BasePage;
import org.testng.Assert;

public class CartPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(CartPage.class);

	private By cartIcon = By.cssSelector(".shopping_cart_link");
	private By checkoutButton = By.id("checkout");

	public CartPage(WebDriver driver) {
		super(driver);
	}

    private By itemNameLink (String productName) {
        return By.xpath("//div[normalize-space()='" + productName + "']");
    }

    private By itemRemoveButton (String productName) {
        return By.xpath("//div[contains(text(),'" + productName + "')]/ancestor::div[@class='cart_item_label']//button[contains(text(),'Remove')]");
    }

    public void removeItemfromCart(String productName) {
        actionUtils.click(itemRemoveButton(productName), "Remove item from cart for " + productName);
    }

    public void goToCartPage() {
        actionUtils.click(cartIcon, "Go to cart page");
    }

    public void verifyItemPresentInCart(String productName) {
        Assert.assertTrue(actionUtils.isElementVisible(itemNameLink(productName), "Item name in cart for " + productName));
    }

    public void verifyItemNotPresentInCart(String productName) {
        Assert.assertFalse(actionUtils.isElementVisible(itemNameLink(productName), "Item name in cart for " + productName));
    }

    public void proceedToCheckout() {
        actionUtils.click(checkoutButton, "Proceed to checkout");
    }

    
}