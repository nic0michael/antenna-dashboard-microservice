package za.co.antenna.controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import za.co.antenna.data.adapters.AntennaAdapter;
import za.co.antenna.dtos.AntennaDto;
import za.co.antenna.dtos.AntennaPersistRequest;
import za.co.antenna.servicemanagers.AntennaServiceManager;
import za.co.antenna.utils.Utils;

@Controller
@RequestMapping("/antenna-dashboard/subantennas")
public class SubAntennaController {
	private static final Logger log = LoggerFactory.getLogger(SubAntennaController.class);

	@Autowired
	AntennaServiceManager antennaServiceManager;

	@Autowired
	AntennaAdapter antennaAdapter;

	@GetMapping
	public String displayAntennas(Model model) {
		return displayHome(model);
	}
	
	@GetMapping("/list")
	public String displayHome(Model model) {
		log.info("ANTENNA | AntennaController| displayListAntennasPage | antenna list called");
		List<AntennaDto> antennasList = antennaServiceManager.findAllSubAntennas();
		if(antennasList==null) {
			log.error("ANTENNA | AntennaController| displayListAntennasPage | antenna listis null");
		} else {
			log.info("ANTENNA | AntennaController| displayListAntennasPage | antenna list size : "+antennasList.size());
		}
		
		model.addAttribute("antennasList", antennasList);
		return "subantennas/list-antennas";
	}
	
	@GetMapping("/new")
	public String displayAmntennaForm(Model model) {
		List<AntennaDto> baseAntennasList = antennaServiceManager.findAllBaseAntennas();
		AntennaPersistRequest antennaPersistRequest=new AntennaPersistRequest();
		antennaPersistRequest.setAction("INSERT");
		antennaPersistRequest.setAntennaTypeCode("S");
		model.addAttribute("antennaPersistRequest", antennaPersistRequest);
		model.addAttribute("baseAntennasList", baseAntennasList);
		log.info("ANTENNA : AntennaController : displayTaskForm : displaying form");
		return "subantennas/new-antenna";
	}

	@PostMapping("/save")
	public String createAntenna( AntennaPersistRequest antennaPersistRequest,Model model) {
		log.info("ANTENNA | AntennaController | createAntenna | AntennaPersistRequest: "+antennaPersistRequest);
		
		String sqlDate=Utils.makeMysqlDateNow();
		antennaPersistRequest.setDateCreated(sqlDate);

		
		if(StringUtils.isNotBlank(antennaPersistRequest.getAntennaId() )  && StringUtils.isNumeric(antennaPersistRequest.getAntennaId()) ) {				
			log.info("ANTENNA | AntennaController | createAntenna |  updating antenna");
			antennaServiceManager.updateAntenna(antennaPersistRequest);
		} else {
			log.info("ANTENNA | AntennaController | createAntenna | saving new antenna");
			antennaServiceManager.insertSubAntenna(antennaPersistRequest);
		}
		

		return "redirect:/antenna-dashboard/subantennas";

	}

	@GetMapping("/maakdood")
	public String removeTask(@RequestParam(value = "antennaCode") String antennaCode, Model model) {
		log.info("ANTENNA : AntennaController : removeTask : to delete antenna with antennaCode : "+antennaCode);
		antennaServiceManager.deleteAntenna(antennaCode);
		return "redirect:/antenna-dashboard/subantennas";
	}
	
	@GetMapping("/verander")
	public String displayTaskFormToUpdate(@RequestParam(value = "antennaCode") String antennaCode, Model model) {
		log.info("ANTENNA : AntennaController : displayTaskFormToUpdate : update Antenna : with antennaCode : "+ antennaCode);
		if(antennaCode!=null) {
			log.info("ANTENNA : AntennaController : displayAntennaFormToUpdate : finding antennaCode : "+antennaCode);

			List<AntennaDto> baseAntennasList = antennaServiceManager.findAllBaseAntennas();
			AntennaPersistRequest antennaPersistRequest=null;
			log.info("ANTENNA : AntennaController : displayTaskFormToUpdate : update Antenna : searching for antenna with antennaCode : "+ antennaCode);
			AntennaDto antennaDto=antennaServiceManager.findByAntennaCode(antennaCode);
			log.info("ANTENNA : AntennaController : displayTaskFormToUpdate : update Antenna : got antennaDto : "+antennaDto);
			
			if(antennaDto!=null) {
				log.info("ANTENNA : AntennaController : displayTaskFormToUpdate : update Antenna : found antennaDto : "+antennaDto);
				antennaPersistRequest=AntennaAdapter.makeAntennaPersistRequest(antennaDto);
				antennaPersistRequest.setAction("UPDATE");
				antennaPersistRequest.setAntennaTypeCode("S");
				log.info("ANTENNA : AntennaController : displayAntennaFormToUpdate : found antenna  : AntennaPersistRequest : "+antennaPersistRequest);
				model.addAttribute("antennaPersistRequest", antennaPersistRequest);
				model.addAttribute("baseAntennasList", baseAntennasList);
				return "subantennas/new-antenna";
			}else {
				log.error("ANTENNA : AntennaController : displayAntennaFormToUpdate : received a null AntennaDto ");
			}
		}
		log.info("ANTENNA : AntennaController : displayEmployeetFormToUpdate : displaying antenna list");
		return "subantennas/list-antennas";	
	}
}
