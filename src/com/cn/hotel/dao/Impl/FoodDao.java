package com.cn.hotel.dao.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cn.hotel.dao.IFoodDao;
import com.cn.hotel.entity.Food;
import com.cn.hotel.utils.Condition;
import com.cn.hotel.utils.JdbcUtil;
import com.cn.hotel.utils.PageBean;
/**
 * 对food进行操作的实现类
 */
public class FoodDao implements IFoodDao {
	
	private QueryRunner qr=JdbcUtil.getQuery();
	
	/*
	 * 得到所有的食物信息
	 */
	public List<Food> getFoodList() {
		String sql="select * from food";
		List<Food> list=null;
		try{
			list=qr.query(sql,new  BeanListHandler<Food>(Food.class));
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * 根据id找到食物信息
	 */
	public Food findFoodById(int id) {
		String sql="select * from food where id=?";
		Food food=null;
		try{
			food=qr.query(sql,new  BeanHandler<Food>(Food.class),id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return food;
	}

	/*
	 * 更新食物信息
	 */
	public void updateFood(Food food) {
		String sql="update food set foodName=?,foodType_id=?,price=?,mprice=?,remark=?,img=? where id=?";
		try{
			qr.update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),food.getMprice(),food.getRemark(),food.getImg(),food.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * 根据食物ID删除食物信息
	 */
	public void deleteFood(int id) {
		String sql="delete from food where id=?";
		try{
			qr.update(sql,id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * 增加一条食物信息
	 */
	public void addFood(Food food){
		String sql="insert into food (foodName,foodType_id,price,mprice,remark,img) values (?,?,?,?,?,?)";
		try{
			qr.update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),food.getMprice(),food.getRemark(),food.getImg());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * 得到一共有多少页数据
	 */
	public int getTotalCount(PageBean<Food> pb) {
		int total=0;
		StringBuilder sb=new StringBuilder();
		List<Object> list=new ArrayList<Object>();
		sb.append("select ");
		sb.append("		count(*) ");
		sb.append("from ");
		sb.append("		foodtype ft,");
		sb.append("		food f ");
		sb.append("where 1=1");
		sb.append("		and ft.id=f.foodType_id");
		
		Condition condition=pb.getCondition();
		if(condition!=null){
			String foodName=condition.getFoodName();
			if(foodName!=null&&!"".equals(foodName.trim())){
				sb.append("       and f.foodName like ?");
				list.add("%"+foodName+"%");
			}
			int type_id=condition.getFoodType_id();
			if(type_id>0){
				sb.append("        and f.foodType_id=?");
				list.add(type_id);
			}
		}
		
		try{
			Long to=qr.query(sb.toString(),new ScalarHandler<Long>(),list.toArray());
			total=to.intValue();
		}catch(Exception e){
			e.printStackTrace();
		}
		return total;
	}

	/*
	 * 查找所有符合条件的信息并放到pagedate属性中
	 */
	public void getAll(PageBean<Food> pb) {
		//查询总记录数，放到pb对象中
		int totalCount=this.getTotalCount(pb);
		pb.setTotalCount(totalCount);
		pb.totalPage();
		
		List<Object> list=new ArrayList<Object>();
		//判断当前页是否合法
		if(pb.getCurrentPage()<=0){
			pb.setCurrentPage(1);
		}else if(pb.getCurrentPage()>pb.getTotalPage()){
			pb.setCurrentPage(pb.getTotalPage());
		}
		
		//获取当前页，计算查询的起始行，放回的行数
		int curPage=pb.getCurrentPage();
		System.out.println("currentPage ---->"+curPage);
		int index=(curPage-1)*pb.getPageCount();
		int count=pb.getPageCount();
		Condition condition=pb.getCondition();
		
		//分页查询数据，把查询到的数据放到pb对象中
		StringBuilder sb=new StringBuilder();
		sb.append("select ");
		sb.append("		f.id,");
		sb.append("		f.foodName,");
		sb.append("		f.foodType_id,");
		sb.append("		f.price,");
		sb.append("		f.mprice,");
		sb.append("		f.remark,");
		sb.append("		f.img,");
		sb.append("		ft.typeName   ");
		sb.append("from");
		sb.append("		food f,");
		sb.append("		foodtype ft");
		sb.append("		where 1=1");
		sb.append("			and f.foodType_id=ft.id");
		if(condition!=null){
			String foodName=condition.getFoodName();
			if(foodName!=null&&!"".equals(foodName.trim())){
				sb.append("         and f.foodName like ?");
				list.add("%"+foodName+"%");
			}
			int type_id=condition.getFoodType_id();
			if(type_id>0){
				sb.append("           and f.foodType_id =?");
				list.add(type_id);
			}
		}
		sb.append(" limit ?,? ");
		list.add(index);
		list.add(count);
		try{	
			List<Food> query = qr.query(sb.toString(),new  BeanListHandler<Food>(Food.class),list.toArray());
			pb.setPageData(query);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	

}
