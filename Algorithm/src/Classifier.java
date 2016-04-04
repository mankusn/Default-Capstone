
public class Classifier {
	
	private String name;

	private int dataPoint;
	private String preClassifyData;
	private String category;
	private int classifierCount;
	
	
	public Classifier(){
		
	}
	public Classifier(String n, String data){
		name = n;
		setPreClassifyData(data);
		
	}
	//Creates classifications for dataPoint 
	public void classifyAttribute(double min, double max){
		if (this.name == "highTemp") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else if (this.name == "lowTemp") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else if (this.name == "precipProb") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else if (this.name == "outlook") {
			//clear-day = 1, clear-night = 2, rain = 3, snow = 4, sleet = 5, 
			//wind = 6, fog = 7, cloudy = 8, partly-cloudy-day = 9, partly-cloudy-night = 10
			this.dataPoint = classifyOutlook();
		} else if (this.name == "windSpeed") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else if (this.name == "windDir") {
			this.dataPoint = classifyFourCategories();
		} else if (this.name == "precipMM") {
			this.dataPoint = classifyThreeCategoriesDouble(min, max);
		} else if (this.name == "visibility") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else if (this.name == "swellDir") {
			this.dataPoint = classifyFourCategories();
		} else if (this.name == "waterTemp_F") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else if (this.name == "swellHeight_m") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else if (this.name == "windDirDegree") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else if (this.name == "windSpeedMiles") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else if (this.name == "swellPeriod_secs") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else if (this.name == "windDir16Point") {
			//N, NNE, NE, ENE, E, ESE, SE,
			//SSE, S, SSW, SW, WSW, W, WNW, NW, NNW
			this.dataPoint = classifyCardinalDirection();
		} else if (this.name == "boatCount") {
			this.dataPoint = classifyThreeCategories(min, max);
		} else {
			System.out.println("ERROR: NAME NOT FOUND IN DATABASE OF CLASSIFIERS");
		}
	}
	
	private int classifyCardinalDirection() {
		int count = 0;
		this.setClassifierCount(16);
		if (this.preClassifyData == "N") {
			count = 1;
		} else if (this.preClassifyData == "NNE") {
			count = 2;
		} else if (this.preClassifyData == "NE") {
			count = 3;
		} else if (this.preClassifyData == "ENE") {
			count = 4;
		} else if (this.preClassifyData == "E") {
			count = 5;
		} else if (this.preClassifyData == "ESE") {
			count = 6;
		} else if (this.preClassifyData == "SE") {
			count = 7;
		} else if (this.preClassifyData == "SSE") {
			count = 8;
		} else if (this.preClassifyData == "S") {
			count = 9;
		} else if (this.preClassifyData == "SSW") {
			count = 10;
		} else if (this.preClassifyData == "SW") {
			count = 11;
		} else if (this.preClassifyData == "WSW") {
			count = 12;
		} else if (this.preClassifyData == "W") {
			count = 13;
		} else if (this.preClassifyData == "WNW") {
			count = 14;
		} else if (this.preClassifyData == "NW") {
			count = 15;
		} else if (this.preClassifyData == "NNW") {
			count = 16;
		}
		return count;
	}
	
	//This function goes through the 10 possible outlook strings and converts them
	//each to a number value, 10 total classifications
	private int classifyOutlook() {
		int dataValue = 0;
		this.setClassifierCount(10);
		if (this.preClassifyData == "clear-day") {
			dataValue = 1;
		} else if (this.preClassifyData == "clear-night") {
			dataValue = 2;
		} else if (this.preClassifyData == "rain") {
			dataValue = 3;
		} else if (this.preClassifyData == "snow") {
			dataValue = 4;
		} else if (this.preClassifyData == "sleet") {
			dataValue = 5;
		} else if (this.preClassifyData == "wind") {
			dataValue = 6;
		} else if (this.preClassifyData == "fog") {
			dataValue = 7;
		} else if (this.preClassifyData == "cloudy") {
			dataValue = 8;
		} else if (this.preClassifyData == "partly-cloudy-day") {
			dataValue = 9;
		} else if (this.preClassifyData == "partly-cloudy-night") {
			dataValue = 10;
		} else {
			System.out.println("ERROR: OUTLOOK VALUE DOES NOT MATCH WITH ANY KNOWN VALUES");
		}
		return dataValue;
	}
	

	
	//This function is for any classification that goes into 3 categories, low, medium
	//and high where low = 1, medium = 2, and high = 3. Must be double value input
	private int classifyThreeCategoriesDouble(double minValue, double maxValue) {
		int count = 0;
		double dataConversion = Double.parseDouble(this.preClassifyData);
		this.setClassifierCount(3);
		double currentValue = minValue;
		while (currentValue <= dataConversion) {
			currentValue += (maxValue - minValue)/classifierCount;
			++count;
		}
		return count;
	}
	
	//This function is for any classification that goes into 3 categories, low, medium
	//and high where low = 1, medium = 2, and high = 3. Must be integer value input
	private int classifyThreeCategories(double minValue, double maxValue) {
//		System.out.println("FOR: "+ this.name.toUpperCase());
		int count = 0;
		int dataConversion = Integer.parseInt(this.preClassifyData);
		this.setClassifierCount(3);
		double currentValue = minValue;
		double change = 0;
//		System.out.println(" Min: "+minValue+" Max: "+maxValue);
		while (currentValue <= dataConversion && currentValue != maxValue) {
			
			change= (maxValue - minValue)/classifierCount;
			currentValue += change;
			++count;
//			System.out.println("Count: "+ count );
//			System.out.println("DataConversion: "+dataConversion);
//			System.out.println("Current Value: "+currentValue);
		}
//		System.out.println("Being Returned!: "+count);
		return count;
	}
	
	//This Function is used to classify into four categories, mainly used for cases
	//when there is a set of radial degrees from 0-359, divided into sets of 90
	private int classifyFourCategories() {
		int count = 0;
		int dataConversion = Integer.parseInt(this.preClassifyData);
		this.setClassifierCount(4);
		if (dataConversion >= 0 && dataConversion < 90) {
			count = 1;
		} else if (dataConversion >= 90 && dataConversion < 180) {
			count = 2;
		} else if (dataConversion >= 180 && dataConversion < 270) {
			count = 3;
		} else if (dataConversion >= 270) {
			count = 4;
		} else {
			System.out.println("ERROR: DEGREE VALUE NOT IN 4 CATEGORIES DEFINED");
		}
		return count;
	}
	
	//IMPORTANT
	public void createRange(Attribute attribute){
		//DEFINITELY NECESSARY BUT MOST LIKELY MUST GO IN THE MAIN CLASS
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public int getDataPoint() {
		return dataPoint;
	}
	public void setDataPoint(int dataPoint) {
		this.dataPoint = dataPoint;
	}
	public int getCategory() {
		return dataPoint;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getClassifierCount() {
		return classifierCount;
	}

	public void setClassifierCount(int classifierCount) {
		this.classifierCount = classifierCount;
	}
	public String getPreClassifyData() {
		return preClassifyData;
	}
	public void setPreClassifyData(String preClassifyData) {
		this.preClassifyData = preClassifyData;
	}

}
