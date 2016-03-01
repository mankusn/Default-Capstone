package apitest;

//Example program to test retrieving temperature from the forecast.io API

import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Apitest{
	public static String unixTimeToDate(long unix){
		//http://stackoverflow.com/questions/17432735/convert-unix-time-stamp-to-date-in-java
		Date date = new Date(unix);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
	public static JSONObject getFullDayWeather(String date) throws JSONException{
		//Creates the API request string
		String APIKey = "c62d91cb9c000638716e55cbc478330f"; //Replace this if we run out of requests on an account
		String latitude = "30.6014";
		String longitude = "-96.3144"; //College Station, TX
		String request = "https://api.forecast.io/forecast/" + APIKey + "/" + latitude + "," +
						longitude + "," + date;
		
		//GETs weather data as a String and converts it to a JSONObject 
		RestTemplate restTemplate = new RestTemplate();
		String daysWeather = restTemplate.getForObject(request,String.class);
		JSONObject weatherObj = new JSONObject(daysWeather);
		
		//We don't need the current or hourly report, just data for the whole day.
		//This data is presented as a JSONObject that contains a JSONArray with one element
		JSONObject fakeDailyObj = weatherObj.getJSONObject("daily");
		JSONArray arr = (JSONArray) fakeDailyObj.get("data");
		JSONObject dailyObj = (JSONObject) arr.get(0);
		return dailyObj;
	}
	
	public static void main(String args[]) throws JSONException{
		//Currently retrieves the last week of temperatures
		//Uses American units (Fahrenheit, MPH)
		long currentTime = System.currentTimeMillis();
		for(int index=0; index<7; index++){
			String requestTime = unixTimeToDate(currentTime);
			JSONObject dailyObj = getFullDayWeather(requestTime);
			
			System.out.print("Date: ");
			System.out.println(requestTime);
			System.out.print("High Temperature: ");
			System.out.println(dailyObj.get("temperatureMax"));
			System.out.print("Low Temperature: ");
			System.out.println(dailyObj.get("temperatureMin"));
			System.out.print("Precipitation % Chance: ");
			System.out.println((dailyObj.getDouble("precipProbability"))*100 + "%");
			System.out.print("Outlook: ");
			System.out.println(dailyObj.get("summary"));
			System.out.print("Wind Speed: ");
			System.out.println(dailyObj.get("windSpeed"));
			System.out.print("Wind Direction (0 = N, 90 = E, etc): ");
			System.out.println(dailyObj.get("windBearing"));
			
			System.out.println();
			currentTime-=86400000; //86,400,000 milliseconds in a day
		}
	}
}