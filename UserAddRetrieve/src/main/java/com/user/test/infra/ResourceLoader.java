package com.user.test.infra;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.user.test.constants.UserConstants;


/**
 * This class holds the logic to load a resource which have necessary config
 * properties during run time. This is a singleton class which loads only once
 * during the application life-cycle.
 * 
 * @author AnilT
 */
public class ResourceLoader {
	private static Properties properties = new Properties();
	static {
		try {
			URL resourceURL = Thread.currentThread().getContextClassLoader()
					.getResource(UserConstants.CONNECTION_FILE_WITH_PATH_FOR_APP);

			properties.load(resourceURL.openStream());
		} catch (FileNotFoundException exp) {
			exp.printStackTrace();
		} catch (IOException exp) {
			exp.printStackTrace();
		}
	}

	/**
	 * Returns the properties value based on key
	 * 
	 * @param propertyKey
	 * @return
	 */
	public static String getPropertyValue(String propertyKey) {
		if (propertyKey != null && !propertyKey.isEmpty()) {
			return properties.getProperty(propertyKey);
		}
		return null;
	}

	/**
	 * Returns the properties of all the config attributes defined in the
	 * properties file.
	 * 
	 * @return
	 */
	public static Map<String, String> getProperties() {
		Map<String, String> propertyMap = new HashMap<String, String>();
		for (String propertyKey : properties.stringPropertyNames()) {
			propertyMap.put(propertyKey, properties.getProperty(propertyKey));
		}
		return propertyMap;
	}
}

