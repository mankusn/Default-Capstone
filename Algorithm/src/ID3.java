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
	public double calculateInformationGain(Vector<InfoRow> dataSet,Attribute old, Attribute target){
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
		//Calculate the sum of the entropy for each subset of records weighted by their probability of occuring in the training set.
		for(String key:valFreq.keySet()){
			valProb = valFreq.get(key)/sum(valFreq.values());
			for(InfoRow record:dataSet){
				if(record.getInfoRow().get(old.getName()).getName()==key){
					dataSubSet.add(record);
				}
			}
			subSetEntropy +=valProb*calculateEntropy(dataSubSet,target);
		}
		//Subtract the entropy of the chosen attribute from the entropy of the whole data set with respect to the target attribute 
		double result = (calculateEntropy(dataSet,target)-subSetEntropy);
		return result;
		
	}
	
	public boolean hasCategory(HashMap<String,Integer> v,InfoRow row, String name){
		return v.containsKey(row.getInfoRow().get(name).getCategory());
	}
	
	public double calculateEntropy(Vector<InfoRow> dataSet, Attribute attribute){
		HashMap<String,Integer> valFreq = new HashMap<String,Integer>();
		double entropy =  0.0;
		//Calculate the frequency of each of the values in the target attribute
		for (InfoRow row:dataSet){
			
			if(hasCategory(valFreq,row,attribute.getName())){
				int oldValue = valFreq.get(attribute.getName());
				valFreq.put(attribute.getName(),oldValue+1);
			}else{
				valFreq.put(attribute.getName(), 1);
			}				
		}
		for(int freq : valFreq.values()){
			entropy += (-freq/dataSet.size())*(Math.log(freq/dataSet.size())/Math.log(2));
		}
		
		attribute.setEntropy(entropy);
		
		return entropy;
	}
	
	public static void setRange(Vector<InfoRow> dataSet,HashMap<String,Attribute> attributes){
		
		double max = 0.0;
		double min = Double.MAX_VALUE;
		double temp;
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
			attributes.get(key).setMin(min);
			attributes.get(key).setMax(max);
		}
	}
	
	public static void classifyDataSet(Vector<InfoRow> dataSet, HashMap<String, Attribute> attributes){
		for(InfoRow row:dataSet){
			row.classifyRow(attributes);
		}
	}
	
	public static void main(String[] args){
		
		Vector<InfoRow> dataSet = new Vector<InfoRow> ();
		HashMap<String,String> rawData1 = new HashMap<String,String>();
		HashMap<String,String> rawData2 = new HashMap<String,String>();
		HashMap<String,String> rawData3 = new HashMap<String,String>();
		Vector<Attribute> attributes = new Vector<Attribute>();
		
		attributes.add(new Attribute("WindSpeed"));
		attributes.add(new Attribute("WindDir"));
		attributes.add(new Attribute("Temp"));
		
		rawData1.put("WindSpeed", "0");
		rawData1.put("WindDir", "0");
		rawData1.put("Temp", "0");
		InfoRow inforow1 = new InfoRow(rawData1);
		dataSet.add(inforow1);
		
		rawData2.put("WindSpeed", "20");
		rawData2.put("WindDir", "180");
		rawData2.put("Temp", "50");
		InfoRow inforow2 = new InfoRow(rawData1);
		dataSet.add(inforow2);
		
		rawData3.put("WindSpeed", "20");
		rawData3.put("WindDir", "180");
		rawData3.put("Temp", "50");
		InfoRow inforow3 = new InfoRow(rawData1);
		dataSet.add(inforow3);
		
		
	}

}
