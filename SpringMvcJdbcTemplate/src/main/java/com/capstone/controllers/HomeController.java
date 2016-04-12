package com.capstone.controllers;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.algorithm.InfoRow;
import com.capstone.algorithm.NeuralNetwork;
import com.capstone.dao.WeatherDataDAO;
import com.capstone.model.Login;
import com.capstone.model.WeatherAndTidal;
import com.capstone.model.WeatherAndTidalString;
import com.capstone.scraping.WeatherAPI;
import com.capstone.utility.Authentication;

/**
 * This controller routes accesses to the application to the appropriate
 * hanlder methods. 
 * @author www.codejava.net
 *
 */
@Controller
public class HomeController {
	boolean loggedIn = false;

	@Autowired 
	private WeatherDataDAO weatherDataDAO; 
	
	//Maps to the page localhost:8080/capstone/
	@RequestMapping(value="/")
	public ModelAndView listContact(ModelAndView model) throws IOException, JSONException{
		//List<DataTO> listData = testDAO.list();
		//model.addObject("listData", listData);
		//model.setViewName("home");
		
		List<WeatherAndTidal> listData = weatherDataDAO.list(); 
		model.addObject("listData", listData);
		model.setViewName("home");
		Vector<InfoRow> dataSet = new Vector<InfoRow>();
		for(int index = 0; index < listData.size(); index++){
			WeatherAndTidalString obj = new WeatherAndTidalString(listData.get(index));
			HashMap<String,String> map = WeatherAndTidalString.getMap(obj);
			InfoRow row = new InfoRow(map);
			//System.out.println(row);
			dataSet.add(row);
			//System.out.println(dataSet.get(index));
		}
//		KNN knn = new KNN(dataSet,5,"boatcount");
//		KNN knn2 = new KNN(dataSet,5,"boatcount2");
//		KNN knn3 = new KNN(dataSet,5,"boatcount3");
//		KNN knn4 = new KNN(dataSet,5,"boatcount4");
//		KNN knntot = new KNN(dataSet,5,"boattotal");
		
		NeuralNetwork knn = new NeuralNetwork(dataSet,"boatcount");
		NeuralNetwork knn2 = new NeuralNetwork(dataSet,"boatcount2");
		NeuralNetwork knn3 = new NeuralNetwork(dataSet,"boatcount3");
		NeuralNetwork knn4 = new NeuralNetwork(dataSet,"boatcount4");
		NeuralNetwork knntot = new NeuralNetwork(dataSet,"boattotal");

		WeatherAndTidal tomorrowData = WeatherAndTidal.getTomorrow();
		WeatherAndTidalString tomorrowString = new WeatherAndTidalString(tomorrowData);
		HashMap<String,String> tomorrowMap = WeatherAndTidalString.getMap(tomorrowString);
		InfoRow tomorrowRow = new InfoRow(tomorrowMap);
		
		knn.addTest(tomorrowRow);
		Object predictionObj = knn.getPrediction();
		String prediction = (String)predictionObj;
		
		knn2.addTest(tomorrowRow);
		predictionObj = knn2.getPrediction();
		String prediction2 = (String)predictionObj;
		
		knn3.addTest(tomorrowRow);
		predictionObj = knn3.getPrediction();
		String prediction3 = (String)predictionObj;
		
		knn4.addTest(tomorrowRow);
		predictionObj = knn4.getPrediction();
		String prediction4 = (String)predictionObj;
		
		knntot.addTest(tomorrowRow);
		predictionObj = knntot.getPrediction();
		String predictiontot = (String)predictionObj;
		
		model.addObject("prediction", prediction);
		model.addObject("prediction2", prediction2);
		model.addObject("prediction3", prediction3);
		model.addObject("prediction4", prediction4);
		model.addObject("predictiontot", predictiontot);
		model.addObject("tomorrowString",tomorrowString.stringForecast());
		System.out.println(tomorrowData.getDate());
		

		
//		KNN knn = new KNN (dataSet, 5, "boattotal");
//		NeuralNetwork nn = new NeuralNetwork(dataSet,"boattotal");
//		double correct = 0, wrong = 0;
//		double result = 0, rate = 0;
//		Vector<InfoRow> dataForClassification = dataSet;
//		for (InfoRow row : dataForClassification) {
//			Instance inst = new DenseInstance(row.getRawValues());
//		    Object predictedClassValue = nn.javasmo.classify(inst);
//		    Object realClassValue = inst.classValue();
//		    if (predictedClassValue.equals(realClassValue))
//		        correct++;
//		    else
//		        wrong++;
//		}
//		rate = correct/(correct+wrong);
//		if(rate>result){
//			result = rate;
//			k = i;
//		}
//		kValues.add(rate);
//		//System.out.println(i+": "+rate);
//		correct = 0.0;
//		wrong = 0.0;
		
		/*WeatherAndTidalString obj = new WeatherAndTidalString(listData.get(0));
		HashMap<String,String> map = WeatherAndTidalString.getMap(obj);
		System.out.println(map);
		System.out.println(map.get("date"));*/
		return model;
	}
	
	/*@RequestMapping(value = "/newData", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		DataTO newData = new DataTO();
		model.addObject("data", newData);
		model.setViewName("DataForm");
		return model;
	}
	
	@RequestMapping(value = "/saveData", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute DataTO dataTO) {
		testDAO.saveOrUpdate(dataTO);		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		String date = request.getParameter("id");
		//contactDAO.delete(contactId);
		testDAO.delete(date);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/editData", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		//int contactId = Integer.parseInt(request.getParameter("id"));
		//Contact contact = contactDAO.get(contactId);
		ModelAndView model = new ModelAndView("DataForm");
		//model.addObject("contact", contact);
		
		return model;
	}*/
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ModelAndView insertPoint() throws JSONException{
		WeatherAndTidal obj = new WeatherAndTidal();
		weatherDataDAO.insert(obj);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/generate", method = RequestMethod.POST)
	public ModelAndView generateData() throws JSONException{
		WeatherAndTidal obj = WeatherAndTidal.getTomorrow();
		weatherDataDAO.insert(obj);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value ="/prediction")
    public ModelAndView predictionPage(ModelAndView model) throws JSONException, IOException{
        if(loggedIn){
        	List<WeatherAndTidal> listData = weatherDataDAO.list(); 
    		model.addObject("listData", listData);
    		Vector<InfoRow> dataSet = new Vector<InfoRow>();
    		for(int index = 0; index < listData.size(); index++){
    			WeatherAndTidalString obj = new WeatherAndTidalString(listData.get(index));
    			HashMap<String,String> map = WeatherAndTidalString.getMap(obj);
    			InfoRow row = new InfoRow(map);
    			//System.out.println(row);
    			dataSet.add(row);
    			//System.out.println(dataSet.get(index));
    		}
    		
    		NeuralNetwork knn = new NeuralNetwork(dataSet,"boatcount");
    		NeuralNetwork knn2 = new NeuralNetwork(dataSet,"boatcount2");
    		NeuralNetwork knn3 = new NeuralNetwork(dataSet,"boatcount3");
    		NeuralNetwork knn4 = new NeuralNetwork(dataSet,"boatcount4");
    		NeuralNetwork knntot = new NeuralNetwork(dataSet,"boattotal");
    		
    		WeatherAndTidal tomorrowData = WeatherAndTidal.getTomorrow();
    		WeatherAndTidalString tomorrowString = new WeatherAndTidalString(tomorrowData);
    		HashMap<String,String> tomorrowMap = WeatherAndTidalString.getMap(tomorrowString);
    		InfoRow tomorrowRow = new InfoRow(tomorrowMap);
    		
    		knn.addTest(tomorrowRow);
    		Object predictionObj = knn.getPrediction();
    		String prediction = (String)predictionObj;
    		
    		knn2.addTest(tomorrowRow);
    		predictionObj = knn2.getPrediction();
    		String prediction2 = (String)predictionObj;
    		
    		knn3.addTest(tomorrowRow);
    		predictionObj = knn3.getPrediction();
    		String prediction3 = (String)predictionObj;
    		
    		knn4.addTest(tomorrowRow);
    		predictionObj = knn4.getPrediction();
    		String prediction4 = (String)predictionObj;
    		
    		knntot.addTest(tomorrowRow);
    		predictionObj = knntot.getPrediction();
    		String predictiontot = (String)predictionObj;
    		
    		model.addObject("prediction", prediction);
    		model.addObject("prediction2", prediction2);
    		model.addObject("prediction3", prediction3);
    		model.addObject("prediction4", prediction4);
    		model.addObject("predictiontot", predictiontot);
    		model.addObject("tomorrowString",tomorrowString.stringForecast());
            model.setViewName("prediction");
            model.addObject("date", WeatherAPI.unixTimeToDate(System.currentTimeMillis()).substring(0,10));
            return model;
        }else{
            model.setViewName("login");
            Login login = new Login(); 
            model.addObject("credentials", login);
            return model; 
        }
        
    }
    
    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public ModelAndView loginPage(ModelAndView model){
        
        Login login = new Login(); 
        model.addObject("credentials", login);
        model.setViewName("login");
        return model;
    }
    
    @RequestMapping(value ="/logout")
    public String logout(){
        loggedIn = false; 
        return "redirect:/login"; 
    }
    
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String validate(@ModelAttribute Login login) throws InvalidKeyException, NoSuchAlgorithmException {
        
    
        String hashedCredentials = "TB89LynzPPoY17LLn1w8NHK9u7kF1blD4bme/BycuGBB1BTnh/5WVXcbVKYOwfJlc6SqHMqNtqCbnJMzmA4C+Q==";
        String hashedCred = Authentication.HMAC(login.getUserName(), login.getPassword());
        if(hashedCred.equals(hashedCredentials)){
            loggedIn = true; 
            return "redirect:/prediction";
        }else{
            System.out.println("WRONG");
            return "redirect:/login"; 
        }
        
    }
}
