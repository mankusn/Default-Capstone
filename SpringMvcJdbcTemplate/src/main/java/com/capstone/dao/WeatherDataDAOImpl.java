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
					data.setDate(rs.getString("date"));
					data.setHighTemp(rs.getInt("highTemp"));
					data.setLowTemp(rs.getInt("lowTemp"));
					data.setOutlook(rs.getString("outlook"));
					data.setPrecipMM(rs.getDouble("precipMM"));
					data.setPrecipProb(rs.getInt("precipChance"));
					data.setSwellDir(rs.getInt("swellDir"));
					data.setSwellHeight(rs.getDouble("swellHeight_m"));
					data.setSwellPeriod(rs.getDouble("swellPeriod_secs"));
					data.setVisibility(rs.getInt("visibility"));
					data.setWaterTemp(rs.getInt("waterTemp_F"));
					data.setWindDir(rs.getInt("windDir"));
					data.setWindDir16Point(rs.getString("winddir16Point"));
					//data.setWindDirDegree(rs.getInt("winddirDegree"));
					data.setWindSpeed(rs.getInt("windSpeed"));
					//data.setWindSpeedMiles(rs.getInt("windspeedMiles"));
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
		String columns = "date, precipMM, visibility, waterTemp_F, winddirDegree, windspeedMiles, swellPeriod_secs, swellHeight_m, highTemp, lowTemp, precipChance, windspeed, boatcount, swellDir, outlook, winddir16Point, windDir";
		String sql = "INSERT INTO public.data (" + columns + ")" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, data.getDate(), data.getPrecipMM(), data.getVisibility(), data.getWaterTemp(), /*data.getWindDirDegree(), data.getWindSpeedMiles(),*/ data.getSwellPeriod(), data.getSwellHeight(), data.getHighTemp(), data.getLowTemp(), data.getPrecipProb(), data.getWindSpeed(), data.getBoatCount(), data.getBoatCount(), data.getSwellDir(), data.getOutlook(), data.getWindDir16Point(), data.getWindDir());
		
	}

}
