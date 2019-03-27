package com.packt.mockito.stubbing;

import java.io.Serializable;
import java.util.List;

public class JsonDataWrapper<T> implements Serializable {

	 private static final long serialVersionUID = 1L;
	 //current page
	 private long page;
	 //total number of records for the given entity.
	 private long total;
	 //list of records to be displayed.
	 private List<T> rows;

	 public JsonDataWrapper(long page, long total, List<T> rows) {
	  this.page = page;
	  this.total = total;
	  this.rows = rows;
	 }

	public long getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

	 // getter setter

	}
