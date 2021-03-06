package com.capstone.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.capstone.dao.WeatherDataDAO;
import com.capstone.dao.WeatherDataDAOImpl;

@Configuration
@ComponentScan(basePackages = "com.capstone")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	//Sets prefix for pages as well as suffix so that they are not need in the URL
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	//Link to db that is stored locally on my machine
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		//dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
		//dataSource.setUrl("jdbc:postgresql://data.c5zqwqcqepbr.us-west-2.rds.amazonaws.com:5432/predictionData");
		dataSource.setUrl("jdbc:postgresql://ec2-54-235-93-178.compute-1.amazonaws.com:5432/d79sju7eaqsi7c?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory"); 
		//dataSource.setUsername("teamdefault");
		//dataSource.setPassword("teamdefault");
		dataSource.setUsername("pwmrefdinrglit");
		dataSource.setPassword("-knBqtlSOtrf3xIjHL093-DkGJ");

		return dataSource;
	}
	
	@Bean
	public WeatherDataDAO getWeatherAndTidalDao(){
		return new WeatherDataDAOImpl(getDataSource());
	}

}
