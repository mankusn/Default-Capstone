package apitest;

//Example program to test retrieving temperature from the forecast.io API

import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

public class Apitest{
	public static void main(String args[]) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		
		//GETs weather data as a String and converts it to a JSONObject called weatherObj. 
		//Because the temperature is contained inside another JSONObject within weatherObj, we must
		//get the "currently" report and parse that to retrieve temperature.
		String daysWeather = restTemplate.getForObject("https://api.forecast.io/forecast/c62d91cb9c000638716e55cbc478330f/30.6014,-96.3144,2015-02-25T12:00:00",String.class);
		JSONObject weatherObj = new JSONObject(daysWeather);
		String currently = (weatherObj.get("currently").toString());
		JSONObject currentObj = new JSONObject(currently);
		
		System.out.println(weatherObj.get("currently"));
		System.out.println(currentObj.get("temperature"));
		System.out.println(weatherObj);
	}
}
