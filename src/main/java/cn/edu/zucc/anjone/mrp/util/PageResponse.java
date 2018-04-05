package cn.edu.zucc.anjone.mrp.util;

import java.util.List;

public class PageResponse {
	//请求的次数
	public int draw;
	//过滤的结果数
	public int recordsTotal;
	//返回的记录数
	public int recordsFiltered;
	
	public List<Object> data;
	
	public PageResponse(List list,int recordsFiltered,int draw){
		this.draw = draw;
		this.recordsFiltered= recordsFiltered;
		this.data = list;
	}
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
}
