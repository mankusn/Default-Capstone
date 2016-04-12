package com.capstone.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import com.capstone.model.WeatherAndTidal;
import com.capstone.dao.WeatherDataDAO;

public class WeatherAndTidalTest {
	private WeatherDataDAO weatherDataDAO;	
	
	
	@Before
	public void setup(){
	}
	
	@Test
	public void firstDatapointHighTempShouldBe62() {
		List<WeatherAndTidal> listData = weatherDataDAO.list();
		WeatherAndTidal wat = listData.get(0);
		assertEquals("The high temperature of our first datapoint in the data set should be 62",62,wat.getHighTemp(),1);
	}

}
