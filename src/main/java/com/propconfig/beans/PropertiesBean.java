package com.propconfig.beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.pennmutual.exceptions.FileExistsException;

public class PropertiesBean {
	
	private static Logger logger = Logger.getLogger(PropertiesBean.class); 
	
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
	
	private File writePropertiesFile(String group, String propText) throws ConfigurationException, UnsupportedEncodingException {
		File propsFile = new File(String.format("config/%s.properties", group));
		PropertiesConfiguration config = new PropertiesConfiguration();
		
		config.load(new StringReader(URLDecoder.decode(propText, "UTF-8")));
		config.save(propsFile);
		propsMap.put(group, config);	
		
		return propsFile;
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
	
	public String setPropertyValue(String group, String prop, String value) throws ConfigurationException, FileNotFoundException, UnsupportedEncodingException {
		PropertiesConfiguration config = loadPropertiesFile(group);
		config.setProperty(prop, URLDecoder.decode(value, "UTF-8"));
		config.save();
		return config.getString(prop);
	}	
	
	public String addPropertiesFile(String group, String propText) throws ConfigurationException, FileExistsException {
		if(propsMap.containsKey(group)) {
			throw new FileExistsException();
		} else {
			try {
				File propsFile = this.writePropertiesFile(group, propText);
				return String.format("Created Properties File %s", propsFile.getAbsolutePath());
			} catch (UnsupportedEncodingException e) {
				return String.format("Wrong encoding for Properties File.");
			}
		}
	}
	
	public String deletePropertiesFile(String group) throws ConfigurationException, FileNotFoundException {
		if(!propsMap.containsKey(group)) {
			throw new FileNotFoundException();
		} else {
			File propsFile = new File(String.format("config/%s.properties", group));
			if(propsFile.delete()) {
				propsMap.remove(group);
				return String.format("Deleted Properties File %s", propsFile.getAbsolutePath());
			} else {
				throw new ConfigurationException(String.format("Unable to remove Properties File %s", propsFile.getAbsolutePath()));
			}
		}
	}
}
