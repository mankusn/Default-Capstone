package com.capstone.algorithm;
/*Copyright 2016 Team Default TAMU CSCE 482 Dr. Murphy*/
import java.io.File;
import java.io.IOException;
import java.util.*;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;
public class Testing {
	
	
	public static void main(String[] args) throws IOException {

//		Vector<InfoRow> dataSet = new Vector<InfoRow> ();
//		HashMap<String,String> rawData1 = new HashMap<String,String>();
//		HashMap<String,String> rawData2 = new HashMap<String,String>();
//		HashMap<String,String> rawData3 = new HashMap<String,String>();
//		HashMap<String,String> rawData4 = new HashMap<String,String>();
//		HashMap<String,String> testData = new HashMap<String,String>();
//		rawData1.put("windSpeed", "1");
//		rawData1.put("windDir", "2");
//		rawData1.put("highTemp", "3");
//		rawData1.put("boatCount", "4");
//		InfoRow inforow1 = new InfoRow(rawData1);
//		dataSet.add(inforow1);
//		
//		rawData2.put("windSpeed", "20");
//		rawData2.put("windDir", "90");
//		rawData2.put("highTemp", "50");
//		rawData2.put("boatCount", "3");
//		InfoRow inforow2 = new InfoRow(rawData2);
//		dataSet.add(inforow2);
//		
//		rawData3.put("windSpeed", "50");
//		rawData3.put("windDir", "180");
//		rawData3.put("highTemp", "100");
//		rawData3.put("boatCount", "12");
//		InfoRow inforow3 = new InfoRow(rawData3);
//		dataSet.add(inforow3);
//		
//		rawData4.put("windSpeed", "40");
//		rawData4.put("windDir", "360");
//		rawData4.put("highTemp", "75");
//		rawData4.put("boatCount", "25");
//		InfoRow inforow4 = new InfoRow(rawData4);
//		dataSet.add(inforow4);
//		
//		testData.put("windSpeed", "10");
//		testData.put("windDir", "103");
//		testData.put("highTemp", "23");
//		testData.put("boatCount", "50");
//		InfoRow test = new InfoRow(testData);
		
//		KNN knn = new KNN(dataSet,(int)Math.sqrt(dataSet.size()),test);
//		
//		System.out.print(knn.getPrediction());
		
		Dataset data = FileHandler.loadDataset(new File("Testing.csv"), 12, ",");
		Dataset dataForClassification = FileHandler.loadDataset(new File("Testing.csv"), 12, ",");
		 /* Contruct a KNN classifier that uses 5 neighbors to make a
		  *decision. */
		double correct = 0, wrong = 0;
		Classifier knn;
		double result = 0.0;
		double rate = 0.0;
		int k = 0;
		Vector<Double> kValues = new Vector<Double>();
		for(int i = 1;i<=50;i++){
			
		
			knn = new KNearestNeighbors(i);
			knn.buildClassifier(data);
			
			
			/* Counters for correct and wrong predictions. */
			
			/* Classify all instances and check with the correct class values */
			for (Instance inst : dataForClassification) {
			    Object predictedClassValue = knn.classify(inst);
			    Object realClassValue = inst.classValue();
			    if (predictedClassValue.equals(realClassValue))
			        correct++;
			    else
			        wrong++;
			}
			rate = correct/(correct+wrong);
			if(rate>result){
				result = rate;
				k = i;
			}
			kValues.add(rate);
			//System.out.println(i+": "+rate);
			correct = 0.0;
			wrong = 0.0;
		}
		//System.out.print(k+": "+result);
	}

}
