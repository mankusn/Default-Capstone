package com.capstone.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.capstone.scraping.TidalAPI;

public class TidalNode {
	private int precipMM;
	private int visibility;
	private int swellDir; 
	private int waterTemp; 
	private double swellHeight; 
	private double swellPeriod; 
	private String windDir16Point;
	private int windDir;
	private int windSpeed;
	
	public TidalNode() throws JSONException {
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
	public String getWindDir16Point() {
		return windDir16Point;
	}
	public void setWindDir16Point(String windDir16Point) {
		this.windDir16Point = windDir16Point;
	}
	public int getWindDir() {
		return windDir;
	}
	public void setWindDir(int windDir) {
		this.windDir = windDir;
	}
	public int getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
	}
}
