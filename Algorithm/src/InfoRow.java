import java.util.*;
import java.util.Map.Entry;


public class InfoRow {
	
	//Create variable for each attribute
	private HashMap<String,Classifier> infoRow;
	
	//...
	
	public InfoRow(HashMap<String, String> dataSet){
		
		infoRow = new HashMap<String,Classifier>();
		
		for(String name:dataSet.keySet()){
			infoRow.put(name,new Classifier(name,dataSet.get(name)));
		}
		
		
		
	}
	
	
	//Getters & Setters
	
	public HashMap<String,Classifier>getInfoRow(){
		return this.infoRow;
	}

	//Classify every attribute
	public void classifyRow(HashMap<String,Attribute> attributes){
		
		for(String key:attributes.keySet()){
			this.infoRow.get(key).classifyAttribute(attributes.get(key).getMin(),attributes.get(key).getMax());
		}
	}
	
	
		

}

