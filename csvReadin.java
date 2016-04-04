// from http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

package com.mkyong.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCVS {

  public static void main(String[] args) {

	ReadCVS obj = new ReadCVS();
	obj.run();

  }

  public void run() {

	//String csvFile = "/Users/mkyong/Downloads/GeoIPCountryWhois.csv";
	String csvFile = "myFile.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

		        // use comma as separator
			String[] country = line.split(cvsSplitBy);

		/*	System.out.println("Country [code= " + country[4] 
                                 + " , name=" + country[5] + "]");
    */
    
    // store array of datapoints into vector of vectors
    // FOR TIDE DATA cols labeled as 
    // | date | swell dir | swell height (ft) | water temp CELCIUS DONT USE | swell period DONT USE | swell period per secs | water temp F |
    
    // FOR REFUGEE DATA cols labeled as
    // | date | zone | refugee count | 
		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("Done");
  }

}
