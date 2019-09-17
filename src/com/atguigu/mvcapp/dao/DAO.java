package com.atguigu.mvcapp.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.mvcapp.db.JdbcUtils;

/**
  * 封装了基本的 CRUD 的方法,以供子类继承和使用
  * 当前 DAO 直接在方法中获取数据库连接.
  * @param <T>: 当前 DAO 处理的实体类的类型是什么. 
 */
public class DAO<T> {
	
	private QueryRunner queryRunner = new QueryRunner();
	
	private Class<T> clazz;
	
	// 在构造器中确定 clazz
	
	public DAO() {
		// 首先获取子类对应的Class；然后得到父类带泛型的那个类型
		Type superClass = getClass().getGenericSuperclass();
		//
		if(superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			
			Type [] typeArgs = parameterizedType.getActualTypeArguments();
			
			if(typeArgs != null && typeArgs.length > 0) {
				// 如果第一个参数是 Class的话
				if(typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0];
				}
			}
		}
		
	}
	
	
	/**
	  * 返回某一个字段的值,例如返回某一天记录的 customerName, 或返回数据表中有多少条记录等.
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql,Object ...args) {
		
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
		
		return null;
	}
	
	
	/**
	  * 返回 T 所对应的 List
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getForList(String sql,Object ...args){
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}
	
	
	
	/**
	  * 返回对应的 T 的一个实例类的对象.
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql,Object ...args) {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
		
		
		return null;
	}
	/**
	  * 该方法封装了 INSERT、DELETE、UPDATE 操作.
	  * 整个 DAO 采用 DBUtils 解决方案.
	 * @param sql: SQL 语句
	 * @param args: 填充 SQL 语句的占位符.
	 */
	public void update(String sql, Object ...args) {
		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			queryRunner.update(connection, sql, args);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
		
	}
	
	
}
