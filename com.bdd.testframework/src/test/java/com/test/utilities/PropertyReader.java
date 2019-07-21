package com.test.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

	private static FileInputStream fip;
	private static Properties configProperty;

	public static Properties loadConfigProperty() throws IOException {
		configProperty = new Properties();
		System.out.println();
		fip = new FileInputStream(new File("src\\test\\resources\\propertiesFiles\\config.properties"));
		configProperty.load(fip);
		return configProperty;
	}

	public static String getConfigPropertyValue(String key) {
		String value = "";
		try {
			value = PropertyReader.loadConfigProperty().getProperty(key);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return value;
	}

	public static Properties loadGenericProperty() throws IOException {
		configProperty = new Properties();
		System.out.println();
		fip = new FileInputStream(new File("src\\test\\resources\\propertiesFiles\\generic.properties"));
		configProperty.load(fip);
		return configProperty;
	}

	public static String getGenericPropertyValue(String key) {
		String value = "";
		try {
			value = PropertyReader.loadConfigProperty().getProperty(key);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return value;
	}
}
