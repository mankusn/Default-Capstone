package com.capstone.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherAndTidalString {
	private String highTemp; 
	private String lowTemp;
	private String windSpeed;
	private String swellDir; 
	private String waterTemp; 
	private String swellHeight; 
	private String swellPeriod;  
	private String boatCount; 
	private String boatCount2;
	private String boatCount3;
	private String boatCount4;
	private String boatTotal;
	private String windDir;
	
	public WeatherAndTidalString(WeatherAndTidal obj){
		highTemp = String.valueOf(obj.getHighTemp());
		lowTemp = String.valueOf(obj.getLowTemp());
		windSpeed = String.valueOf(obj.getWindSpeed());
		swellDir = String.valueOf(obj.getSwellDir());
		waterTemp = String.valueOf(obj.getWaterTemp());
		swellHeight = String.valueOf(obj.getSwellHeight());
		swellPeriod = String.valueOf(obj.getSwellPeriod());
		boatCount = String.valueOf(obj.getBoatCount());
		boatCount2 = String.valueOf(obj.getBoatCount2());
		boatCount3= String.valueOf(obj.getBoatCount3());
		boatCount4= String.valueOf(obj.getBoatCount4());
		boatTotal = String.valueOf(obj.getBoatTotal());
		windDir = String.valueOf(obj.getWindDir());
	}
	
	public static HashMap<String,String> getMap(WeatherAndTidalString obj) throws JsonParseException, JsonMappingException, IOException, JSONException{
		ObjectMapper mapper = new ObjectMapper(); 
		String json = mapper.writeValueAsString(obj).toLowerCase();
		HashMap<String,String> map = new HashMap<String,String>();
		map = mapper.readValue(json, new TypeReference<HashMap<String,String>>(){});
        return map;
	}
	
	public String toString() {
		return "High temp: " + highTemp + "\nLow temp: " + lowTemp + "\nWind speed: " + windSpeed + 
				"\nSwell direction: " + swellDir + "\nWater temp: " + waterTemp + "\nSwell Height: " + swellHeight + 
				"\nSwell period:  " + swellPeriod + "\nWind direction: " + windDir;
	}
	
	public Vector<String> stringForecast(){
		Vector<String> vec = new Vector<String>();
		vec.add("High temp: " + highTemp + "\u00b0" + "F");
		vec.add("Low temp: " + lowTemp + "\u00b0" + "F");
		vec.add("Wind speed: " + windSpeed + "mph");
		vec.add("Swell direction: " + swellDir + "\u00b0");
		vec.add("Water temp: " + waterTemp + "\u00b0" + "F");
		vec.add("Swell Height: " + swellHeight + "m");
		vec.add("Swell period:  " + swellPeriod + "s");
		vec.add("Wind direction: " + windDir + "\u00b0");
		return vec;
	}

	public String getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(String highTemp) {
		this.highTemp = highTemp;
	}

	public String getLowTemp() {
		return lowTemp;
	}

	public void setLowTemp(String lowTemp) {
		this.lowTemp = lowTemp;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getSwellDir() {
		return swellDir;
	}

	public void setSwellDir(String swellDir) {
		this.swellDir = swellDir;
	}

	public String getWaterTemp() {
		return waterTemp;
	}

	public void setWaterTemp(String waterTemp) {
		this.waterTemp = waterTemp;
	}

	public String getSwellHeight() {
		return swellHeight;
	}

	public void setSwellHeight(String swellHeight) {
		this.swellHeight = swellHeight;
	}

	public String getSwellPeriod() {
		return swellPeriod;
	}

	public void setSwellPeriod(String swellPeriod) {
		this.swellPeriod = swellPeriod;
	}

	public String getBoatCount() {
		return boatCount;
	}

	public void setBoatCount(String boatCount) {
		this.boatCount = boatCount;
	}

	public String getWindDir() {
		return windDir;
	}

	public void setWindDir(String windDir) {
		this.windDir = windDir;
	}

	public String getBoatCount2() {
		return boatCount2;
	}

	public void setBoatCount2(String boatCount2) {
		this.boatCount2 = boatCount2;
	}

	public String getBoatCount3() {
		return boatCount3;
	}

	public void setBoatCount3(String boatCount3) {
		this.boatCount3 = boatCount3;
	}

	public String getBoatCount4() {
		return boatCount4;
	}

	public void setBoatCount4(String boatCount4) {
		this.boatCount4 = boatCount4;
	}
	
	public String getBoatTotal() {
		return boatTotal;
	}

	public void setBoatTotal(String boatTotal) {
		this.boatTotal = boatTotal;
	}
}
