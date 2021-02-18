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

@RestController
public class EmployeeController {
	
	@RequestMapping("/getEmployees")
    public List<Employee> getEmployees() 
    {
		List<Employee> employeesList = new ArrayList<Employee>();
		String str = "not pass";
		try {
			Connection con = getConnection();;
			str = "pass";
		}catch (Exception e){
			e.printStackTrace();
		}


		str = connectDB();

		employeesList.add(new Employee(1,str,"gupta","howtodoinjava@gmail.com"));
		return employeesList;
    }

	private static Connection getConnection() throws URISyntaxException, Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String dbUrl =  "mysql://us-cdbr-east-03.cleardb.com/heroku_70451cd8b61281c";
		return DriverManager.getConnection(dbUrl, "b93dc6f07df6ea", "313d4648");
	}

	private String connectDB() {
		
		Connection connect = null;
		String str="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionStringURL = "mysql://us-cdbr-east-03.cleardb.com/heroku_70451cd8b61281c";
			connect = DriverManager.getConnection(connectionStringURL, "b93dc6f07df6ea", "313d4648");

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
