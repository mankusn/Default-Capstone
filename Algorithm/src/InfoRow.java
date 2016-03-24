import java.util.*;


public class InfoRow {
	
	//Create variable for each attribute
	private Classifier windSpeed;
	private Classifier temp;
	private Classifier outlook;
	//...
	
	public InfoRow(int windsp, int t, int out /*For each attribute*/){
		windSpeed = new Classifier("Wind Speed", windsp);
		temp = new Classifier("Temperature", t);
		//...
		
	}
	//Getters & Setters
	
	//Classify every attribute
	public void classifyRow(){
		
	}
	
	
		

}

