package br.com.adminfo.relatorios.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import br.com.adminfo.relatorios.model.City;
import br.com.adminfo.relatorios.model.PdfUtils;
import br.com.adminfo.relatorios.model.ReportChart;
import br.com.adminfo.relatorios.model.State;
import br.com.adminfo.relatorios.services.CityService;
import br.com.adminfo.relatorios.services.StateService;

@Controller
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;
	
	public ReportController(StateService stateService, CityService cityService) {
		this.stateService = stateService;
		this.cityService = cityService;
	}
	
	@GetMapping("/html")
	public String html(@RequestParam(required = false, value = "state") Long id, Model model){
		model.addAttribute("states", stateService.findAll());
		
		if(id == null) {
			return "report/index";
		}
		
		model.addAttribute("list", cityService.reportAllCitiesByState(id));
		return "report/index";
	}
	
	@GetMapping("/chart")
	public String chart(Model model){
		List<ReportChart> list = stateService.countCitiesByState();
		model.addAttribute("labels", list.stream().map(ReportChart::getName).collect(Collectors.toList()));
		model.addAttribute("data", list.stream().map(ReportChart::getTotal).collect(Collectors.toList()));
		return "report/chart";
	}
	
	@GetMapping("/pdf")
	public ResponseEntity<InputStreamResource> pdf(@RequestParam(required = false, value = "state") Long id, Model model){
		model.addAttribute("states", stateService.findAll());
		
		if(id == null) {
			return null;
		}
		
		List<City> list = cityService.reportAllCitiesByState(id);
		ByteArrayInputStream pdf = PdfUtils.generatePdf(list);
		return ResponseEntity.ok().header("Content-Disposition",  "inline; filename=report.df").contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
	}
	
	@GetMapping("/csv")
	public void csv(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Content-Disposition", "attachment filename=csv.csv");
		String[] header = {"id", "name"};
		
		ICsvBeanWriter csvBeanWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		csvBeanWriter.writeHeader(header);
		
		List<State> states = stateService.findAll();
		
		for(State state : states) {
			csvBeanWriter.write(state, header);
		}
		
		csvBeanWriter.close();
	}
	
}