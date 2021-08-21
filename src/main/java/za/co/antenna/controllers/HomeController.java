package za.co.antenna.controllers;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import za.co.antenna.dtos.AntennaDto;
import za.co.antenna.dtos.MeasurementRequest;
import za.co.antenna.servicemanagers.AntennaServiceManager;
import za.co.antenna.servicemanagers.EmployeeServiceManager;
import za.co.antenna.servicemanagers.MeasurementServiceManager;



@Controller
public class HomeController {
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private EmployeeServiceManager emplmod;
	
	
	@Value("${project.version}")
	private String projectVersion;
	
	@Value("${project.name}")
	private String projectName;

	@GetMapping("antenna-dashboard") //antenna-dashboard
	public String displayHome1(Model model) {
		return "redirect:/antenna-dashboard/home";
	}
	
	@GetMapping("/home")
	public String displayHome2(Model model) {
		return "redirect:/antenna-dashboard/home";
	}
	
	@GetMapping
	public String displayHome(Model model) {
		return "redirect:/antenna-dashboard/home";
	}

	@GetMapping("/antenna-dashboard/home")
	public String displayHomePage(Model model) {	
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String simpleDate = simpleDateFormat.format(new Date());
		model.addAttribute("simpleDate", simpleDate);
		model.addAttribute("timestamp", Instant.now());
		
		model.addAttribute("projectVersion", projectVersion);
		model.addAttribute("projectName", projectName);

		return "main/home";
	}
	


}
