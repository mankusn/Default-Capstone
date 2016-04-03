import java.io.FileNotFoundException;
import java.util.*;


public class ID3 {
	
	private Vector<InfoRow> pastData;
	private DecisionTree decisionTree;
	
	public ID3(){
		
	}
	public void generateTree(){
		
	}
	public static int sum(Collection<Integer> list){
		int sum = 0;
		for(int num:list){
			sum+=num;
		}
		return sum;
	}
	
	public static void calculateInformationGain(Vector<InfoRow> dataSet, Attribute boatCount,Vector<Attribute> selectedAttr, int category) {
		
		Vector<InfoRow> subSet = new Vector<InfoRow>();
		double valProb = 0.0;
		double subSetEntropy =  0.0;
		double infoGain = 0.0;
		String name;
		switch (category){
			case 1:
				name = "BoatLow";
				break;
			case 2:
				name = "BoatMed";
				break;
			case 3:
				name = "BoatHigh";
			default:
				name = null;
		}
		//Calculate the frequency of each of the values in the target attribute
		for (InfoRow row:dataSet){
			if(row.getInfoRow().get("boatCount").getCategory()==category){
				subSet.add(row);
			}
				
		}
		HashMap<String,Integer> valFreq = new HashMap<String,Integer>();
		Classifier currentClass = null;
		String catName;
		//Calculate the frequency of each of the values in the target attribute
		HashMap<String,Attribute> attributeList = subSet.get(0).getAttributes();
		for(String attrName:attributeList.keySet()){
			if(attrName =="boatCount"){
				continue;
			}
			for (InfoRow row:dataSet){
				currentClass = row.getInfoRow().get(attrName);
				catName = Integer.toString(currentClass.getDataPoint());
				
				if (valFreq.containsKey(attrName)){
					valFreq.put(attrName, valFreq.get(attrName) + 1);
				}else{
					valFreq.put(attrName, 1);
				}		
			}

		}
		for(String attr:attributeList.keySet()){
			if(attr == "boatCount"){
				continue;
			}
			calculateAttributeEntropy(subSet,attributeList.get(attr));
			
			for(String cat:attributeList.get(attr).getEntropyValues().keySet()){
				valProb = ((double)valFreq.get(attr))/sum(valFreq.values());
				subSetEntropy += valProb*attributeList.get(attr).getEntropyValues().get(cat);
			}
			
			infoGain = attributeList.get("boatCount").getEntropyValues().get(Integer.toString(category)) - subSetEntropy;
			System.out.println(infoGain + attributeList.get(attr).getName());
			attributeList.get(attr).setInfoGain(name, infoGain);
		}
		
		
		//Subtract the entropy of the chosen attribute from the entropy of the whole data set with respect to the boatCount attribute 
//		double result = (calculateEntropy(dataSet,boatCount)-subSetEntropy);
		
	}
	
	public static boolean hasCategory(HashMap<String,Integer> v,InfoRow row, String name){
		return v.containsKey(row.getInfoRow().get(name).getCategory());
	}
	
	public static double calculateSingleEntropy(Vector<InfoRow> dataSet, Attribute attribute, String catWanted){
		HashMap<String,Integer> valFreq = new HashMap<String,Integer>();
		double entropy =  0.0;
		Classifier currentClass = null;
		String category;
		//Calculate the frequency of each of the values in the target attribute
		for (InfoRow row:dataSet){
			currentClass = row.getInfoRow().get(attribute.getName());
			category = Integer.toString(currentClass.getDataPoint());
			if (valFreq.containsKey(category)){
				valFreq.put(category, valFreq.get(category) + 1);
			}else{
				valFreq.put(category, 1);
			}
		}
		double notP = 0.0;
		double P = 0.0;	
		double freq = (double)valFreq.get(catWanted);
		notP =((dataSet.size()-freq)/dataSet.size())*(Math.log((dataSet.size()-freq)/dataSet.size())/Math.log(2));
		P = (-freq/dataSet.size())*(Math.log(freq/dataSet.size())/Math.log(2));
		entropy = P-notP;	
		return entropy;
				
	}
	
	public static void calculateAttributeEntropy(Vector<InfoRow> dataSet, Attribute attribute){
		HashMap<String,Integer> valFreq = new HashMap<String,Integer>();
		double entropy =  0.0;
		Classifier currentClass = null;
		String category;
		//Calculate the frequency of each of the values in the target attribute
		for (InfoRow row:dataSet){
			currentClass = row.getInfoRow().get(attribute.getName());
			category = Integer.toString(currentClass.getDataPoint());
			if (valFreq.containsKey(category)){
				valFreq.put(category, valFreq.get(category) + 1);
			}else{
				valFreq.put(category, 1);
			}		
		}
		double notP = 0.0;
		double P = 0.0;
		for(String cat: valFreq.keySet()){
			
			double freq = (double)valFreq.get(cat);
			notP =((dataSet.size()-freq)/dataSet.size())*(Math.log((dataSet.size()-freq)/dataSet.size())/Math.log(2));
			P = (-freq/dataSet.size())*(Math.log(freq/dataSet.size())/Math.log(2));
			entropy = P-notP;
			attribute.getEntropyValues().put(cat,entropy);						
			entropy = 0.0;
			
		}	
		
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
	
	public static void classifyDataSet(Vector<InfoRow> dataSet){
		for(InfoRow row:dataSet){
			//System.out.println("getting row");
			row.classifyRow();
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
		InfoRow inforow1 = new InfoRow(rawData1,attributes);
		dataSet.add(inforow1);
		
		rawData2.put("windSpeed", "20");
		rawData2.put("windDir", "90");
		rawData2.put("highTemp", "50");
		rawData2.put("boatCount", "3");
		InfoRow inforow2 = new InfoRow(rawData2,attributes);
		dataSet.add(inforow2);
		
		rawData3.put("windSpeed", "50");
		rawData3.put("windDir", "180");
		rawData3.put("highTemp", "100");
		rawData3.put("boatCount", "6");
		InfoRow inforow3 = new InfoRow(rawData3,attributes);
		dataSet.add(inforow3);
		
		rawData4.put("windSpeed", "40");
		rawData4.put("windDir", "360");
		rawData4.put("highTemp", "75");
		rawData4.put("boatCount", "10");
		InfoRow inforow4 = new InfoRow(rawData4,attributes);
		dataSet.add(inforow4);
		
		setRange(dataSet,attributes);
		System.out.println("Classify Data Set");
		classifyDataSet(dataSet);
		System.out.println("Calculate Entropy");
		
		calculateInformationGain(dataSet,attributes.get("boatCount"),new Vector<Attribute>(),1);
//		calculateEntropy(dataSet,attributes.get("windSpeed"),"1");
//		calculateEntropy(dataSet,attributes.get("windDir"),"1");
//		calculateEntropy(dataSet,attributes.get("highTemp"),"1");
//		calculateEntropy(dataSet,attributes.get("boatCount"),"1");
		
	}

}
