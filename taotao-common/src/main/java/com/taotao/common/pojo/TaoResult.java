package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

public class TaoResult<T> implements Serializable{

	private long total;

	private List<T> rows;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
