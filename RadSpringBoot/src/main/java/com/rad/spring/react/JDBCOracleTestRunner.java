package com.rad.spring.react;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rad.spring.react.service.OracleJdbcService;
@Component
public class JDBCOracleTestRunner implements CommandLineRunner{

	@Autowired
	private OracleJdbcService oracleJdbcService;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("check running jdbc oracle");
		//oracleJdbcService.insertRecord("2", "belanja");
		//List<Map<String, Object>> recordList = oracleJdbcService.fetchAllRecords();
		//System.out.println("check select list :: " + recordList);

	}
	
	
	

}
