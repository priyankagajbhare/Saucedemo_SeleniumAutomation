package com.automation.tests;

import com.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.driver.DriverFactory;
import com.automation.utils.ConfigReader;


public class CartCheckoutTest extends BaseTest {

    @Test
    public void verifyItemCanBeRemovedFromCart() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
        inventoryPage.addToCart("Sauce Labs Backpack");
        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.goToCartPage();
        cartPage.verifyItemPresentInCart("Sauce Labs Backpack");
        cartPage.removeItemfromCart("Sauce Labs Backpack");
        cartPage.verifyItemNotPresentInCart("Sauce Labs Backpack");
    }

    @Test
    public void verifyUserCanMoveToCheckout() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
        inventoryPage.addToCart("Sauce Labs Backpack");
        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.goToCartPage();
        cartPage.verifyItemPresentInCart("Sauce Labs Backpack");
        cartPage.proceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());
        checkoutPage.verifyCheckoutPageTitle();
    }

    @Test
    public void verifyUserCanCheckoutWithValidInformation() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
        inventoryPage.addToCart("Sauce Labs Backpack");
        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.goToCartPage();
        cartPage.proceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());
        checkoutPage.enterUserDetails("Priya", "Kumar", "642002");
        checkoutPage.verifyItemPresentInList("Sauce Labs Backpack");
    }

    @Test
    public void verifyUserCanCheckoutWithMissingUsername() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
        inventoryPage.addToCart("Sauce Labs Backpack");
        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.goToCartPage();
        cartPage.proceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());
        checkoutPage.enterUserDetails("", "Kumar", "642002");
        checkoutPage.verifyErrorMessage("Error: First Name is required");
    }

    @Test
    public void verifyUserCanCheckoutWithMissingInformation() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.loginAsValidUser(ConfigReader.get("username"), ConfigReader.get("password"));

        InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
        inventoryPage.addToCart("Sauce Labs Backpack");
        CartPage cartPage = new CartPage(DriverFactory.getDriver());
        cartPage.goToCartPage();
        cartPage.proceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());
        checkoutPage.enterUserDetails("Priya", "", "642002");
        checkoutPage.verifyErrorMessage("Error: Last Name is required");
//        checkoutPage.enterUserDetails("Priya", "Kumar", "");
//        checkoutPage.verifyErrorMessage("Error: Postal Code is required");
    }
}