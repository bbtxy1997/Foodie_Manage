package com.qst.foodie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

/**
 * 数据库操作类
 * @author 李卓阳
 *
 */
public class DBUtil {
	private static BasicDataSource dataSource = null;
	private static Connection conn = null;
	
	public DBUtil(){
	}
	
	/**
	 * 初始化数据库连接池
	 */
	public static void init(){
		if (dataSource != null){
			try {
				dataSource.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dataSource = null;
		}
		//使用Properties对象定义数据库连接池信息
		try {
			Properties p = new Properties();
			p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
			p.setProperty("url", "jdbc:mysql://localhost:3306/wsdc?useUnicode=true&characterEncoding=utf-8");
			p.setProperty("username", "root");
			p.setProperty("password", "000");
			p.setProperty("maxActive", "30");				//设置处于活动状态的数据库连接的最大数目，0表示不受限制
			p.setProperty("maxIdle", "10");					//设置处于空闲状态的数据库连接的最大数目，0表示不受限制
			p.setProperty("maxWait", "1000");				//设置没有处于空闲状态的连接时，请求数据库连接所需的最长等待时间（单位ms），如果超出改时间将抛出异常，-1表示无限期等待
			p.setProperty("removeAbandoned", "false");
			p.setProperty("removeAbandonedTimeout", "120");
			p.setProperty("testOnBorrow", "true");
			p.setProperty("logAbandoned", "true");
			//以指定信息创建数据源
			dataSource = (BasicDataSource)BasicDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库连接
	 * @return Connection对象
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:mysql://localhost:3306/wsdc?useUnicode=true&characterEncoding=utf-8";
		String user = "root";
		String psw = "000";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,psw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 关闭数据库连接
	 * @param rs
	 * @param stmt
	 * @param conn
	 */
	public static void closeJDBC(ResultSet rs, Statement stmt, Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}