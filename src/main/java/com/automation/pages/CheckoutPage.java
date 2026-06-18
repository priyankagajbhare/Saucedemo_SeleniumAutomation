package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.base.BasePage;
import org.testng.Assert;

public class CheckoutPage extends BasePage {

	private static final Logger log = LoggerFactory.getLogger(CheckoutPage.class);

	private By checkoutPageTitle = By.cssSelector(".title");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zipCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By errorMessage = By.cssSelector("[data-test='error']");

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

    private By itemNameLink (String itemName) {
        return By.xpath("//div[normalize-space()='" + itemName + "']");
    }

    public void verifyCheckoutPageTitle() {
        Assert.assertEquals(actionUtils.getText(checkoutPageTitle, "Checkout page title"), "Checkout: Your Information");
    }

    public void enterUserDetails(String firstNameValue, String lastNameValue, String zipCodeValue) {
        actionUtils.type(firstName, firstNameValue, "First Name");
        actionUtils.type(lastName, lastNameValue, "Last Name");
        actionUtils.type(zipCode, zipCodeValue, "Zip Code");
        actionUtils.click(continueButton, "Continue");
    }

    public void verifyErrorMessage(String expctdErrorMessage) {
        Assert.assertEquals(actionUtils.getText(errorMessage, "Error message"), expctdErrorMessage);
    }

    public void verifyItemPresentInList(String itemName) {
        Assert.assertTrue(actionUtils.isElementVisible(itemNameLink(itemName), "Item name in list for " + itemName));
    }

}