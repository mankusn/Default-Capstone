package apitest;

/*TODO: 1) Get water data
		2) Integrate with database
		3) Get boat data?
*/
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
	
	public static String makeAPIRequest() throws JSONException{
		return makeAPIRequest("");
	}
	
	// --- --- --- --- --- --- --- --- Tide API calls
	
	public static String makeTideAPIRequest() throws JSONException{
		
		String APIKey = "c1bceffff953053aa354659b63a67";
		String latitude = "30.6014";
		String longitude = "-96.3144"; //College Station, TX
		String request = "http://api.worldweatheronline.com/free/v2/marine.ashx?key=" + APIKey + "&format=JSON&q=" + latitude + "," + longitude;
		return request;
	}
	
	public static JSONObject getTideForecast() throws JSONException{
		String request = makeTideAPIRequest();
		//System.out.println("req:: " + request);
		RestTemplate restTemplate = new RestTemplate();
		String tidePredict = restTemplate.getForObject(request, String.class);
		JSONObject tideObj = new JSONObject(tidePredict);
		
		System.out.println(" ending test ");
		
		return tideObj;
	}
	
	// --- --- --- --- --- --- --- --- End Tide API calls
	
	public static String makeAPIRequest(String date) throws JSONException{
		String APIKey = "c62d91cb9c000638716e55cbc478330f"; //Replace this if we run out of requests on an account
		String latitude = "30.6014";
		String longitude = "-96.3144"; //College Station, TX
		String request = "https://api.forecast.io/forecast/" + APIKey + "/" + latitude + "," +
						longitude;
		if(!date.equals(""))
			request+= "," + date;
		return request;
	}
	
	public static JSONObject getPastFullDayWeather(String date) throws JSONException{
		//Creates the API request string
		String request = makeAPIRequest(date);
		
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
	
	public static void lastWeeksForecast() throws JSONException{
		long currentTime = System.currentTimeMillis();
		for(int index = 0; index < 7; index++){
			String requestTime = unixTimeToDate(currentTime);
			JSONObject dailyObj = getPastFullDayWeather(requestTime);
			
			System.out.print("Date: ");
			System.out.println(requestTime);
			System.out.print("High Temperature: ");
			System.out.println(dailyObj.get("temperatureMax"));
			System.out.print("Low Temperature: ");
			System.out.println(dailyObj.get("temperatureMin"));
			System.out.print("Precipitation % Chance: ");
			System.out.println((int)((dailyObj.getDouble("precipProbability"))*100) + "%");
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
	
	public static void nextWeeksForecast() throws JSONException{
		String request = makeAPIRequest();
		
		RestTemplate restTemplate = new RestTemplate();
		String forecast = restTemplate.getForObject(request, String.class);
		JSONObject weatherObj = new JSONObject(forecast);
		
		JSONObject dailyForecastObj = weatherObj.getJSONObject("daily");
		JSONArray days = (JSONArray) dailyForecastObj.get("data");
		for(int index = 0; index < 8; ++index){
			JSONObject dayObj = (JSONObject) days.get(index);
			System.out.print("Date: ");
			System.out.println(unixTimeToDate((dayObj.getLong("time")*1000)));
			System.out.print("High Temperature: ");
			System.out.println(dayObj.get("temperatureMax"));
			System.out.print("Low Temperature: ");
			System.out.println(dayObj.get("temperatureMin"));
			System.out.print("Precipitation % Chance: ");
			System.out.println((int)((dayObj.getDouble("precipProbability"))*100) + "%");
			System.out.print("Outlook: ");
			System.out.println(dayObj.get("summary"));
			System.out.print("Wind Speed: ");
			System.out.println(dayObj.get("windSpeed"));
			System.out.print("Wind Direction (0 = N, 90 = E, etc): ");
			System.out.println(dayObj.get("windBearing"));
			System.out.println();
		}
	}
	
	public static void main(String args[]) throws JSONException{
		//Currently retrieves the last week of temperatures
		//Uses American units (Fahrenheit, MPH)
		//System.out.println("NEXT WEEK'S FORECAST\n--------------------");
		//nextWeeksForecast();
		//System.out.println("LAST WEEK'S WEATHER\n-------------------");
		//lastWeeksForecast();
		JSONObject a = getTideForecast();
		System.out.println(a);
		
	}
}