package com.capstone.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.capstone.scraping.WeatherAPI;


public class WeatherAPITest{
	protected long unixTime;
	
	@Before
	public void setUp(){
		unixTime = 1433823669000L;
	}
	
	@Test
	public void pastHighTemperatureShouldBe91Point57() throws JSONException{
		JSONObject weather = WeatherAPI.getPastWeather(unixTime);
		assertEquals("The high temperature on June 9th, 2015 should be",91.57,weather.getDouble("temperatureMax"),1);
	}
	
	@Test
	public void nextDaysForecastShouldExist() throws JSONException{
		JSONObject forecast = WeatherAPI.getNextDaysForecast();
		assertNotNull("Can't test the contents, but the next day's forecast should exist", forecast);
	}
}
