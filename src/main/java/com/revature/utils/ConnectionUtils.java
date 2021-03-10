package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtils {
	private static ConnectionUtils cu = null;
	private static Properties props;
	
	private ConnectionUtils() {
		props = new Properties();
		
		try {
			InputStream dbProps = ConnectionUtils.class.getClassLoader().
				getResourceAsStream("database.properties");
			props.load(dbProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionUtils getConnectionUtil() {
		if (cu == null)
			cu = new ConnectionUtils();
		return cu;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName(props.getProperty("drv"));
			conn = DriverManager.getConnection(
						props.getProperty("url"),
						props.getProperty("usr"),
						props.getProperty("psw")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
