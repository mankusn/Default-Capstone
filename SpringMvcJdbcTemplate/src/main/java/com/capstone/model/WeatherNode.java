package com.capstone.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.capstone.scraping.WeatherAPI;

public class WeatherNode {
	private String date;
	private int highTemp; 
	private int lowTemp; 
	private int precipProb; 
	private String outlook;
	
	public WeatherNode() throws JSONException{
		JSONObject weather = WeatherAPI.getNextDaysForecast();
		date = WeatherAPI.getDate(weather);
		highTemp = WeatherAPI.getHighTemp(weather);
		lowTemp = WeatherAPI.getLowTemp(weather);
		precipProb = WeatherAPI.getPrecipProb(weather);
		outlook = WeatherAPI.getOutlook(weather);
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
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
}
