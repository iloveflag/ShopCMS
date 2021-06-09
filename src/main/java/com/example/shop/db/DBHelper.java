package com.example.shop.db;

import java.sql.*;
import javax.naming.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBHelper {
	private static Logger logger = LoggerFactory.getLogger(DBHelper.class);
	
	//------ 下面是使用MySQL的设置 -------
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3306/shop";
	private static String user = "root";
	private static String pwd = "root";

	public static Connection getConnection2() {
		javax.sql.DataSource ds = null;
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			ds = (javax.sql.DataSource) ctx.lookup("java:comp/env/jdbc/shop");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch (NamingException ne) {
			logger.error(ne.getMessage());
			ne.printStackTrace();
		} catch (SQLException se) {
			logger.error(se.getMessage());
			se.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) { }
			conn = null;
		}
	}
	
	public static void close(Statement stmt, ResultSet rs) {
    	if (stmt != null) {
    		try {
    			stmt.close();
    		} catch (SQLException e) {}
    	}
    	if (rs != null) {
    		try {
    			rs.close();
    		} catch (SQLException e) {}
    	}
    }

}
