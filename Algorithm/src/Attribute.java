import java.util.Vector;


public class Attribute {
	
	private int entropy;
	private int infoGain;
	private String name;
	private Vector<Attribute> children;
	
	public Attribute(){
		
	}

	public int getEntropy() {
		return entropy;
	}

	public void setEntropy(int entropy) {
		this.entropy = entropy;
	}

	public int getInfoGain() {
		return infoGain;
	}

	public void setInfoGain(int infoGain) {
		this.infoGain = infoGain;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
