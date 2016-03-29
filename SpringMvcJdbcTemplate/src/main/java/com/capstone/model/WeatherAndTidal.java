package com.capstone.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.capstone.scraping.TidalAPI;
import com.capstone.scraping.WeatherAPI;

public class WeatherAndTidal {

	private int highTemp; 
	private int lowTemp; 
	private int precipProb; 
	private String outlook;
	private int windSpeed; 
	private int precipMM;
	private int visibility; 
	private int swellDir; 
	private int waterTemp; 
	private double swellHeight; 
	private double swellPeriod; 
	private String date; 
	private String windDir16Point;
	private int boatCount; 
	private int windDir;
	
	public WeatherAndTidal() throws JSONException{
		JSONObject weather = WeatherAPI.getNextDaysForecast();
		date = WeatherAPI.getDate(weather);
		highTemp = WeatherAPI.getHighTemp(weather);
		lowTemp = WeatherAPI.getLowTemp(weather);
		precipProb = WeatherAPI.getPrecipProb(weather);
		outlook = WeatherAPI.getOutlook(weather);
		windDir = WeatherAPI.getWindSpeed(weather);
		
		JSONObject tidal = TidalAPI.isolateTideData(TidalAPI.getTideDataForTime("1200",TidalAPI.getTideForecast()));
		precipMM = TidalAPI.getPrecipMM(tidal);
		visibility = TidalAPI.getVisibility(tidal);
		swellDir = TidalAPI.getSwellDir(tidal);
		waterTemp = TidalAPI.getWaterTemp(tidal);
		swellHeight = TidalAPI.getSwellHeight(tidal);
		swellPeriod = TidalAPI.getSwellPeriod(tidal);
		windDir16Point = TidalAPI.getCardinalWindDir(tidal);
		windSpeed = TidalAPI.getWindSpeed(tidal);
	}
	
	public int getHighTemp() {
		return highTemp;
	}
	public void setHighTemp(int highTemp) {
		this.highTemp = highTemp;
	}
	public int getLowTemp() {
		return lowTemp;
	}
	public void setLowTemp(int lowTemp) {
		this.lowTemp = lowTemp;
	}
	public int getPrecipProb() {
		return precipProb;
	}
	public void setPrecipProb(int precipProb) {
		this.precipProb = precipProb;
	}
	public String getOutlook() {
		return outlook;
	}
	public void setOutlook(String outlook) {
		this.outlook = outlook;
	}
	public int getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
	}
	public int getPrecipMM() {
		return precipMM;
	}
	public void setPrecipMM(int precipMM) {
		this.precipMM = precipMM;
	}
	public int getVisibility() {
		return visibility;
	}
	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	public int getSwellDir() {
		return swellDir;
	}
	public void setSwellDir(int swellDir) {
		this.swellDir = swellDir;
	}
	public int getWaterTemp() {
		return waterTemp;
	}
	public void setWaterTemp(int waterTemp) {
		this.waterTemp = waterTemp;
	}
	public double getSwellHeight() {
		return swellHeight;
	}
	public void setSwellHeight(double swellHeight) {
		this.swellHeight = swellHeight;
	}
	public double getSwellPeriod() {
		return swellPeriod;
	}
	public void setSwellPeriod(double swellPeriod) {
		this.swellPeriod = swellPeriod;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWindDir16Point() {
		return windDir16Point;
	}
	public void setWindDir16Point(String windDir16Point) {
		this.windDir16Point = windDir16Point;
	}
	public int getBoatCount() {
		return boatCount;
	}
	public void setBoatCount(int boatCount) {
		this.boatCount = boatCount;
	}
	public int getWindDir() {
		return windDir;
	}
	public void setWindDir(int windDir) {
		this.windDir = windDir;
	} 
	
	
	
	
	
	
	
	
	
	
	
	

}
