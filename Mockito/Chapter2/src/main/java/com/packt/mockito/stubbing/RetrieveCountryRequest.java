package com.packt.mockito.stubbing;

public class RetrieveCountryRequest {
	private long page;
	private int rowPerPage;
	private SortColumn sortname;
	private SortOrder sortOrder;
	private String serachQuery;
	private String queryType;

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public SortColumn getSortname() {
		return sortname;
	}

	public void setSortname(SortColumn sortname) {
		this.sortname = sortname;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSerachQuery() {
		return serachQuery;
	}

	public void setSerachQuery(String serachQuery) {
		this.serachQuery = serachQuery;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

}
