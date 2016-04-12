package com.capstone.tests;

/*import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.capstone.dao.WeatherDataDAO;
import com.capstone.dao.WeatherDataDAOImpl;
import com.capstone.model.WeatherAndTidal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class WeatherAndTidalTest {
	
	@Configuration
	static class ContextConfiguration{
		@Bean
		public WeatherDataDAO weatherDataDAO() throws JSONException{
			return new WeatherDataDAOImpl(getDataSource());
		}
	}
	
	@Autowired
	private WeatherDataDAO weatherDataDAO;	
	
	
	@Before
	public void setup(){
	}
	
	@Test
	public void firstDatapointHighTempShouldBe62() {
		List<WeatherAndTidal> listData = weatherDataDAO.list();
		WeatherAndTidal wat = listData.get(0);
		assertEquals("The high temperature of our first datapoint in the data set should be 64",64,wat.getHighTemp(),1);
	}
	
	public static DataSource getDataSource() {
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

	
}*/
