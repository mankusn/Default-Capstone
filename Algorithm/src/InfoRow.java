import java.util.*;
import java.util.Map.Entry;


public class InfoRow {
	
	//Create variable for each attribute
	private HashMap<String,Classifier> infoRow;
	private HashMap<String,Attribute> attributes;
	//...
	
	public InfoRow(HashMap<String, String> dataSet,HashMap<String,Attribute> a){
		
		attributes = a;
		infoRow = new HashMap<String,Classifier>();
		
		for(String name:dataSet.keySet()){
			infoRow.put(name,new Classifier(name,dataSet.get(name)));
		}
		
		
		
	}
	
	
	//Getters & Setters
	
	public HashMap<String,Classifier>getInfoRow(){
		return this.infoRow;
	}

	public HashMap<String, Attribute> getAttributes() {
		return attributes;
	}


	public void setAttributes(HashMap<String, Attribute> attributes) {
		this.attributes = attributes;
	}


	//Classify every attribute
	public void classifyRow(){
		
		for(String key:this.attributes.keySet()){
			this.infoRow.get(key).classifyAttribute(attributes.get(key).getMin(),attributes.get(key).getMax());
		}
	}
	
	
		

}

