package com.atguigu.mvcapp.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 *  JDBC 操作的工具类
 *
 */
public class JdbcUtils {

	private static DataSource dateSource = null;
	
	static {
		// 数据源只能被创建一次
		dateSource = new ComboPooledDataSource("mvcapp");
	}
	/**
	 * 返回数据源的一个 Connection 对象
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return dateSource.getConnection();
	}
	
	/**
	 * 释放 Connection
	 * @param connection
	 */
	public static void releaseConnection(Connection connection) {
		
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
