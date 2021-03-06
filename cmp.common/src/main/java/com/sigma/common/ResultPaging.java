package com.sigma.common;

import java.util.List;

public class ResultPaging{
	
	private int page;
	private int size;
	private List<?> list;
	private int count;
	
	public ResultPaging(){}
	
	public ResultPaging(int page, int size){
		this.setPage(page);
		this.setSize(size);
	}
	
	public ResultPaging(int page, int size, List<?> list){
		this.setPage(page);
		this.setSize(size);
		this.setList(list);
	}
	
	public ResultPaging(int page, int size, int count, List<?> list){
		this.setPage(page);
		this.setSize(size);
		this.setCount(count);
		this.setList(list);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}
