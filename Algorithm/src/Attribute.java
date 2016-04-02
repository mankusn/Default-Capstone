import java.util.*;


public class Attribute {
	
	private double infoGain;
	private double min;
	private double max;
	private HashMap<String,Double> classesEntropy;
	
	
	
	public Attribute(String n){
		name = n;
		classesEntropy = new HashMap<String,Double>();
		int numofClasses = 0;
		if(name == "windDir"||name=="swellDir"){
			numofClasses = 4;
		}else if(name=="outlook"){
			numofClasses = 10;
		}else{
			numofClasses = 3;
		}
		for(int i =1;i<=numofClasses;i++){
			classesEntropy.put(Integer.toString(i), 0.0);
		}
	}
	
	
	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	private String name;
	private Vector<Attribute> children;
	
	

	public HashMap<String,Double> getEntropyValues() {
		
		return classesEntropy;
	}

	public void setEntropy(String className, double entropy) {
		this.classesEntropy.put(className, entropy);
	}

	public double getInfoGain() {
		return infoGain;
	}

	public void setInfoGain(double infoGain) {
		this.infoGain = infoGain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
