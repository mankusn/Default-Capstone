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

	// --- --- --- --- --- --- --- --- Tide API calls
	
	public static String makeTideAPIRequest() throws JSONException{
		
		String APIKey = "c1bceffff953053aa354659b63a67";
		//String latitude = "30.6014";
		//String longitude = "-96.3144"; //College Station, TX
		
		// lesvos coords
		String latitude = "39.3012";
		String longitude = "25.9888";
		
		String request = "http://api.worldweatheronline.com/free/v2/marine.ashx?key=" + APIKey + "&format=JSON&q=" + latitude + "," + longitude;
		return request;
	}
	
	public static JSONArray getTideForecast() throws JSONException{
		String request = makeTideAPIRequest();
		//System.out.println("req:: " + request);
		RestTemplate restTemplate = new RestTemplate();
		String tidePredict = restTemplate.getForObject(request, String.class);
		JSONObject tideObj = new JSONObject(tidePredict);
		//System.out.println(tideObj);
		//System.out.println(tideObj.get("data"));
		//System.out.println(tideObj.get("query"));
		
		JSONObject data = new JSONObject(tideObj.getString("data"));
		//System.out.println(data);
		//System.out.println(data.getJSONArray("weather"));
		JSONArray dat = (JSONArray) data.getJSONArray("weather");
		JSONObject temp = dat.getJSONObject(0);
		JSONArray tempre = temp.getJSONArray("hourly");
		//System.out.println(dat);
		//System.out.println(temp);
		//System.out.println(tempre);
		/*Vector<String> a = new Vector<String>();
		for(int i = 0; i < dat.length(); i++){
			a.addElement(temp.getString("swellDir"));
			System.out.println("count " + i);
			System.out.println(temp.getString("swellDir"));
		}
		
		
		for(int i = 0; i < a.size(); i++){
			//System.out.println(a.elementAt(i));
		} */
		
		
		
		//JSONArray arr = (JSONArray) data.getJSONArray("request");
		//System.out.println(arr.get(0));
		//System.out.println(data.get("request"));
		//JSONArray arr = (JSONArray) tideObj.get("data");
		//JSONObject newobj = (JSONObject) arr.get(0);
		
		// KEYS
		// waterTemp_F
		// visibility
		// swellDir
		// swellHeight_m
		// winddir16Point
		// swellPeriod_secs
		//tideObj.getString("waterTemp_F");
		//JSONObject waterTemp = new JSONObject(tideObj.get("waterTemp_F"));
		
		System.out.println(" ending test ");
		
		System.out.println(" returning data for times (24 hr format):");
		for(int i = 0; i < tempre.length(); i++){
			String time = tempre.getJSONObject(i).getString("time");
			System.out.println(time);
		}
		
		return tempre;
	}
	
	public static JSONObject getTideDataForTime(String time, JSONArray data) throws JSONException{
		if(time == "0"){
			JSONObject ret = data.getJSONObject(0);
			return ret;
		}
		else if(time == "300"){
			JSONObject ret = data.getJSONObject(1);
			return ret;
		}
		else if(time == "600"){
			JSONObject ret = data.getJSONObject(2);
			return ret;
		}
		else if(time == "900"){
			JSONObject ret = data.getJSONObject(3);
			return ret;
		}
		else if(time == "1200"){
			JSONObject ret = data.getJSONObject(4);
			return ret;
		}
		else if(time == "1500"){
			JSONObject ret = data.getJSONObject(5);
			return ret;
		}
		else if(time == "1800"){
			JSONObject ret = data.getJSONObject(6);
			return ret;
		}
		else if(time == "2100"){
			JSONObject ret = data.getJSONObject(7);
			return ret;
		}
		
		return new JSONObject();
	}
	
	public static JSONObject isolateTideData(JSONObject input) throws JSONException{
		
		JSONObject output = new JSONObject();
		// DESIRED KEYS
		// precipMM
		// visibility
		// swellDir
		// waterTemp_F
		// swellHeight_m
		// winddirDegree
		// windspeedMiles
		// swellPeriod_secs
		// winddir16Point
		
		output.append("precipMM", input.getString("precipMM"));
		output.append("visibility", input.getString("visibility"));
		output.append("swellDir", input.getString("swellDir"));
		output.append("waterTemp_F", input.getString("waterTemp_F"));
		output.append("swellHeight_m", input.getString("swellHeight_m"));
		output.append("winddirDegree", input.getString("winddirDegree"));
		output.append("windspeedMiles", input.getString("windspeedMiles"));
		output.append("swellPeriod_secs", input.getString("swellPeriod_secs"));
		output.append("winddir16Point", input.getString("winddir16Point"));
		
		return output;
	}
	
	
	// --- --- --- --- --- --- --- --- End Tide API calls
	
	//Case for future weather data where no date is required
	public static String makeAPIRequest(String date) throws JSONException{
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
		//System.out.println("NEXT WEEK'S FORECAST\n--------------------");
		//nextWeeksForecast();
		//System.out.println("LAST WEEK'S WEATHER\n-------------------");
		//lastWeeksForecast();
		
		JSONArray a = getTideForecast();
		JSONObject b = getTideDataForTime("2100", a);
		System.out.println("data for 2100 is ");
		//System.out.println(b);
		System.out.println(isolateTideData(b));
		
	}
}
