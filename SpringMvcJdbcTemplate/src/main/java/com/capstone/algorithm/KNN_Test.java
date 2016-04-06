package com.capstone.algorithm;
import static org.junit.Assert.*;

import java.util.*;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;

import org.junit.Assert;
import org.junit.Test;


/*public class KNN_Test {
	

	
	@Test
	public void infoRowConstructor() {
		HashMap<String,String> rawData1 = new HashMap<String,String>();
		rawData1.put("windSpeed", "1");
		rawData1.put("windDir", "2");
		rawData1.put("highTemp", "3");
		rawData1.put("boatCount", "4");
		InfoRow inforow1 = new InfoRow(rawData1);
		assertEquals(inforow1.getInfoRow(),rawData1);
	}
	
	@Test
	public void infoRowGetRawValues() {
		HashMap<String,String> rawData1 = new HashMap<String,String>();
		rawData1.put("windSpeed", "1");
		rawData1.put("windDir", "2");
		rawData1.put("highTemp", "3");
		rawData1.put("boatCount", "4");
		InfoRow inforow1 = new InfoRow(rawData1);
		double[] array = new double[]{3.0,2.0,1.0};

		Assert.assertArrayEquals(inforow1.getRawValues(),array, 0);
	}
	@Test
	public void knnConstructor() {
		Vector<InfoRow> dataSet = new Vector<InfoRow> ();
		HashMap<String,String> rawData1 = new HashMap<String,String>();
		HashMap<String,String> testData = new HashMap<String,String>();
		rawData1.put("windSpeed", "1");
		rawData1.put("windDir", "2");
		rawData1.put("highTemp", "3");
		rawData1.put("boatCount", "4");
		InfoRow inforow1 = new InfoRow(rawData1);
		dataSet.add(inforow1);		

		testData.put("windSpeed", "10");
		testData.put("windDir", "103");
		testData.put("highTemp", "23");
		testData.put("boatCount", "50");
		InfoRow test = new InfoRow(testData);
		
		KNN knn = new KNN(dataSet,3);

		assertEquals(knn.getTesting(),test);
		assert(knn.getData() instanceof Dataset);
		assert(knn.getKnn() instanceof Classifier);
		assert(knn.getRanges() instanceof String[]);
	}
	
	@Test
	public void knnClassifyTraining() {
		Vector<InfoRow> dataSet = new Vector<InfoRow> ();
		HashMap<String,String> rawData1 = new HashMap<String,String>();
		HashMap<String,String> rawData2 = new HashMap<String,String>();
		HashMap<String,String> rawData3 = new HashMap<String,String>();
		HashMap<String,String> rawData4 = new HashMap<String,String>();
		HashMap<String,String> testData = new HashMap<String,String>();
		rawData1.put("windSpeed", "1");
		rawData1.put("windDir", "2");
		rawData1.put("highTemp", "3");
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
		rawData3.put("boatCount", "12");
		InfoRow inforow3 = new InfoRow(rawData3);
		dataSet.add(inforow3);
		
		rawData4.put("windSpeed", "40");
		rawData4.put("windDir", "360");
		rawData4.put("highTemp", "75");
		rawData4.put("boatCount", "40");
		InfoRow inforow4 = new InfoRow(rawData4);
		dataSet.add(inforow4);
		
		testData.put("windSpeed", "10");
		testData.put("windDir", "103");
		testData.put("highTemp", "23");
		testData.put("boatCount", "50");
		InfoRow test = new InfoRow(testData);
		
		String[] rangeTest = new String[]{"1-10 boats","11-20 boats","21-30 boats","31< boats"};
		KNN knn = new KNN(dataSet,3);
		Assert.assertArrayEquals( knn.getRanges(),rangeTest);
	}
	@Test
	public void knnPrediction() {
		Vector<InfoRow> dataSet = new Vector<InfoRow> ();
		HashMap<String,String> rawData1 = new HashMap<String,String>();
		HashMap<String,String> rawData2 = new HashMap<String,String>();
		HashMap<String,String> rawData3 = new HashMap<String,String>();
		HashMap<String,String> rawData4 = new HashMap<String,String>();
		HashMap<String,String> testData = new HashMap<String,String>();
		rawData1.put("windSpeed", "1");
		rawData1.put("windDir", "2");
		rawData1.put("highTemp", "3");
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
		rawData3.put("boatCount", "12");
		InfoRow inforow3 = new InfoRow(rawData3);
		dataSet.add(inforow3);
		
		rawData4.put("windSpeed", "40");
		rawData4.put("windDir", "360");
		rawData4.put("highTemp", "75");
		rawData4.put("boatCount", "40");
		InfoRow inforow4 = new InfoRow(rawData4);
		dataSet.add(inforow4);
		
		testData.put("windSpeed", "45");
		testData.put("windDir", "361");
		testData.put("highTemp", "76");
		testData.put("boatCount", null);
		InfoRow test = new InfoRow(testData);
		
		KNN knn = new KNN(dataSet,2);

		assertEquals(knn.getPrediction(), "11-20 boats" );


	}
	
}*/
