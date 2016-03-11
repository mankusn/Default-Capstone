package api;

import org.json.JSONObject;
import static org.junit.Assert.*;
import org.json.JSONException;
import org.junit.*;

import api.WeatherAPI;

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
