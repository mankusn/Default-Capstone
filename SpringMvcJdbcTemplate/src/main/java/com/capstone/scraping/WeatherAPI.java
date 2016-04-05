package com.capstone.scraping;

/*
 * TODO: 1) Integrate with database
 */

//Example program to test retrieving weather from the forecast.io API

import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WeatherAPI{
	//Helper function unixTimeToDate takes a unix timestamp
	//and returns a date formatted to fit the forecast.io API
	public static String unixTimeToDate(long unix){
		//http://stackoverflow.com/questions/17432735/convert-unix-time-stamp-to-date-in-java
		Date date = new Date(unix);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
	//makeAPIRequest has two cases, depending on whether we're
	//requesting current/future or past weather
	//
	//Case for current/future weather data where no date is required
	public static String makeAPIRequest() throws JSONException{
		return makeAPIRequest("");
	}

	//Case for past weather data where a date is required
	public static String makeAPIRequest(String date){
		//Creates the API request string
		String APIKey = "c62d91cb9c000638716e55cbc478330f"; //Replace this if we run out of requests on an account
		String latitude = "39.2645";
		String longitude = "26.2777"; //Lesvos, Greece
		String request = "https://api.forecast.io/forecast/" + APIKey + "/" + latitude + "," +
						longitude;
		if(!date.equals(""))
			request+= "," + date;
		return request;
	}
	
	public static JSONObject makeAPICall(String request) throws JSONException{
		//I used the "Consuming a RESTful Web Service" found here: https://spring.io/guides/gs/consuming-rest/
		RestTemplate restTemplate = new RestTemplate();
		String daysWeather = restTemplate.getForObject(request,String.class);
		JSONObject weatherObj = new JSONObject(daysWeather);
		return weatherObj;
	}
	
	
	//getPastWeather and getNextDaysForecast return a JSONObject containing
	//weather data for a given date. Future weather returns the next seven days,
	//so it needs to be parsed to return a single day
	
	public static JSONObject getPastWeather(long unixStamp) throws JSONException{
		String date = unixTimeToDate(unixStamp);
		String request = makeAPIRequest(date);
		JSONObject weatherObj = makeAPICall(request);
		
		//We don't need the "current" or hourly report, just data for the whole day
		//This data is presented as a JSONObject that contains a JSONArray with one element
		//With that element being the JSONObject containing the daily report
		JSONObject fakeDailyObj = weatherObj.getJSONObject("daily");
		JSONArray arr = (JSONArray) fakeDailyObj.get("data");
		JSONObject dailyObj = (JSONObject) arr.get(0);
		return dailyObj;
	}
	
	public static JSONObject getNextDaysForecast() throws JSONException{
		String request = makeAPIRequest();
		JSONObject weatherObj = makeAPICall(request);
		
		//Like the past data, each day's data is a JSONObject inside a JSONArray inside a JSONObject
		//In this case, there are 8 objects inside the array, with each one representing a day
		JSONObject dailyForecastObj = weatherObj.getJSONObject("daily");
		JSONArray days = (JSONArray) dailyForecastObj.get("data");
		JSONObject dayObj = (JSONObject) days.get(1);
		return dayObj;
	}

	
	//getter methods for parsing the object returned by getPastWeather and
	//getNextDaysForecast. This way is easier for the algorithm than to create
	//a class that contains all these members
	
	public static String getDate(JSONObject obj) throws JSONException{
		String fullTime = unixTimeToDate((obj.getLong("time")*1000));
		String date = fullTime.substring(0, 10);
		return date;
	}
	
	public static int getHighTemp(JSONObject obj) throws JSONException{
		return (int)(obj.getDouble("temperatureMax"));
	}
	
	public static int getLowTemp(JSONObject obj) throws JSONException{
		return (int)(obj.getDouble("temperatureMin"));
	}
	
	public static int getPrecipProb(JSONObject obj) throws JSONException{
		return (int)(obj.getDouble("precipProbability")*100);
	}
	
	public static int getWindSpeed(JSONObject obj) throws JSONException{
		return (int)(obj.getDouble("windSpeed"));
	}
	
	public static int getWindDir(JSONObject obj) throws JSONException{
		return obj.getInt("windBearing");
	}
	
	public static double getPrecip(JSONObject obj) throws JSONException{
		return obj.getDouble("precipIntensity");
	}

	public static void displayWeatherData(JSONObject obj) throws JSONException{
		System.out.print("Date: ");
		System.out.println(getDate(obj));
		System.out.print("High Temperature: ");
		System.out.println(getHighTemp(obj));
		System.out.print("Low Temperature: ");
		System.out.println(getLowTemp(obj));
		System.out.print("Precipitation % Chance: ");
		System.out.println(getPrecipProb(obj) + "%");
		System.out.print("Wind Speed: ");
		System.out.println(getWindSpeed(obj));
		System.out.print("Wind Direction (0 = N, 90 = E, etc): ");
		System.out.println(getWindDir(obj));	
		System.out.print("Precipitation Intensity: ");
		System.out.println(getPrecip(obj));
		System.out.println();
	}
	
	/*public static void main(String args[]) throws JSONException{
		//Currently retrieves the last week of temperatures
		//Uses American units (Fahrenheit, MPH)
		//Dummy comment to test github
		System.out.println("TOMORROW'S FORECAST\n--------------------");
		displayWeatherData(getNextDaysForecast());
		System.out.println("YESTERDAY'S WEATHER\n--------------------");
		displayWeatherData(getPastWeather(System.currentTimeMillis()));
	}*/
}
