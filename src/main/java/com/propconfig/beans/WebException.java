package com.propconfig.beans;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WebException {
	
	private static Logger logger = Logger.getLogger(WebException.class);
	
	@ExceptionHandler(ConfigurationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void handleConfigurationException(ConfigurationException ex, 
			HttpServletResponse response) {
		logger.error(ex.getMessage());
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void handleDataFormatException(FileNotFoundException ex, 
			HttpServletResponse response) {
		logger.error(ex.getMessage());
	  }
}
