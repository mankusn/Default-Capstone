package com.capstone.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.capstone.model.Tidal;

public class CSVParser {
	
	public static List<Tidal> parseCSV() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(
	            "C:\\Users\\Nick\\Downloads\\data.csv"));
		
	    // read file line by line
	    String line = null;
	    Scanner scanner = null;
	    int index = 0;
	    List<Tidal> tideList = new ArrayList<Tidal>();
		line = reader.readLine();
	    while ((line = reader.readLine()) != null) {
	        Tidal tide = new Tidal(); 
	        scanner = new Scanner(line);
	        scanner.useDelimiter(",");
	        while (scanner.hasNext()) {
	            String data = scanner.next();
	            if (index == 0)
					tide.setSwellDir(Integer.parseInt(data));
	            else if (index == 1)
	                tide.setSwellHeight(Integer.parseInt(data));
	            else if (index == 4)
	                tide.setSwellPeriod(Integer.parseInt(data));
	            else if (index == 5)
	                tide.setWaterTempF((int)Double.parseDouble(data));
				else if (index == 12)
					tide.setBoatCount1(Integer.parseInt(data));
				else if (index == 13)
					tide.setBoatCount2(Integer.parseInt(data));
				else if (index == 14)
					tide.setBoatCount3(Integer.parseInt(data));
				else if (index == 15)
					tide.setBoatCount4(Integer.parseInt(data));
				else if (index == 16)
					tide.setTotal_boatcount(Integer.parseInt(data));
	            index++;
	        }
	        index = 0;
	        tideList.add(tide);
	    }
	     
	    //close reader
	    reader.close();
	     
	    //System.out.println(tideList);
	    return tideList; 
	}
	
     
}