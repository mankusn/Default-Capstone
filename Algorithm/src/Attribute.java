import java.util.Vector;


public class Attribute {
	
	private double entropy;
	private double infoGain;
	private double min;
	private double max;
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
	
	public Attribute(String n){
		name = n;
	}

	public double getEntropy() {
		
		return entropy;
	}

	public void setEntropy(double entropy) {
		this.entropy = entropy;
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
