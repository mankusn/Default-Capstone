package com.capstone.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.algorithm.InfoRow;
import com.capstone.algorithm.KNN;
import com.capstone.dao.ContactDAO;
import com.capstone.dao.TestDAO;
import com.capstone.dao.WeatherDataDAO;
import com.capstone.model.WeatherAndTidal;
import com.capstone.model.WeatherAndTidalString;
import com.capstone.scraping.WeatherAPI;
import com.capstone.to.DataTO;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This controller routes accesses to the application to the appropriate
 * hanlder methods. 
 * @author www.codejava.net
 *
 */
@Controller
public class HomeController {

	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private TestDAO testDAO;
	
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
			System.out.println(dataSet.get(index));
		}
		KNN knn = new KNN(dataSet,5,"boatcount_one");
		
		WeatherAndTidal tomorrowData = WeatherAndTidal.getTomorrow();
		WeatherAndTidalString tomorrowString = new WeatherAndTidalString(tomorrowData);
		HashMap<String,String> tomorrowMap = WeatherAndTidalString.getMap(tomorrowString);
		InfoRow tomorrowRow = new InfoRow(tomorrowMap);
		knn.addTest(tomorrowRow);
		System.out.println(knn.getPrediction());
		
		/*WeatherAndTidalString obj = new WeatherAndTidalString(listData.get(0));
		HashMap<String,String> map = WeatherAndTidalString.getMap(obj);
		System.out.println(map);
		System.out.println(map.get("date"));*/
		return model;
	}
	
	@RequestMapping(value = "/newData", method = RequestMethod.GET)
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
	}
	
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
}
