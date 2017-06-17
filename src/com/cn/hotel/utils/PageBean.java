package com.cn.hotel.utils;

import java.util.List;
/**
 * 分页工具类
 * @author Administrator
 *
 * @param <T>
 */
public class PageBean<T> {
	private int currentPage;    //当前页
	private int pageCount;    //每页有多少条记录
	private int totalCount;		//一共有多少条记录
	private int totalPage;	//每页有多少条记录
	private List<T> pageData;     //每一页存放的数据
	private Condition condition;     //帮助分页的辅助对象
	
	public void totalPage(){
		if(totalCount%pageCount==0){
			totalPage=totalCount/pageCount;
		}else{
			totalPage=(totalCount/pageCount)+1;
		}
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getPageData() {
		return pageData;
	}
	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
}
