package com.sachin.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sachin.automation.base.BasePage;

public class CheckoutStepOnePage extends BasePage {

	private By firstNameField = By.id("first-name");
	private By lastNameField = By.id("last-name");
	private By postalCodeField = By.id("postal-code");
	private By continueButton = By.id("continue");
	private By errorMessage = By.cssSelector("[data-test='error']");

	public CheckoutStepOnePage(WebDriver driver) {
		super(driver);
	}

	public boolean isPageLoaded() {
		return driver.getCurrentUrl().contains("checkout-step-one");
	}

	public CheckoutStepOnePage enterFirstName(String firstName) {
		actionUtils.type(firstNameField, firstName, "First name field");
		return this;
	}

	public CheckoutStepOnePage enterLastName(String lastName) {
		actionUtils.type(lastNameField, lastName, "Last name field");
		return this;
	}

	public CheckoutStepOnePage enterPostalCode(String postalCode) {
		actionUtils.type(postalCodeField, postalCode, "Postal code field");
		return this;
	}

	public CheckoutStepTwoPage clickContinueButton() {
		actionUtils.click(continueButton, "Continue button");
		return new CheckoutStepTwoPage(driver);
	}

	public boolean isErrorDisplayed() {
		return waitUtils.waitForElementVisible(errorMessage).isDisplayed();
	}
}
