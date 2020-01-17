package database;

import java.sql.DriverManager;

public interface DatabaseInfo {
	static String url = "jdbc:mysql://localhost:3306/feedback";
	static String userName = "root";
	static String password = "";
	static java.sql.Connection getConnection() throws Exception
	{
		return DriverManager.getConnection(DatabaseInfo.url, DatabaseInfo.userName, DatabaseInfo.password);
	}
	
	}
	
