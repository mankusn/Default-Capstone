package com.capstone.dao;

import java.util.List;

import com.capstone.model.WeatherAndTidal;

public interface WeatherDataDAO {

	public List<WeatherAndTidal> list(); 
	public WeatherAndTidal get (String date); 
	public void insert(WeatherAndTidal data);
	
	
}


