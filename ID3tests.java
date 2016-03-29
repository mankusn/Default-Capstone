package api;

import org.json.JSONObject;
import static org.junit.Assert.*;
import org.json.JSONException;
import org.junit.*;
import java.util.*;


public class ID3tests{
  
	
	// data for tests 1 and 2
	Vector<Integer> list1 = {1, 2, 3, 4, 5};
	list<Integer> list2 = {5, 4, 3, 2, 1};
	
	// data for test 4
	// dataset == vector of InfoRow
	// 
		Vector<InfoRow> dataSet = new Vector<InfoRow> ();
		HashMap<String,String> rawData1 = new HashMap<String,String>();
		HashMap<String,String> rawData2 = new HashMap<String,String>();
		HashMap<String,String> rawData3 = new HashMap<String,String>();
		Vector<Attribute> attributes = new Vector<Attribute>();
		
		attributes.add(new Attribute("WindSpeed"));
		attributes.add(new Attribute("WindDir"));
		attributes.add(new Attribute("Temp"));
		
		rawData1.put("WindSpeed", "0");
		rawData1.put("WindDir", "0");
		rawData1.put("Temp", "0");
		InfoRow inforow1 = new InfoRow(rawData1);
		dataSet.add(inforow1);
		
		rawData2.put("WindSpeed", "20");
		rawData2.put("WindDir", "180");
		rawData2.put("Temp", "50");
		InfoRow inforow2 = new InfoRow(rawData2);
		dataSet.add(inforow2);
		
		rawData3.put("WindSpeed", "20");
		rawData3.put("WindDir", "180");
		rawData3.put("Temp", "50");
		InfoRow inforow3 = new InfoRow(rawData3);
		dataSet.add(inforow3);
	
  
  @test
  public void vectorSumTest(){
    assertEquals(15, sum(list1));
  }
  
  @test
  public void listSumTest(){
    assertEquals(15, sum(list2));
  }
  
  @test
  public void hasCatTest(){
    // need internal attributes 
  }
	
	
	
}
