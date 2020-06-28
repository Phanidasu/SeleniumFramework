package com.hms.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseReader {
	
	
	public static Connection getMySQLConnection(String hostName,
            String portConn,
            String dbName,
            String userName,
            String passWord)
	
		throws ClassNotFoundException, SQLException {
		
		// With port
		String connectionURL = "jdbc:mysql://" + hostName + ":" + portConn + "/" + dbName+"?serverTimezone=UTC";
		
		// Connection
		Connection conn = DriverManager.getConnection(connectionURL, userName, passWord);
				
		return conn;
		}

	
	   public static String getDataFromTable(String query, String columnName) throws SQLException, ClassNotFoundException {
		   
		   Connection conn = getMySQLConnection("localhost", "3306", "hms", "admin", "admin");
		   Statement stat = conn.createStatement();	
		   ResultSet res = stat.executeQuery(query);
		   String cname = null;
		   
		   if(res.next()) {
				cname =  res.getString(columnName);
				
			}
		     return cname; 
	   }
	   
	   
	   public static void insertData(String query) throws ClassNotFoundException, SQLException {
		   Connection conn = getMySQLConnection("localhost", "3306", "hms", "admin", "admin");
		   Statement stat = conn.createStatement();	
		   stat.executeUpdate(query);   
	   }
	
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		//String postalcode = DataBaseReader.getDataFromTable("select * from customers where city='mumbai'","postalcode" );
		//String customername = DataBaseReader.getDataFromTable("select * from customers where city='mumbai'","customername");
		
		DataBaseReader.insertData("INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)\r\n" + 
				"VALUES ('test', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', '4006', 'Norway')");
		
	}

}
