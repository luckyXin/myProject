package com.audit.common;

import java.util.ArrayList;
import java.util.List;

public class GridDataModel<T> {
	private Integer total;

	private List<T> rows = new ArrayList<T>();

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
