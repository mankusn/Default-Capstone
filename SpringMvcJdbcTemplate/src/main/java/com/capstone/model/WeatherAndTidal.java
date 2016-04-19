package com.capstone.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.capstone.scraping.TidalAPI;
import com.capstone.scraping.WeatherAPI;

public class WeatherAndTidal {

	private int highTemp; 
	private String date;
	private int lowTemp; 
	private int precipProb; 
	private int windSpeed; 
	private double precip;
	private int swellDir; 
	private int waterTemp; 
	private double swellHeight; 
	private double swellPeriod;  
	private int boatCount;
	private int boatCount2;
	private int boatCount3;
	private int boatCount4;
	private int boatTotal;
	private int windDir;
	
	public WeatherAndTidal() throws JSONException{}
	
	public WeatherAndTidal(long unix) throws JSONException{
		JSONObject weather = WeatherAPI.getPastWeather(unix);
		date = WeatherAPI.getDate(weather);
		highTemp = WeatherAPI.getHighTemp(weather);
		lowTemp = WeatherAPI.getLowTemp(weather);
		//precipProb = WeatherAPI.getPrecipProb(weather);
		windDir = WeatherAPI.getWindDir(weather);
		windSpeed = WeatherAPI.getWindSpeed(weather);
		//precip = WeatherAPI.getPrecip(weather);
		
		/*JSONObject tidal = TidalAPI.isolateTideData(TidalAPI.getTideDataForTime("1200",TidalAPI.getTideForecast()));
		swellDir = TidalAPI.getSwellDir(tidal);
		waterTemp = TidalAPI.getWaterTemp(tidal);
		swellHeight = TidalAPI.getSwellHeight(tidal);
		swellPeriod = TidalAPI.getSwellPeriod(tidal);*/
	}
	
	public static WeatherAndTidal getToday() throws JSONException{
		WeatherAndTidal wat = new WeatherAndTidal();
		JSONObject weather = WeatherAPI.getTodaysForecast();
		wat.highTemp = WeatherAPI.getHighTemp(weather);
		wat.lowTemp = WeatherAPI.getLowTemp(weather);
		//wat.precipProb = WeatherAPI.getPrecipProb(weather);
		wat.windDir = WeatherAPI.getWindDir(weather);
		wat.windSpeed = WeatherAPI.getWindSpeed(weather);
		//wat.precip = WeatherAPI.getPrecip(weather);
		
		JSONObject tidal = TidalAPI.isolateTideData(TidalAPI.getTideDataForTime("1200",TidalAPI.getTideForecast()));
		wat.swellDir = TidalAPI.getSwellDir(tidal);
		wat.waterTemp = TidalAPI.getWaterTemp(tidal);
		wat.swellHeight = TidalAPI.getSwellHeight(tidal);
		wat.swellPeriod = TidalAPI.getSwellPeriod(tidal);
		
		return wat;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public String getDate(){
		return date;
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
	public int getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
	}
	public double getPrecip() {
		return precip;
	}
	public void setPrecip(double precip) {
		this.precip = precip;
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

	public int getBoatCount2() {
		return boatCount2;
	}

	public void setBoatCount2(int boatCount2) {
		this.boatCount2 = boatCount2;
	}

	public int getBoatCount3() {
		return boatCount3;
	}

	public void setBoatCount3(int boatCount3) {
		this.boatCount3 = boatCount3;
	}

	public int getBoatCount4() {
		return boatCount4;
	}

	public void setBoatCount4(int boatCount4) {
		this.boatCount4 = boatCount4;
	}

	public int getBoatTotal() {
		return boatTotal;
	}

	public void setBoatTotal(int boatTotal) {
		this.boatTotal = boatTotal;
	} 
	
	
	
	
	
	
	
	
	
	
	
	

}
