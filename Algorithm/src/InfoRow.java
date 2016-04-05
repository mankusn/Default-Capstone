/*Copyright 2016 Team Default TAMU CSCE 482 Dr. Murphy*/
import java.util.*;
public class InfoRow {
	
	//Create variable for each attribute
	private HashMap<String,String> infoRow;
	//...
	
	public InfoRow(HashMap<String, String> dataSet){
		

		infoRow = dataSet;
		
		
		
		
	}
	
	//Returns Raw double values for Java-ML Instance. Excludes boatCount
	public double[] getRawValues(){
		double [] values = new double[this.infoRow.size()-1];
		int count = 0;
		for(String name:new TreeSet<String>(this.infoRow.keySet())){
			if (name=="boatCount")
				continue;
			values[count] = Double.parseDouble(this.infoRow.get(name));
			count++;
		}
		return values;
	}
	public HashMap<String,String>getInfoRow(){
		return this.infoRow;
	}




	
	
	
		

}

