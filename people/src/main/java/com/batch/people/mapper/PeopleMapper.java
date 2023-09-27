package com.batch.people.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.batch.people.entity.People;


public class PeopleMapper implements RowMapper<People> {  
	
 public People mapRow(ResultSet rs, int rowNum) throws SQLException {  
	 People people = new People();  
   
  people.setpeopleId(rs.getLong("peopleId"));
  people.setLoginId(rs.getString("loginId"));
  people.setFirstName(rs.getString("firstName"));
  people.setLastName(rs.getString("lastName"));
  people.setSex(rs.getString("sex"));
  people.setEmail(rs.getString("email"));
  people.setPhone(rs.getString("phone"));
  
  
  return people;  
 }  
} 