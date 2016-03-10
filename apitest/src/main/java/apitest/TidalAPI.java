package apitest;

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
	public static void main(String args[]) throws JSONException{
		JSONArray a = getTideForecast();
		JSONObject b = getTideDataForTime("2100", a);
		System.out.println("data for 2100 is ");
		//System.out.println(b);
		System.out.println(isolateTideData(b));
	}
}
