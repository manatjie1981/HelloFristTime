package com.howtodoinjava.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.howtodoinjava.demo.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
public class EmployeeController {
	
	@RequestMapping("/getEmployees")
    public List<Employee> getEmployees() 
    {
		List<Employee> employeesList = new ArrayList<Employee>();
		String str = connectDB();
		employeesList.add(new Employee(1,"lokesh","gupta","howtodoinjava@gmail.com"));
		return employeesList;
    }
	
	private String connectDB() {
		
		Connection connect = null;
		String str="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect =  DriverManager.getConnection("jdbc:mysql://mysql-20612-0.cloudclusters.net/HelloTome" +
					"?user=mydb&password=43213019");

			if(connect != null){
				str = "Database Connected.";
				System.out.println("Database Connected.");
			} else {
				str = "Database Connect Failed.";
				System.out.println("Database Connect Failed.");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Close
		try {
			if(connect != null){
				connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}

}
