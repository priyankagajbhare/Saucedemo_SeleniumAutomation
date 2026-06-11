package com.automation.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static final Properties prop = new Properties();

	static {
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/config.properties");
			prop.load(fis);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load config file", e);
		}
	}

	public static String get(String key) {
		return prop.getProperty(key).trim();
	}

	public static int getInt(String key) {
		return Integer.parseInt(get(key));
	}
}