package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GetDBConnectionUtil {
	static String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static String USER = "root";
	static String PASS = "Lecaotuanvu.2004_mysql";

	public static Connection getConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection failed");
		}
		return conn;
	}

}
