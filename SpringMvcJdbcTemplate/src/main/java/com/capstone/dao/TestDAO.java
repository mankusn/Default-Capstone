package com.capstone.dao;

import java.util.List;

import com.capstone.model.Contact;
import com.capstone.to.DataTO;

public interface TestDAO {
	
	public void saveOrUpdate(DataTO dataTO);

	public void delete(String date);

	public DataTO get(String date);

	public List<DataTO> list();
}
