package com.propconfig.controllers;

import java.io.FileNotFoundException;

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
public class FileController {
	@Autowired
	private PropertiesBean props;

	@RequestMapping(path="/file/{group}", produces = "text/plain")
	String propertiesFile( @PathVariable("group") String group) throws ConfigurationException, FileNotFoundException {
		return props.getPropertiesFile(group);
	}

	@RequestMapping(path="/file/{group}", produces = "text/plain", method = RequestMethod.POST)
	String addPropertyFile( @PathVariable("group") String group, @RequestBody String propText) 
			throws ConfigurationException, FileNotFoundException, FileExistsException {
		return props.addPropertiesFile(group, propText);
	}
	
	@RequestMapping(path="/file/{group}", produces = "text/plain", method = RequestMethod.PUT)
	String updatePropertyFile( @PathVariable("group") String group, @RequestBody String propText) 
			throws ConfigurationException, FileNotFoundException, FileExistsException {
		return props.addPropertiesFile(group, propText);
	}

	@RequestMapping(path="/file/{group}", produces = "text/plain", method = RequestMethod.DELETE)
	String deletePropertyFile( @PathVariable("group") String group, @RequestBody String propText) 
			throws ConfigurationException, FileNotFoundException {
		return props.deletePropertiesFile(group);
	}
}
