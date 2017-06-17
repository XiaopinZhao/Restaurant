package com.cn.hotel.utils;

import javax.management.Query;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 数据库连接类
 *
 */
public class JdbcUtil {
	//定义一个datasource对象
	private static DataSource dataSource;
	static{
		dataSource=new ComboPooledDataSource();
	}
	
	public DataSource getDataSource(){
		return dataSource;
	}
	
	/*
	 * 
	 * dbUtils常用工具类对象
	 */
	public static QueryRunner getQuery(){
		return new QueryRunner(dataSource);
	}

}
