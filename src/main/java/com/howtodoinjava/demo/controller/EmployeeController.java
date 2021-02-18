package com.howtodoinjava.demo.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.demo.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

@RestController
public class EmployeeController {
	
	@RequestMapping("/getEmployees")
    public List<Employee> getEmployees() 
    {
		List<Employee> employeesList = new ArrayList<Employee>();
		Connection con = null;
		try {
			con = getConnection();
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from guests");
			String name = "";
			String sureName = "";
			String email = "";
			if(rs.next()){
				name = rs.getString("firstname");
				sureName = rs.getString("lastname");
				email = rs.getString("email");
			}
			employeesList.add(new Employee(1,name,sureName,email));
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		return employeesList;
    }

	private static Connection getConnection() throws URISyntaxException, Exception {
		Class.forName("org.postgresql.Driver");
		String dbUrl =   "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_70451cd8b61281c";
		return DriverManager.getConnection(dbUrl,"b93dc6f07df6ea","313d4648");
	}

}
