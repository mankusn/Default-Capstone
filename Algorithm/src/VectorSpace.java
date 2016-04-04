
import java.io.File;
import java.io.IOException;
import java.util.*;

import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.*;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import net.sf.javaml.core.SparseInstance;
import net.sf.javaml.tools.InstanceTools;
import net.sf.javaml.tools.data.FileHandler;



public class VectorSpace {
	
	
	
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
//				System.out.println("Key: "+key+" Min Value: "+min+ " Max Value: "+max);
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
	
	public static void main(String[] args) {
		
		Vector<InfoRow> dataSet = new Vector<InfoRow> ();
		HashMap<String,String> rawData1 = new HashMap<String,String>();
		HashMap<String,String> rawData2 = new HashMap<String,String>();
		HashMap<String,String> rawData3 = new HashMap<String,String>();
		HashMap<String,String> rawData4 = new HashMap<String,String>();
		HashMap<String,String> testData = new HashMap<String,String>();
		HashMap<String, Attribute> attributes = new HashMap<String, Attribute>();
		
		attributes.put("windSpeed",new Attribute("windSpeed"));
		attributes.put("windDir",new Attribute("windDir"));
		attributes.put("highTemp",new Attribute("highTemp"));
		attributes.put("boatCount",new Attribute("boatCount"));
		
		rawData1.put("windSpeed", "1");
		rawData1.put("windDir", "2");
		rawData1.put("highTemp", "3");
		rawData1.put("boatCount", "4");
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
		
		testData.put("windSpeed", "10");
		testData.put("windDir", "103");
		testData.put("highTemp", "23");
		testData.put("boatCount", "20");
		InfoRow test = new InfoRow(testData,attributes);
		
		Dataset data = new DefaultDataset();
		
		for(InfoRow row: dataSet){
			data.add(new DenseInstance(row.getValues()));
		}
		
		Clusterer km=new KMeans(2);
		 Dataset[] clusters = km.cluster(data);
		 
		 for(Dataset d:clusters){
			 System.out.println(d);
//			 for(Instance i:d){
//				 System.out.println(i);
//			 }
		 }
		
//		test.classifyRow();
//		setRange(dataSet,attributes);
//		System.out.println("Classify Data Set");
//		classifyDataSet(dataSet);
//		
//		System.out.println(new CosineSimilarity(dataSet.get(0),test).getCosine());
//		System.out.println(new CosineSimilarity(dataSet.get(1),test).getCosine());
//		System.out.println(new CosineSimilarity(dataSet.get(2),test).getCosine());
//		System.out.println(new CosineSimilarity(dataSet.get(3),test).getCosine());
		
		
	}

}
