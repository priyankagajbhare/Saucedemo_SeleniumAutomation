package com.automation.reporting;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static ExtentReports extentReports;

	public static ExtentReports getInstance() {

		if (extentReports == null) {
			ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extent-report.html");
			File reportDir = new File(System.getProperty("user.dir") + "/reports");
			if (!reportDir.exists()) {
				reportDir.mkdirs();
			}
			reporter.config().setReportName("SauceDemo Automation Report");

			extentReports = new ExtentReports();
			extentReports.attachReporter(reporter);
			extentReports.setSystemInfo("Tester", "Sachin");
		}
		return extentReports;
	}
}