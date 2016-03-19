package com.propconfig.beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesBean {
	
	Map<String, PropertiesConfiguration> propsMap = new HashMap<String, PropertiesConfiguration>();
	
	private PropertiesConfiguration loadPropertiesFile(String group) throws ConfigurationException, FileNotFoundException {
		File propsFile = new File(String.format("config/%s.properties", group));	

		if(propsMap.containsKey(group)) {
			return propsMap.get(group);
		} else if(propsFile.exists()) {
			PropertiesConfiguration config = new PropertiesConfiguration(propsFile);
			propsMap.put(group, config);
			return config;
		}
		throw new FileNotFoundException("Properties File Not Found");
	}
	
	public String getPropertiesFile(String group) throws ConfigurationException, FileNotFoundException {
		StringWriter propData = new StringWriter();
		PropertiesConfiguration config = loadPropertiesFile(group);
		config.save(propData);
		
		return propData.toString();
	}
	
	public String getPropertyValue(String group, String prop) throws ConfigurationException, FileNotFoundException {
		PropertiesConfiguration config = loadPropertiesFile(group);
		return config.getString(prop);
	}
}
