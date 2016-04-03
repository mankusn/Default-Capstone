import java.util.*;


public class Attribute {
	
	
	private double min;
	private double max;
	private HashMap<String,Double> InfoGain;
	private HashMap<String,Double> classesEntropy;
	
	
	
	public Attribute(String n){
		name = n;
		InfoGain = new HashMap<String,Double>();
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attribute other = (Attribute) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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

	public HashMap<String,Double> getInfoGain() {
		return InfoGain;
	}

	public void setInfoGain(String name, double infoGain) {
		this.InfoGain.put(name,infoGain);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
