// from http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class readCSV {

  public static void main(String[] args) {

	readCSV obj = new readCSV();
	obj.run();

  }

  public void run() {

	//String csvFile = "/Users/mkyong/Downloads/GeoIPCountryWhois.csv";
	String csvFile = "myFile.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	
	Vector<Vector<String>> datTable = new Vector<Vector<String>>();

	try {

		br = new BufferedReader(new FileReader(csvFile));
		
		
		
		while ((line = br.readLine()) != null) {

		        // use comma as separator
			String[] row  = line.split(cvsSplitBy);

			datTable.add(row);
			
    
			    // store array of datapoints into vector of vectors
			    // FOR TIDE DATA cols labeled as 
			    // | date | swell dir | swell height (ft) | water temp CELCIUS DONT USE | swell period DONT USE | swell period per secs | water temp F |
			    
			    // FOR REFUGEE DATA cols labeled as
			    // | date | zone | refugee count | 
			    
			    // for each element 
		}
		
		for(int i = 0; i < datTable.size(); i++){
			for(int j = 0; j < datTable[i].size(); j++){
				// insert elements from table into DB
				// fill this out 
			}
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

	System.out.println("CSV file readin complete. ");
  }

}
