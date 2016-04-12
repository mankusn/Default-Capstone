package com.capstone.algorithm;

import java.util.Vector;

import weka.classifiers.functions.SMO;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.weka.WekaClassifier;

public class NeuralNetwork {
	private Dataset data;
	private InfoRow testing;
	private WekaClassifier javasmo;
	private String column;
	private String[] ranges;
	final private int numClasses = 10;
	public NeuralNetwork(Vector<InfoRow> d, String col){
		this.ranges = new String[numClasses + 1]; 
		column = col;
		SMO smo = new SMO();
		this.javasmo = new WekaClassifier(smo);;
		this.data = new DefaultDataset();
		classifyBoatData(d);
		importData(d);
		this.javasmo.buildClassifier(this.data);
	}
	
	public void addTest(InfoRow test){
		this.testing = test;
	}
	
	//Returns Prediction of Classification
	public Object getPrediction(){
		Instance instance = new DenseInstance(this.testing.getRawValues());
		Object prediction = this.javasmo.classify(instance);
		
		
		return ranges[(int)(double)prediction];
		
	}
	//Converts raw data into Java-ML Dataset
	private void importData(Vector<InfoRow> dataSet){
		DenseInstance instance;
		for(InfoRow row: dataSet){
			instance = new DenseInstance(row.getRawValues());
			instance.setClassValue( Double.parseDouble(row.getInfoRow().get(this.column)));
			this.data.add(instance);
		}
	}
	
	//Finds minimum and maximum value in raw data set
	private double[] getMinMax(Vector<InfoRow> dataSet){
		double array[] = new double[2];
		double max = 0.0;
		double min = Double.MAX_VALUE;
		double temp= 0.0;
		for(InfoRow row: dataSet){
			for(String key:row.getInfoRow().keySet()){
				if(key ==this.column){
					temp = Double.parseDouble(row.getInfoRow().get(key));
					if(temp> max)
						max = temp;
					if(temp<min)
						min = temp;
				}else
					continue;
			}
		}
		array[0] = min;
		array[1] = max;
		return array;		
	}
	
	//Finds Specific class for boat data
	private int classify(double dataConversion, double minValue, double maxValue) {
		int count = 0;
		double currentValue = minValue;
		double change = (maxValue - minValue)/numClasses;
		while (currentValue <= dataConversion && currentValue != maxValue) {
			currentValue += change;
			++count;
		}
		return count;
	}
	
	//Gets ranges of each class
	private void getRanges(double min, double max){
		double change = (max-min)/numClasses;
		double temp = min;
		int upper = 0;
		this.ranges[0] =">="+min+" BOATS";
		for(int i =1;i<=numClasses-1;i++){
			
			upper = (int)temp+(int)change;
			this.ranges[i] = Integer.toString((int)(temp+1))+"-"+Integer.toString(upper)+ " BOATS";
			temp = upper;
		}
		this.ranges[numClasses] = Integer.toString((int)temp+1)+"< BOATS";	
	}
	
	//Classifies Boat Data in 4 classes
	private void classifyBoatData(Vector<InfoRow> dataSet){
		String newValue;
		double minMax[] = getMinMax(dataSet);
		getRanges(minMax[0],minMax[1]);
		double boatData = 0.0;
		for(InfoRow row:dataSet){
			boatData = Double.parseDouble(row.getInfoRow().get(this.column));
			newValue = Double.toString(classify(boatData,minMax[0],minMax[1]));
			row.getInfoRow().put(this.column, newValue );
			
		}
		
	}
	
	public Dataset getData() {
		return data;
	}

	public void setData(Dataset data) {
		this.data = data;
	}

	public InfoRow getTesting() {
		return testing;
	}

	public void setTesting(InfoRow testing) {
		this.testing = testing;
	}

	public Classifier getKnn() {
		return this.javasmo;
	}

	public void setKnn(WekaClassifier jsmo) {
		this.javasmo = jsmo;
	}
	
	public String[] getRanges() {
		return ranges;
	}
	
	public void setRanges(String[] ranges) {
		this.ranges = ranges;
	}
}