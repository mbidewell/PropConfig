package com.propconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.propconfig.beans.PropertiesBean;

@Configuration
public class AppConfig {
	@Bean
	public PropertiesBean getPropertiesBean() {
		return new PropertiesBean();
	}

}
