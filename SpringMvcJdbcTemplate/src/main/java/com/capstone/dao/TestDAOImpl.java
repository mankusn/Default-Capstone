package com.capstone.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.capstone.model.Contact;
import com.capstone.to.DataTO;

public class TestDAOImpl implements TestDAO {

	private JdbcTemplate jdbcTemplate;

	public TestDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void saveOrUpdate(DataTO dataTO) {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO public.data (date, boatcount)" + " VALUES (?, ?)";
		jdbcTemplate.update(sql, dataTO.getDate(), dataTO.getBoatCount());

	}

	@Override
	public void delete(String date) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM public.data WHERE contact_id=?";
		jdbcTemplate.update(sql, date);
	}

	@Override
	public DataTO get(String date) {
		String sql = "SELECT * FROM contact WHERE date =" + date;
		return jdbcTemplate.query(sql, new ResultSetExtractor<DataTO>() {

			@Override
			public DataTO extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					DataTO dataTO = new DataTO();
					dataTO.setDate(rs.getString("date"));
					dataTO.setDate(rs.getString("boatcount"));
					return dataTO;
				}
				
				return null;
			}
			
		});
	}

	

	@Override
	public List<DataTO> list() {
		String sql = "SELECT * FROM public.data";
		List<DataTO> listData = jdbcTemplate.query(sql, new RowMapper<DataTO>() {

			@Override
			public DataTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact aContact = new Contact();
				DataTO dataTO = new DataTO();
				dataTO.setDate(rs.getString("date"));
				dataTO.setBoatCount(rs.getString("boatcount"));
				return dataTO;
			}
			
		});
		
		return listData;
	}

}
