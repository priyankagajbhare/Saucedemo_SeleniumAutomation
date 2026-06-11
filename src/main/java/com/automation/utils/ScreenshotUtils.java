package com.automation.utils;

import java.io.File;
import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtils {

	public static String capture(WebDriver driver, String testName) {

		try {
			String folderPath = System.getProperty("user.dir") + "/reports/screenshots/";
			File folder = new File(folderPath);

			if (!folder.exists()) {
				folder.mkdirs();
			}

			String fileName = testName + "_" + System.currentTimeMillis() + ".png";

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File(folderPath + fileName);

			FileUtils.copyFile(src, dest);

			return dest.getAbsolutePath();

		} catch (Exception e) {
			throw new RuntimeException("Failed to capture screenshot", e);
		}
	}
}