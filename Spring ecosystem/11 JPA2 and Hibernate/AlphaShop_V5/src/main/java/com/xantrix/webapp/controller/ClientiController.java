package com.xantrix.webapp.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xantrix.webapp.service.ClientiService;
import com.xantrix.webapp.entities.Clienti;

@Controller
@RequestMapping("/clienti")
public class ClientiController 
{
	private static final Logger logger = LoggerFactory.getLogger(ClientiController.class);
	
	@Autowired
	private ClientiService clientiService;
	
	List<Clienti> MainRecordset;
	
	private String OrderType = "DESC";
	private int OrderBy = 0;
	
	List<PagingData> Pages = new ArrayList<PagingData>();
	private int PageNum = 1;
	private int RecForPage = 10;
	
	private boolean IsClienti = true;
	
	private void GetAllClienti()
	{
		MainRecordset = clientiService.SelTutti();
	}
	
	private void setPages(int Page)
	{
		int Min = 1;
		int ValMin = 1;
		int Max = 5;
		
		if (Pages != null)
			Pages.clear();
		
		int Group = (int) Math.ceil((double)Page / 5);
		
		Max = Group * 5;
		Min = (Max - 5 == 0) ? 1 : Max - 4;
		
		ValMin = Min;
		
		while (Min <= Max)
		{
			Pages.add(new PagingData(Min,false));
			
			Min++;
		}
		
		if (Page - ValMin > 0)
			Pages.get(Page - ValMin).setIsSelected(true);
		else
			Pages.get(0).setIsSelected(true);
	}
	
	@GetMapping
	public String GetClienti(Model model)
	{
		logger.info("Otteniamo tutti i clienti");
		
		List<Clienti> recordset = null;
		long NumRecords = 0;
		
		GetAllClienti();
		
		if (MainRecordset != null)
		{
			 NumRecords = MainRecordset.size();
			
			 recordset = MainRecordset
					 .stream()
					 .filter(u -> !u.getCodFidelity().equals("-1"))
					 .skip(0)
					 .limit(RecForPage)
					 .sorted(Comparator.comparing(Clienti::getCodFidelity).reversed())
					 .collect(Collectors.toList());
		}
		
		logger.info("Numero di record per pagina: " + RecForPage);
		
		setPages(PageNum);
		
		model.addAttribute("Titolo", "Ricerca Clienti");
		model.addAttribute("Titolo2", "Risultati Ricerca ");
		model.addAttribute("NumRecords", NumRecords);
		model.addAttribute("clienti", recordset);
		model.addAttribute("filter", "");
		model.addAttribute("OrderType", OrderType);
		model.addAttribute("OrderBy", OrderBy);
		model.addAttribute("PageNum", PageNum);
		model.addAttribute("RecPage", RecForPage);
		model.addAttribute("Pages", Pages);
		model.addAttribute("IsClienti", IsClienti);

		return "clienti";
		
		
	}
}
