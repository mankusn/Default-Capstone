package apitest;

//Example program to test retrieving temperature from the forecast.io API

import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.json.JSONException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Apitest{
	public static String unixTimeToDate(long unix){
		Date date = new Date(unix);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-6"));
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	
	public static JSONObject getCurrentWeather(String date) throws JSONException{
		//Creates the API request string
		String APIKey = "c62d91cb9c000638716e55cbc478330f"; //Replace this if we run out of requests on an account
		String latitude = "30.6014";
		String longitude = "-96.3144"; //College Station, TX
		String request = "https://api.forecast.io/forecast/" + APIKey + "/" + latitude + "," +
						longitude + "," + date;
		
		//GETs weather data as a String and converts it to a JSONObject called weatherObj. 
		//Because the temperature is contained inside another JSONObject within weatherObj, we must
		//get the "currently" report and parse that to retrieve temperature.
		RestTemplate restTemplate = new RestTemplate();
		String daysWeather = restTemplate.getForObject(request,String.class);
		JSONObject weatherObj = new JSONObject(daysWeather); 
		String currently = weatherObj.get("currently").toString();
		JSONObject currentlyObj = new JSONObject(currently);
		return currentlyObj;
	}
	
	public static void main(String args[]) throws JSONException{
		//Currently retrieves the last week of temperatures
		//http://stackoverflow.com/questions/17432735/convert-unix-time-stamp-to-date-in-java
		long currentTime = System.currentTimeMillis();
		for(int index=0; index<7; index++){
			String requestTime = unixTimeToDate(currentTime);
			JSONObject currentlyObj = getCurrentWeather(requestTime);
			System.out.print("Date: ");
			System.out.print(requestTime);
			System.out.print("\tTemperature: ");
			System.out.println(currentlyObj.get("temperature") + "\n");
			currentTime-=86400000; //86,400,000 milliseconds in a day
		}
	}
}