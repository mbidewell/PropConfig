package com.propconfig.controllers;

import java.io.FileNotFoundException;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propconfig.beans.PropertiesBean;

@RestController
public class RootController {
	@Autowired
	private PropertiesBean props;

	@RequestMapping(path="/file/{group}", produces = "text/plain")
	String propertiesFile( @PathVariable("group") String group) throws ConfigurationException, FileNotFoundException {
		return props.getPropertiesFile(group);
	}

	@RequestMapping(path="/value/{group}/{prop}", produces = "text/plain")
	String propertyValue( @PathVariable("group") String group, @PathVariable("prop") String prop) 
			throws ConfigurationException, FileNotFoundException {
		return props.getPropertyValue(group, prop);
	}

}
