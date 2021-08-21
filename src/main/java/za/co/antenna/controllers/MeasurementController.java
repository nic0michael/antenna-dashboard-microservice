package za.co.antenna.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import za.co.antenna.data.adapters.MeasurementAdapter;
import za.co.antenna.dtos.AntennaDto;
import za.co.antenna.servicemanagers.AntennaServiceManager;

@Controller
@RequestMapping("/antenna-dashboard/measurements")
public class MeasurementController {
	private static final Logger log = LoggerFactory.getLogger(MeasurementController.class);

	@Value("${plotter.service.uri}")
	static String plotterServiceUri;

	@Autowired
	MeasurementAdapter measurementAdapter;	

	@Autowired
	AntennaServiceManager antennaServiceManager;

	@GetMapping("/plot")
	public String plotMeasurements(@RequestParam(value = "antennaCode") String antennaCode,
								   @RequestParam(value = "measurementCode") String measurementCode,
								   Model model) {
		log.info("ANTENNA | MeasurementController| plotMeasurements | antenna list called");
		
		String plotterUrl = "",
		       measurementForm = "crtgainfrm";

		switch (measurementCode) {
			case "GAIN" : {
				measurementForm = "crtgainfrm";
				break;
			}
	
			case "HPOLAR" : {
				measurementForm = "crthpolfrm";
				break;
			}
	
			case "VPOLAR" : {
				measurementForm = "crtvpolfrm";
				break;
			}
			
			case "VSWR" : {
				measurementForm = "crtvswrfrm";
				break;
			}
	
			default : {
				measurementForm = "crtgainfrm";
				log.info("ANTENNA | MeasurementController| plotMeasurements | Invalid measurement code: " + measurementCode);
				break;
			}
		}
		
		if (plotterServiceUri != null && !plotterServiceUri.equals("")) {
			plotterUrl = plotterServiceUri +
						 "/" +
						 measurementForm +
						 "?mt=" +					
						 measurementCode +
						 "&ac=" +
						 antennaCode;
			model.addAttribute("plotterUrl", plotterUrl);
			log.info("ANTENNA | MeasurementController| plotMeasurements | plotterUrl = " + plotterUrl);
		}
		else {
			plotterUrl = "http://localhost:8080" +
						 "/" +
						 measurementForm +
						 "?mt=" +					
						 measurementCode +
						 "&ac=" +
						 antennaCode;
			model.addAttribute("plotterUrl", plotterUrl);
			log.info("ANTENNA | MeasurementController| plotMeasurements | plotterUrl is null");
		}

		return "redirect:" + plotterUrl;
	}

	@GetMapping("/listbaseantennas")
	public String listBaseAntennas(Model model) {
		log.info("ANTENNA | MeasurementController | listBaseAntennas | called");

		List<AntennaDto> baseAntennasList = antennaServiceManager.findAllBaseAntennas();
		String antennasListMessage = "";

		if (baseAntennasList == null) {
			antennasListMessage = "Could not retrieve any antennas.";
			log.error("ANTENNA | UploadController | listBaseAntennas | Antennas list is null");
		}
		else {
			antennasListMessage = " " + baseAntennasList.size() + " antennas retrieved.";
			log.info("ANTENNA | UploadController| listBaseAntennas | Number of antennas retrieved: " + baseAntennasList.size());
		}

		model.addAttribute("antennasList", baseAntennasList);
		model.addAttribute("antennasListMessage", antennasListMessage);
		return "measurements/list-measurements";
	}

	@GetMapping("/listsubantennas")
	public String listSubAntennas(Model model) {
		log.info("ANTENNA | MeasurementController | listSubAntennas | called");		

		List<AntennaDto> subAntennasList = antennaServiceManager.findAllSubAntennas();
		String antennasListMessage = "";

		if (subAntennasList == null) {
			antennasListMessage = "Could not retrieve any antennas.";
			log.error("ANTENNA | UploadController | listSubAntennas | Antennas list is null");
		}
		else {
			antennasListMessage = " " + subAntennasList.size() + " antennas retrieved.";
			log.info("ANTENNA | UploadController| listSubAntennas | Number of antennas retrieved: " + subAntennasList.size());
		}

		model.addAttribute("antennasList", subAntennasList);
		model.addAttribute("antennasListMessage", antennasListMessage);
		return "measurements/list-measurements";
	}
}
