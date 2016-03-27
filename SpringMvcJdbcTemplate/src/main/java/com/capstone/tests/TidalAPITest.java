package com.capstone.tests;

import static org.junit.Assert.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import com.capstone.scraping.TidalAPI;

public class TidalAPITest {

	@Test
	public void tidalForecastShouldExist() throws JSONException{
		JSONArray a = TidalAPI.getTideForecast();
		JSONObject b = TidalAPI.getTideDataForTime("2100",a);
		assertNotNull("Can't test the contents, but the next day's forecast should exist", b);
	}

}
