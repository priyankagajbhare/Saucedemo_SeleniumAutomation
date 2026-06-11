package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.base.BasePage;

public class CheckoutStepTwoPage extends BasePage {

	private By finishButton = By.id("finish");
	private By summaryTitle = By.cssSelector(".title");

	public CheckoutStepTwoPage(WebDriver driver) {
		super(driver);
	}

	public boolean isPageLoaded() {
		return driver.getCurrentUrl().contains("checkout-step-two");
	}

	public CheckoutCompletePage completeCheckout() {
		actionUtils.click(finishButton, "Finish button");
		return new CheckoutCompletePage(driver);
	}

	public boolean isSummaryDisplayed() {
		return waitUtils.waitForElementVisible(summaryTitle).getText().equals("Checkout: Overview");
	}
}
