package com.propconfig.controllers;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pennmutual.exceptions.FileExistsException;
import com.propconfig.beans.PropertiesBean;

@RestController
public class ValueController {
	@Autowired
	private PropertiesBean props;
	
	@RequestMapping(path="/value/{group}/{prop}", produces = "text/plain")
	String propertyValue( @PathVariable("group") String group, @PathVariable("prop") String prop) 
			throws ConfigurationException, FileNotFoundException {
		return props.getPropertyValue(group, prop);
	}

	@RequestMapping(path="/value/{group}/{prop}", produces = "text/plain", method = RequestMethod.POST)
	String addPropertyValue( @PathVariable("group") String group,@PathVariable("prop") String prop, @RequestBody String propText) 
			throws ConfigurationException, FileNotFoundException, FileExistsException, UnsupportedEncodingException {
		return props.setPropertyValue(group, prop, propText);
	}
	
	@RequestMapping(path="/value/{group}/{prop}", produces = "text/plain", method = RequestMethod.PUT)
	String setPropertyValue( @PathVariable("group") String group,@PathVariable("prop") String prop, @RequestBody String propText) 
			throws ConfigurationException, FileNotFoundException, FileExistsException, UnsupportedEncodingException {
		return props.setPropertyValue(group, prop, propText);
	}
}
