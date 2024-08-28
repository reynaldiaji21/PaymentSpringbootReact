package com.rad.spring.react.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OracleJdbcService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int insertRecord(String id, String name) {
		String sql ="INSERT INTO CATEGORY(id, name) values(?,?)";
		try {
		return jdbcTemplate.update(sql,id, name);
		}catch (DuplicateKeyException e) {
			System.out.println("Data already insert :: " + id +" "+name);
		return -1;
		}catch (Exception e) {
			System.out.println("Unexpected exception:: " + e.getMessage());
			return -1;	// TODO: handle exception
		}
	}
	
	public List<Map<String, Object>> fetchAllRecords(){
		try {
			System.out.println("select list category :: ");
			String query = "SELECT * FROM CATEGORY";
			return jdbcTemplate.queryForList(query) ;
		}catch (Exception e) {
			System.out.println("error hit query list :: " + e.getMessage());
			
			return null;
		}
	}
	
	
	
}
