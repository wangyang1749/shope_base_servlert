package com.shop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtils {
	public static Properties properties = new Properties();
	public static String getValue(String key) {
		try {
			InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("db.properties");
			properties.load(in);
			return properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
