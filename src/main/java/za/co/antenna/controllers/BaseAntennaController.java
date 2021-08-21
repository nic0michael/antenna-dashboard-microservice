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
@RequestMapping("/antenna-dashboard/baseantennas")
public class BaseAntennaController {
	private static final Logger log = LoggerFactory.getLogger(BaseAntennaController.class);
	
	@Autowired
	AntennaServiceManager antennaServiceManager;	
	

	@GetMapping
	public String displayBaseAntennas(Model model) {
		List<AntennaDto> antennasList = antennaServiceManager.findAllBaseAntennas();
		if(antennasList==null) {
			log.error("ANTENNA | BaseAntennaController| displayBaseAntennas | base antenna listis null");
		} else {
			log.info("ANTENNA | BaseAntennaController| displayBaseAntennas | base antenna list size : "+antennasList.size());
		}
		
		model.addAttribute("antennasList", antennasList);
		return "baseantennas/list-baseantennas";
	}
	
	@GetMapping("/list")
	public String displayBaseAntennaHome(Model model) {
		List<AntennaDto> antennasList = antennaServiceManager.findAllBaseAntennas();
		if(antennasList==null) {
			log.error("ANTENNA | BaseAntennaController| displayBaseAntennaHome | base antenna listis null");
		} else {
			log.info("ANTENNA | BaseAntennaController| displayBaseAntennaHome | base antenna list size : "+antennasList.size());
		}
		
		model.addAttribute("antennasList", antennasList);
		return "baseantennas/list-baseantennas";
	}
	

	
	@GetMapping("/listsub")
	public String displaySubAntennaHome(Model model) {
		List<AntennaDto> antennasList = antennaServiceManager.findAllSubAntennas();
		if(antennasList==null) {
			log.error("ANTENNA | BaseAntennaController| displayBaseAntennaHome | base antenna listis null");
		} else {
			log.info("ANTENNA | BaseAntennaController| displayBaseAntennaHome | base antenna list size : "+antennasList.size());
		}
		
		model.addAttribute("antennasList", antennasList);
		return "baseantennas/list-subantennas";
	}
	
	@GetMapping("/new")
	public String displayBaseAntennaForm(Model model) {
		log.info("ANTENNA : SubtaskController : displayBaseAntennaForm : creating new BaseAntenna");
		

		AntennaPersistRequest antennaPersistRequest=new AntennaPersistRequest();
		antennaPersistRequest.setAction("INSERT");
		antennaPersistRequest.setAntennaTypeCode("B");
		model.addAttribute("antennaPersistRequest", antennaPersistRequest);
		log.info("ANTENNA : BaseAntennaController : displayBaseAntennaForm : displaying form");
		

		return "baseantennas/new-baseantenna";
	}

	
	@GetMapping("/newsub")
	public String displaySubAntennaForm(Model model) {
		log.info("ANTENNA : SubtaskController : displayBaseAntennaForm : creating new BaseAntenna");

		List<AntennaDto> baseAntennasList = antennaServiceManager.findAllBaseAntennas();
		AntennaPersistRequest antennaPersistRequest=new AntennaPersistRequest();
		antennaPersistRequest.setAction("INSERT");
		antennaPersistRequest.setAntennaTypeCode("S");

		model.addAttribute("baseAntennasList", baseAntennasList);
		model.addAttribute("antennaPersistRequest", antennaPersistRequest);
		log.info("ANTENNA : BaseAntennaController : displayBaseAntennaForm : displaying form");
		

		return "baseantennas/new-subantenna";
	}

	@PostMapping("/save")
	public String createBaseAntenna( AntennaDto antennaDto,Model model) {
		antennaDto.setMainAntennaCode(antennaDto.getAntennaCode());
		antennaDto.setAntennaTypeCode("B");
		log.info("ANTENNA : BaseAntennaController : createBaseAntenna : got  antennaDto: "+antennaDto);
		AntennaPersistRequest antennaPersistRequest=AntennaAdapter.makeAntennaPersistRequest(antennaDto);
		if(antennaPersistRequest!=null && StringUtils.isNotBlank(antennaPersistRequest.getAntennaId() )  && StringUtils.isNumeric(antennaPersistRequest.getAntennaId()) ) {				
			log.info("ANTENNA : BaseAntennaController : createBaseAntenna : updating base antenna");
			
			String sqlDate=Utils.makeMysqlDateNow();
			antennaPersistRequest.setDateCreated(sqlDate);
			
			log.info("ANTENNA : BaseAntennaController : createBaseAntenna : saving Antenna   antennaDto: "+antennaDto);
		
			antennaServiceManager.updateAntenna(antennaPersistRequest);
		} else {
			log.info("ANTENNA : BaseAntennaController : createBaseAntenna : saving new task");
			antennaServiceManager.insertBaseAntenna(antennaPersistRequest);
		}
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/antenna-dashboard/baseantennas/list";
	}
	

	@PostMapping("/savesub")
	public String createSubAntenna( AntennaDto antennaDto,Model model) {
		antennaDto.setAntennaTypeCode("S");
		log.info("ANTENNA : BaseAntennaController : createSubAntenna : got  antennaDto: "+antennaDto);
		AntennaPersistRequest antennaPersistRequest=AntennaAdapter.makeAntennaPersistRequest(antennaDto);
		if(antennaPersistRequest!=null && StringUtils.isNotBlank(antennaPersistRequest.getAntennaId() )  && StringUtils.isNumeric(antennaPersistRequest.getAntennaId()) ) {				
			log.info("ANTENNA : BaseAntennaController : createSubAntenna : updating base antenna");
			
			String sqlDate=Utils.makeMysqlDateNow();
			antennaPersistRequest.setDateCreated(sqlDate);
			
			log.info("ANTENNA : BaseAntennaController : createSubAntenna : saving Antenna   antennaDto: "+antennaDto);
		
			antennaServiceManager.updateAntenna(antennaPersistRequest);
		} else {
			log.info("ANTENNA : BaseAntennaController : createSubAntenna : saving new task");
			antennaServiceManager.insertSubAntenna(antennaPersistRequest);
		}
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/antenna-dashboard/baseantennas/listsub";
	}

	@GetMapping("/maakdood")
	public String removeBaseAntennas(@RequestParam(value = "antennaCode") String antennaCode, Model model) {
		log.info("ANTENNA : BaseAntennaController : removeBaseAntennas : antennaCode : "+antennaCode);
		antennaServiceManager.deleteAntenna(antennaCode);
		return "redirect:/antenna-dashboard/baseantennas";
	}


	@GetMapping("/maakdoodsub")
	public String removeSubAntennas(@RequestParam(value = "antennaCode") String antennaCode, Model model) {
		log.info("ANTENNA : BaseAntennaController : removeBaseAntennas : antennaCode : "+antennaCode);
		antennaServiceManager.deleteAntenna(antennaCode);
		return "redirect:/antenna-dashboard/baseantennas/listsub";
	}
	
	@GetMapping("/verander")
	public String displayBaseAntennasFormToUpdate(@RequestParam(value = "antennaCode") String antennaCode, Model model) {
		log.info("ANTENNA : BaseAntennaController : displayBaseAntennasFormToUpdate : update BaseAntenna : with id : "+antennaCode);
		if(antennaCode!=null) {
			log.info("ANTENNA : BaseAntennaController : displayBaseAntennasFormToUpdate : finding antenna id : "+antennaCode);
			AntennaDto antennaDto=antennaServiceManager.findByAntennaCode(antennaCode);
			log.info("ANTENNA : BaseAntennaController : displayBaseAntennasFormToUpdate : found antenna  : "+antennaDto.getAntennaId());
			if(antennaDto!=null) {
				log.info("ANTENNA : BaseAntennaController : displayAntennaFormToUpdate : created AntennaDto : "+antennaDto);
				model.addAttribute("antennaDto", antennaDto);
			}
		}
		log.info("ANTENNA : BaseAntennaController : displayBaseAntennasFormToUpdate : displaying form");
		return "baseantennas/edit-baseantenna";		
	}
	

	
	@GetMapping("/verandersub")
	public String displaySubAntennasFormToUpdate(@RequestParam(value = "antennaCode") String antennaCode, Model model) {
		log.info("ANTENNA : BaseAntennaController : displayBaseAntennasFormToUpdate : update SubAntenna : with id : "+antennaCode);
		
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
				return "baseantennas/new-subantenna";		
			}else {
				log.error("ANTENNA : AntennaController : displayAntennaFormToUpdate : received a null AntennaDto ");
			}
		}
		log.info("ANTENNA : AntennaController : displayEmployeetFormToUpdate : displaying antenna list");
				
		
		return "baseantennas/new-subantenna";		
	}
}
