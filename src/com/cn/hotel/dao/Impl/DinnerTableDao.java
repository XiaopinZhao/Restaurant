package com.cn.hotel.dao.Impl;

import java.sql.Date;
import java.util.List;

import javax.xml.registry.DeleteException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.cn.hotel.dao.IDinnerTableDao;
import com.cn.hotel.entity.DinnerTable;
import com.cn.hotel.utils.JdbcUtil;
/**
 * 对dinnertable进行操作的实现类
 */
public class DinnerTableDao implements IDinnerTableDao {
	private QueryRunner qr=JdbcUtil.getQuery();
	
	/*
	 * 得到所有的DinnerTable对象
	 */
	public List<DinnerTable> getDinnerTableList() {
		qr=JdbcUtil.getQuery();
		String sql="select * from dinnertable ";
		List<DinnerTable> list=null;
		try {
			 list = qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 根据ID找到餐桌
	 */
	public DinnerTable getDinnerTableById(int id) {
		String sql="select * from dinnertable where id=?";
		DinnerTable dTable=null;
		try{
			dTable=qr.query(sql,new  BeanHandler<DinnerTable>(DinnerTable.class),id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return dTable;
	}

	/*
	 * 添加餐桌
	 */
	public void addDinnerTable(DinnerTable dinnerTable) {
		String sql="insert into dinnertable (tableName,tableStatus,orderDate) values (?,?,?) ";
		try{
			 qr.update(sql,dinnerTable.getTableName(),dinnerTable.getTableStatus(),dinnerTable.getOrderdate());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * 删除餐桌信息
	 */
	public void deletDinnerTable(int id) {
		String sql="delete from dinnertable where id=?";
		try{
			qr.update(sql,id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * 更新
	 */
	public void upDataDinnerTable(DinnerTable dinnerTable) {
		String sql="update dinnertable set tableName=?, tableStatus=?, orderDate=? where id=?";
		try{
			System.out.println("-->updata"+dinnerTable.getTableStatus());
			qr.update(sql,dinnerTable.getTableName(),dinnerTable.getTableStatus(),dinnerTable.getOrderdate(),dinnerTable.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	/*
	 *根据桌子的名称选出适当的数据
	 */
	public List<DinnerTable> query(String keyword) {
		String sql="select * from dinnertable where tableName like?";
		List<DinnerTable> list=null;
		try{
			list=qr.query(sql,new  BeanListHandler<DinnerTable>(DinnerTable.class),"%"+keyword+"%");
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

}
