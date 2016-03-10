package apitest;

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
	public static String unixTimeToDate(long unix){
		//http://stackoverflow.com/questions/17432735/convert-unix-time-stamp-to-date-in-java
		Date date = new Date(unix);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
	//Case for future weather data where no date is required
	public static String makeAPIRequest() throws JSONException{
		return makeAPIRequest("");
	}

	//Case for past weather data where a date is required
	public static String makeAPIRequest(String date){
		//Creates the API request string
		String APIKey = "c62d91cb9c000638716e55cbc478330f"; //Replace this if we run out of requests on an account
		String latitude = "30.6014";
		String longitude = "-96.3144"; //College Station, TX
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
	
	public static JSONObject getPastFullDayWeather(String date) throws JSONException{
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
	
	public static void displayWeatherData(JSONObject obj) throws JSONException{
		System.out.print("Date: ");
		System.out.println(unixTimeToDate((obj.getLong("time")*1000)));
		System.out.print("High Temperature: ");
		System.out.println(obj.get("temperatureMax"));
		System.out.print("Low Temperature: ");
		System.out.println(obj.get("temperatureMin"));
		System.out.print("Precipitation % Chance: ");
		System.out.println((int)((obj.getDouble("precipProbability"))*100) + "%");
		System.out.print("Outlook: ");
		System.out.println(obj.get("summary"));
		System.out.print("Wind Speed: ");
		System.out.println(obj.get("windSpeed"));
		System.out.print("Wind Direction (0 = N, 90 = E, etc): ");
		System.out.println(obj.get("windBearing"));		
		System.out.println();
	}
	
	public static void lastWeeksForecast() throws JSONException{
		long currentTime = System.currentTimeMillis();
		for(int index = 0; index < 7; index++){
			String requestTime = unixTimeToDate(currentTime);
			JSONObject dailyObj = getPastFullDayWeather(requestTime);
			displayWeatherData(dailyObj);
			currentTime-=86400000; //86,400,000 milliseconds in a day
		}
	}
	
	public static void nextWeeksForecast() throws JSONException{
		String request = makeAPIRequest();
		JSONObject weatherObj = makeAPICall(request);
		
		//Like the past data, each day's data is a JSONObject inside a JSONArray inside a JSONObject
		//In this case, there are 8 objects inside the array, with each one representing a day
		JSONObject dailyForecastObj = weatherObj.getJSONObject("daily");
		JSONArray days = (JSONArray) dailyForecastObj.get("data");
		for(int index = 0; index < 8; ++index){
			JSONObject dayObj = (JSONObject) days.get(index);
			displayWeatherData(dayObj);
		}
	}
	
	public static void main(String args[]) throws JSONException{
		//Currently retrieves the last week of temperatures
		//Uses American units (Fahrenheit, MPH)
		System.out.println("NEXT WEEK'S FORECAST\n--------------------");
		nextWeeksForecast();
		System.out.println("LAST WEEK'S WEATHER\n-------------------");
		lastWeeksForecast();
	}
}
