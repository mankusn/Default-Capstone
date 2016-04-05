/*Copyright 2016 Team Default TAMU CSCE 482 Dr. Murphy*/
import java.util.*;
public class InfoRow {
	
	//Create variable for each attribute
	private HashMap<String,String> infoRow;
	//...
	
	public InfoRow(HashMap<String, String> dataSet){
		

		infoRow = dataSet;
		
		
		
		
	}
	
	//Getters & Setters
	public double[] getValues(){
		double [] values = new double[infoRow.size()];
		int count = 0;
		for(String name:infoRow.keySet()){
			values[count] = Double.parseDouble(infoRow.get(name));
			count++;
		}
		return values;
	}
	public double[] getRawValues(){
		double [] values = new double[infoRow.size()];
		int count = 0;
		for(String name:infoRow.keySet()){
			if (name=="boatCount")
				continue;
			values[count] = Double.parseDouble(infoRow.get(name));
			count++;
		}
		return values;
	}
	public HashMap<String,String>getInfoRow(){
		return this.infoRow;
	}




	
	
	
		

}

