package com.packt.mockito.stubbing;

import javax.servlet.http.HttpServletRequest;

public class RequestBuilder {

	public static RetrieveCountryRequest build( HttpServletRequest webReq) {
		RetrieveCountryRequest request = new RetrieveCountryRequest();
		request.setPage(getLong(webReq.getParameter("page")));
		request.setRowPerPage(getInt(webReq.getParameter("rp")));
		request.setSortOrder(SortOrder.find(webReq.getParameter("sortorder")));
		request.setSortname(SortColumn.find(webReq.getParameter("sortname")));
		request.setSerachQuery(webReq.getParameter("qtype"));
		
		return request;
	}

	private static Integer getInt(String val) {
		Integer retVal = null;
		try {
			retVal = Integer.parseInt(val);
		} catch (Exception e) {
		}

		return retVal;
	}
	
	private static Long getLong(String val) {
		Long retVal = null;
		try {
			retVal = Long.parseLong(val);
		} catch (Exception e) {
		}

		return retVal;
	}
}
