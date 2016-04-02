import java.util.*;


public class ID3 {
	
	private Vector<InfoRow> pastData;
	private DecisionTree decisionTree;
	
	public ID3(){
		
	}
	public void generateTree(){
		
	}
	public int sum(Collection<Integer> list){
		int sum = 0;
		for(int num:list){
			sum+=num;
		}
		return sum;
	}
	public double calculateInformationGain(Vector<InfoRow> dataSet,Attribute old, Attribute boatCount){
		HashMap<String,Integer> valFreq = new HashMap<String,Integer>();
		double subSetEntropy =  0.0;
		//Calculate the frequency of each of the values in the target attribute
		for (InfoRow row:dataSet){
			
			if(hasCategory(valFreq,row,old.getName())){
				int oldValue = valFreq.get(old.getName());
				valFreq.put(old.getName(),oldValue+1);
			}else{
				valFreq.put(old.getName(), 1);
			}				
		}
		double valProb = 0.0;
		Vector<InfoRow> dataSubSet = new Vector<InfoRow>();
		//Calculate the sum of the entropy for each subset of records weighted by their probability of occurring in the training set.
		for(String key:valFreq.keySet()){
			valProb = valFreq.get(key)/sum(valFreq.values());
			for(InfoRow record:dataSet){
				if(record.getInfoRow().get(old.getName()).getName()==key){
					dataSubSet.add(record);
				}
			}
//			subSetEntropy +=valProb*calculateEntropy(dataSubSet,boatCount);
		}
		//Subtract the entropy of the chosen attribute from the entropy of the whole data set with respect to the boatCount attribute 
//		double result = (calculateEntropy(dataSet,boatCount)-subSetEntropy);
		return 0.0;
		
	}
	
	public static boolean hasCategory(HashMap<String,Integer> v,InfoRow row, String name){
		return v.containsKey(row.getInfoRow().get(name).getCategory());
	}
	
	
	
	public static void calculateEntropy(Vector<InfoRow> dataSet, Attribute attribute){
//		System.out.println("Calculating entropy for: "+attribute.getName());
		HashMap<String,Integer> valFreq = new HashMap<String,Integer>();
		double entropy =  0.0;
		Classifier currentClass = null;
		String category;
		//Calculate the frequency of each of the values in the target attribute
		for (InfoRow row:dataSet){
			
			currentClass = row.getInfoRow().get(attribute.getName());
//			System.out.println("Class: "+currentClass.getPreClassifyData());
			category = Integer.toString(currentClass.getDataPoint());
//			System.out.println("category: "+ category);
			if (valFreq.containsKey(category)){
				valFreq.put(category, valFreq.get(category) + 1);
			}else{
				valFreq.put(category, 1);
			}
			
			
		}
//		System.out.println("numOfCategories: "+valFreq.keySet().size());
		double notP = 0.0;
		double P = 0.0;
		for(String key: valFreq.keySet()){
			
			double freq = (double)valFreq.get(key);
//			System.out.println("Freq for "+key+": "+freq);
			notP =((dataSet.size()-freq)/dataSet.size())*(Math.log((dataSet.size()-freq)/dataSet.size())/Math.log(2));
			P = (-freq/dataSet.size())*(Math.log(freq/dataSet.size())/Math.log(2));
			entropy = P-notP;
			attribute.getEntropyValues().put(key,entropy);
//			System.out.println("Entropy for "+key+": "+entropy);
			
			entropy = 0.0;
			
		}
		
		//attribute.setEntropy(entropy);
		
		
	}
	//CALL THIS BEFORE ANYTHING
	public static void setRange(Vector<InfoRow> dataSet,HashMap<String,Attribute> attributes){
		//System.out.println("settingRange");
		double max = 0.0;
		double min = Double.MAX_VALUE;
		double temp= 0.0;
		for(String key:attributes.keySet()){
			if(key =="outlook"||key=="windDir16Point")
				continue;
			for(InfoRow row:dataSet){
				temp = Double.parseDouble(row.getInfoRow().get(key).getPreClassifyData());
				if(temp> max)
					max = temp;
				if(temp<min)
					min = temp;
			}
//			System.out.println("Key: "+key+" Min Value: "+min+ " Max Value: "+max);
			attributes.get(key).setMin(min);
			attributes.get(key).setMax(max);
			max = 0.0;
			min = Double.MAX_VALUE;
		}
	}
	
	public static void classifyDataSet(Vector<InfoRow> dataSet, HashMap<String, Attribute> attributes){
		for(InfoRow row:dataSet){
			//System.out.println("getting row");
			row.classifyRow(attributes);
		}
	}
	
	public static void main(String[] args){
		
		Vector<InfoRow> dataSet = new Vector<InfoRow> ();
		HashMap<String,String> rawData1 = new HashMap<String,String>();
		HashMap<String,String> rawData2 = new HashMap<String,String>();
		HashMap<String,String> rawData3 = new HashMap<String,String>();
		HashMap<String,String> rawData4 = new HashMap<String,String>();
		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
		
		attributes.put("windSpeed",new Attribute("windSpeed"));
		attributes.put("windDir",new Attribute("windDir"));
		attributes.put("highTemp",new Attribute("highTemp"));
		attributes.put("boatCount",new Attribute("boatCount"));
		
		rawData1.put("windSpeed", "0");
		rawData1.put("windDir", "0");
		rawData1.put("highTemp", "0");
		rawData1.put("boatCount", "0");
		InfoRow inforow1 = new InfoRow(rawData1);
		dataSet.add(inforow1);
		
		rawData2.put("windSpeed", "20");
		rawData2.put("windDir", "90");
		rawData2.put("highTemp", "50");
		rawData2.put("boatCount", "3");
		InfoRow inforow2 = new InfoRow(rawData2);
		dataSet.add(inforow2);
		
		rawData3.put("windSpeed", "50");
		rawData3.put("windDir", "180");
		rawData3.put("highTemp", "100");
		rawData3.put("boatCount", "6");
		InfoRow inforow3 = new InfoRow(rawData3);
		dataSet.add(inforow3);
		
		rawData4.put("windSpeed", "40");
		rawData4.put("windDir", "360");
		rawData4.put("highTemp", "75");
		rawData4.put("boatCount", "10");
		InfoRow inforow4 = new InfoRow(rawData4);
		dataSet.add(inforow4);
		
		setRange(dataSet,attributes);
		System.out.println("Classify Data Set");
		classifyDataSet(dataSet,attributes);
		System.out.println("Calculate Entropy");

		calculateEntropy(dataSet,attributes.get("windSpeed"));
		calculateEntropy(dataSet,attributes.get("windDir"));
		calculateEntropy(dataSet,attributes.get("highTemp"));
		calculateEntropy(dataSet,attributes.get("boatCount"));
		
	}

}
