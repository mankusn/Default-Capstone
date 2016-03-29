package com.capstone.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capstone.dao.ContactDAO;
import com.capstone.dao.TestDAO;
import com.capstone.dao.WeatherDataDAO;
import com.capstone.model.WeatherAndTidal;
import com.capstone.scraping.WeatherAPI;
import com.capstone.to.DataTO;

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
	public ModelAndView listContact(ModelAndView model) throws IOException{
		//List<DataTO> listData = testDAO.list();
		//model.addObject("listData", listData);
		//model.setViewName("home");
		
		List<WeatherAndTidal> listData = weatherDataDAO.list(); 
		model.addObject("listData", listData);
		model.setViewName("home");
		
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
		long time = System.currentTimeMillis();
		time = time-86400000;
		for(int i=0; i<150; i++){
			WeatherAndTidal obj = new WeatherAndTidal(time);
			weatherDataDAO.insert(obj);
		}
		return new ModelAndView("redirect:/");
	}
}
