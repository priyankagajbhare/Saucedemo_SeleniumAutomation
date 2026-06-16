package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.base.BasePage;
import org.testng.Assert;

public class ProductDetailsPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(ProductDetailsPage.class);

	private By productName = By.cssSelector(".inventory_details_name.large_size");
	private By productDescription = By.cssSelector(".inventory_details_desc.large_size");
	private By productCost = By.cssSelector(".inventory_details_price");

	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}

    public void verifyProductDetails(String productName, String productDescription, String productCost) {
        Assert.assertEquals(actionUtils.getText(this.productName, "Product Name"), productName);
        Assert.assertEquals(actionUtils.getText(this.productDescription, "Product Description"), productDescription);
        Assert.assertEquals(actionUtils.getText(this.productCost, "Product Cost"), productCost);
    }
}