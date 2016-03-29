import java.util.*;
import java.util.Map.Entry;


public class InfoRow {
	
	//Create variable for each attribute
	private HashMap<String,Classifier> infoRow;
	
	//...
	
	public InfoRow(HashMap<String, String> dataSet){
		
		infoRow = new HashMap<String,Classifier>();
		
		for(Entry<String,String> entry:dataSet.entrySet()){
			
			infoRow.put(entry.getKey(),new Classifier(entry.getKey(),entry.getValue()));
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

