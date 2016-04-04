import java.util.*;
public class CosineSimilarity {
	
	private InfoRow training;
	private InfoRow testing;

	private double cosine;
	
	public CosineSimilarity(InfoRow train, InfoRow test){
		
		training = train;
		testing = test;
		cosine = cosine(train, test);
		
	}
	public double getCosine(){
		return cosine;
	}
	private double dotProduct(HashMap<String,Classifier> train, HashMap<String,Classifier> test){
		double dotProduct = 0.0;
		for(String name: test.keySet()){
//			System.out.println(name+" Test: "+test.get(name).getCategory()+" Train: "+train.get(name).getCategory());
			dotProduct+= (Integer.parseInt(train.get(name).getPreClassifyData()) * Integer.parseInt(test.get(name).getPreClassifyData()));
		}
//		System.out.println("dotProduct: "+dotProduct);
		return dotProduct;
	}
	
	private double magnitude(HashMap<String,Classifier> test){
		double magnitude = 0.0;
		for(String name: test.keySet()){
//			System.out.println(name+": "+test.get(name).getCategory()+"*"+test.get(name).getCategory());
			magnitude+= (Integer.parseInt(test.get(name).getPreClassifyData())*Integer.parseInt(test.get(name).getPreClassifyData()));
			
		}
		magnitude = Math.sqrt(magnitude);
//		System.out.println("Magnitude: "+magnitude);
		return magnitude;
	}
	private double cosine(InfoRow train, InfoRow test){
	
		double dotProduct = dotProduct(train.getInfoRow(),test.getInfoRow());
		double magnitude = magnitude(train.getInfoRow()) * magnitude(test.getInfoRow());
//		System.out.println("TotalMagnitude: "+magnitude);
		double cosine = dotProduct/magnitude;
//		System.out.println("Cosine: "+cosine);
		return cosine;
		
	
	
	
	}
}
