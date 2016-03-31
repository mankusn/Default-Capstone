package com.capstone.model;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherAndTidalString {
	private String highTemp; 
	private String lowTemp; 
	private String precipProb; 
	private String outlook;
	private String windSpeed; 
	private String precip;
	private String visibility; 
	private String swellDir; 
	private String waterTemp; 
	private String swellHeight; 
	private String swellPeriod; 
	private String date; 
	private String boatCount; 
	private String windDir;
	
	public WeatherAndTidalString(WeatherAndTidal obj){
		highTemp = String.valueOf(obj.getHighTemp());
		lowTemp = String.valueOf(obj.getLowTemp());
		precipProb = String.valueOf(obj.getPrecipProb());
		outlook = obj.getOutlook();
		windSpeed = String.valueOf(obj.getWindSpeed());
		precip = String.valueOf(obj.getPrecip());
		visibility = String.valueOf(obj.getVisibility());
		swellDir = String.valueOf(obj.getSwellDir());
		waterTemp = String.valueOf(obj.getWaterTemp());
		swellHeight = String.valueOf(obj.getSwellHeight());
		swellPeriod = String.valueOf(obj.getSwellPeriod());
		date = obj.getDate();
		boatCount = String.valueOf(obj.getBoatCount());
		windDir = String.valueOf(obj.getWindDir());
	}
	
	public static HashMap<String,String> getMap(WeatherAndTidalString obj) throws JsonParseException, JsonMappingException, IOException, JSONException{
		ObjectMapper mapper = new ObjectMapper(); 
		String json = mapper.writeValueAsString(obj);
		HashMap<String,String> map = new HashMap<String,String>();
		map = mapper.readValue(json, new TypeReference<HashMap<String,String>>(){});
        return map;
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

	public String getPrecipProb() {
		return precipProb;
	}

	public void setPrecipProb(String precipProb) {
		this.precipProb = precipProb;
	}

	public String getOutlook() {
		return outlook;
	}

	public void setOutlook(String outlook) {
		this.outlook = outlook;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getPrecip() {
		return precip;
	}

	public void setPrecip(String precip) {
		this.precip = precip;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
}
