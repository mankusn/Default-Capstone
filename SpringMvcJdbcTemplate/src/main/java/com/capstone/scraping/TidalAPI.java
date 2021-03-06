package com.capstone.scraping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

public class TidalAPI {
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
		
		
		JSONObject data = new JSONObject(tideObj.getString("data"));
		
		JSONArray dat = (JSONArray) data.getJSONArray("weather");
		JSONObject temp = dat.getJSONObject(0);
		JSONArray tempre = temp.getJSONArray("hourly");
		
		
		//System.out.println(" ending test ");
		
		//System.out.println(" returning data for times (24 hr format):");
		//for(int i = 0; i < tempre.length(); i++){
			//String time = tempre.getJSONObject(i).getString("time");
			//System.out.println(time);
		//}
		
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
	
	public static double getPrecipMM(JSONObject input) throws JSONException{
		String precipmm = input.getString("precipMM");
		precipmm = precipmm.replaceAll("[^\\d.]", "");
		return Double.parseDouble(precipmm);
	}
	
	public static int getVisibility(JSONObject input) throws JSONException{
		String vis = input.getString("visibility");
		vis = vis.replaceAll("[^\\d.]", "");
		return Integer.parseInt(vis);
	}
	
	public static int getSwellDir(JSONObject input) throws JSONException{
		String dir = input.getString("swellDir");
		dir = dir.replaceAll("[^\\d.]", "");
		return Integer.parseInt(dir);
	}
	
	public static int getWaterTemp(JSONObject input) throws JSONException{
		String temp = input.getString("waterTemp_F");
		temp = temp.replaceAll("[^\\d.]", "");
		return Integer.parseInt(temp);
	}
	
	public static double getSwellHeight(JSONObject input) throws JSONException{
		String height = input.getString("swellHeight_m");
		height = height.replaceAll("[^\\d.]", "");
		return Double.parseDouble(height);
	}
	
	public static int getWindDir(JSONObject input) throws JSONException{
		return Integer.parseInt(input.getString("winddirDegree"));
	}
	
	public static int getWindSpeed(JSONObject input) throws JSONException{
		String speed = input.getString("windspeedMiles");
		speed = speed.replaceAll("[^\\d.]", "");
		return Integer.parseInt(speed);
	}
	
	public static double getSwellPeriod(JSONObject input) throws JSONException{
		String per = input.getString("swellPeriod_secs");
		per = per.replaceAll("[^\\d.]", "");
		return Double.parseDouble(per);
	}
	
	public static String getCardinalWindDir(JSONObject input) throws JSONException{
		String dir = input.getString("winddir16Point");
		dir = dir.replaceAll("[^\\d.]", "");
		return input.getString("winddir16Point");
	}
	
	public static void main(String args[]) throws JSONException{
		JSONArray a = getTideForecast();
		JSONObject b = getTideDataForTime("2100", a);
		System.out.println("data for 2100 is ");
		//System.out.println(b);
		System.out.println(isolateTideData(b));
	}
}
