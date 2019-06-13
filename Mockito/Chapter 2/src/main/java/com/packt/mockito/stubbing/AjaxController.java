package com.packt.mockito.stubbing;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("session")
public class AjaxController {
	private final CountryDao countryDao;

	public AjaxController(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	@RequestMapping(value = "retrieveCountries", method = RequestMethod.POST)
	public @ResponseBody
	JsonDataWrapper<Country> retrieve(HttpServletRequest webRequest) {
		List<Country> countries = new ArrayList<Country>();
		RetrieveCountryRequest request = RequestBuilder.build(webRequest);
		countries = countryDao.retrieve(request);
		Long startIndex = (request.getPage() - 1) * (request.getRowPerPage());
		int size = countries.size();
		Long endIndex = (startIndex + request.getRowPerPage()) > size ? size
				: (startIndex + request.getRowPerPage());
		if (startIndex < endIndex) {
			countries = countries.subList(startIndex.intValue(),
					endIndex.intValue());
		}

		JsonDataWrapper<Country> wrapper = new JsonDataWrapper<Country>(
				request.getPage(), size, countries);

		return wrapper;
	}

}
