package com.rad.spring.react;

import java.security.KeyStore.Entry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import oracle.jdbc.datasource.impl.OracleDataSource;

@SpringBootApplication
public class RadSpringBootApplication {

	public static void main(String[] args) {
	SpringApplication.run(RadSpringBootApplication.class, args);
		//String input ="Jakarta Tomcat";
		//countUniqueChar(input);
		
		
		
//		int[] grades = {73, 67, 38, 33};
//		int[] valueGrades = gradeStudent(grades);
//		System.out.println(Arrays.toString(valueGrades));
		
	}

	public static void countUniqueChar(String input){
		Map<Character, Integer> charCount = new LinkedHashMap<>();
		char[] array = input.toCharArray();
		for (char c : array) {
			if (c != ' ') {
				charCount.put(c, charCount.getOrDefault(c, 0) + 1);

			}
		}

		for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
			System.out.print(entry.getKey() + ":" + entry.getValue() + " ");
		}

	}
	
	
	public static int[] gradeStudent(int[] grades) {
		int[] valueGrades =  new int[grades.length];
		for (int i = 0; i < grades.length; i++) {
			int grade = grades[i];
			if(grade >=38){
				int multipleFive = ((grade/5)+1) *5 ;
				if(multipleFive - grade<3) {
					grade = multipleFive;
				}
			}
			valueGrades[i] = grade;
		}
		
		return valueGrades;
		
		
	}
	
	
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setU
//	}
	
	
	
}
