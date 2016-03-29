
public class Classifier {
	
	private String name;
	private String dataPoint;
	private String category;
	
	public Classifier(String n, String data){
		name = n;
		dataPoint = data;
		
	}
	//Creates classifications for dataPoint 
	public void classifyAttribute(){
		//Switch statement for each type of data point
				
		
	}
	//possibly unnecessary
	public void createRange(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getDataPoint() {
		return dataPoint;
	}
	public void setDataPoint(String dataPoint) {
		this.dataPoint = dataPoint;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
		

}
