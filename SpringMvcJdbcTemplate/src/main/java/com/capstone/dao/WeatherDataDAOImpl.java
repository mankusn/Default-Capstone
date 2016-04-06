package com.capstone.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.capstone.model.Contact;
import com.capstone.model.WeatherAndTidal;
import com.capstone.to.DataTO;

public class WeatherDataDAOImpl implements WeatherDataDAO {

	private JdbcTemplate jdbcTemplate;

	public WeatherDataDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<WeatherAndTidal> list() {
		String sql = "SELECT * FROM public.data";
		List<WeatherAndTidal> listData = jdbcTemplate.query(sql, new RowMapper<WeatherAndTidal>() {

			@Override
			public WeatherAndTidal mapRow(ResultSet rs, int rowNum) throws SQLException {
				//Contact aContact = new Contact();
				//DataTO dataTO = new DataTO();
				//dataTO.setDate(rs.getString("date"));
				//dataTO.setBoatCount(rs.getString("boatcount"));
				//return dataTO;		
				WeatherAndTidal data;
				try {
					data = new WeatherAndTidal();
					data.setBoatCount(rs.getInt("boatcount"));
					data.setHighTemp(rs.getInt("hightemp"));
					data.setLowTemp(rs.getInt("lowtemp"));
					data.setPrecip(rs.getDouble("precip"));
					data.setPrecipProb(rs.getInt("precipchance"));
					data.setSwellDir(rs.getInt("swelldir"));
					data.setSwellHeight(rs.getDouble("swellheight_m"));
					data.setSwellPeriod(rs.getDouble("swellperiod_secs"));
					data.setWaterTemp(rs.getInt("watertemp_f"));
					data.setWindDir(rs.getInt("winddir"));
					data.setWindSpeed(rs.getInt("windspeed"));
					return data;
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} 
			}
			
		});
		
		return listData;
	}

	@Override
	public WeatherAndTidal get(String date) {
		return null;
	}

	@Override
	public void insert(WeatherAndTidal data) {
		String columns = "precip, watertemp_f, swellperiod_secs, swellheight_m, hightemp, lowtemp, precipchance, windspeed, boatcount, swelldir, winddir";
		String sql = "INSERT INTO public.data (" + columns + ")" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, data.getPrecip(), data.getWaterTemp(), data.getSwellPeriod(), data.getSwellHeight(), data.getHighTemp(), data.getLowTemp(), data.getPrecipProb(), data.getWindSpeed(), data.getBoatCount(), data.getSwellDir(), data.getWindDir());
		
	}

}
