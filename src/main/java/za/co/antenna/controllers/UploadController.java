package za.co.antenna.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import za.co.antenna.dtos.FileImageDto;
import za.co.antenna.dtos.FileImageMeasurementDto;
import za.co.antenna.dtos.FileImageMeasurementPersistResponse;

import za.co.antenna.servicemanagers.UploadServiceManager;

@Controller
@RequestMapping("/antenna-dashboard/fileuploads")
public class UploadController {
	private static final Logger log = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	UploadServiceManager uploadServiceManager;	
	
	@GetMapping("/list")
	public String listFileImages(Model model) {
		log.info("ANTENNA | UploadController | listFileImages | called");
		
		List<FileImageDto> fileImages = uploadServiceManager.findAllFileImages();
		String fileImagesListMessage = "";

		if (fileImages == null) {
			fileImagesListMessage = "Could not retrieve any uploaded files.";
			log.error("ANTENNA | UploadController | listFileImages | File image list is null");
		}
		else {
			fileImagesListMessage = " " + fileImages.size() + " uploaded files retrieved.";
			log.info("ANTENNA | UploadController| listFileImages | Number of file images retrieved: " + fileImages.size());
		}

		model.addAttribute("fileImagesList", fileImages);
		model.addAttribute("fileImagesListMessage", fileImagesListMessage);
		return "fileuploads/list-fileuploads";
	}

	@GetMapping("/newcsv")
	public String newCsvFile(Model model) {
		log.info("ANTENNA | UploadController | newCsvFile | called");
		return "fileuploads/new-fileuploads";
	}

	@PostMapping("/csvupload")
	public String uploadCsvFile(MultipartFile file, Model model) {
		log.info("ANTENNA | UploadController | uploadCsvFile | called");
		String csvFileUploadMessage = "";

		if (file == null || file.isEmpty())  {
			csvFileUploadMessage = "Please choose a CSV file to upload.";
			model.addAttribute("csvFileUploadMessage", csvFileUploadMessage);
			log.info("ANTENNA | UploadController| uploadCsvFile | Null or empty CSV file received");
			return "fileuploads/new-fileuploads";
		}

		boolean isCsvSuccessfullyProcessed = uploadServiceManager.processCsvRecords(file);

		if (isCsvSuccessfullyProcessed) {
			csvFileUploadMessage = "CSV file successfully uploaded.";
			log.error("ANTENNA | UploadController | uploadCsvFile | CSV file successfully uploaded");
		}
		else {
			csvFileUploadMessage = "CSV file upload did not succeed. Check for possible file structure errors.";
			log.info("ANTENNA | UploadController| uploadCsvFile | CSV file upload did not succeed");
		}

		model.addAttribute("csvFileUploadMessage", csvFileUploadMessage);
		return "fileuploads/new-fileuploads";
	}

	@GetMapping(value="measurements")
	public String listFileImageMeasurements(@RequestParam(value = "antennaCode") String antennaCode,
											@RequestParam(value = "publishedMessage") String publishedMessage,
											Model model) {
		log.info("ANTENNA | UploadController | listfileimagemeasurements | called");
		List<FileImageMeasurementDto> fileImageMeasurements = uploadServiceManager.viewFileImageMeasurements(antennaCode);
		String fileImageMeasurementsListMessage = "";

		if (fileImageMeasurements == null) {
			fileImageMeasurementsListMessage = "Could not retrieve any file image measurements.";
			log.error("ANTENNA | UploadController | listFileImageMeasurements | File image measurement list is null");
		}
		else {
			fileImageMeasurementsListMessage = " " + fileImageMeasurements.size() + " file image measurements retrieved.";
			log.info("ANTENNA | UploadController| listFileImageMeasurements | Number of file image measurements retrieved: " + fileImageMeasurements.size());
		}
		
		model.addAttribute("antennaCode", antennaCode);
		model.addAttribute("fileImageMeasurementsList", fileImageMeasurements);
		
		if (!publishedMessage.equals("PENDING")) {
			model.addAttribute("fileImageMeasurementsListMessage", publishedMessage);
		}
		else {
			model.addAttribute("fileImageMeasurementsListMessage", fileImageMeasurementsListMessage);
		}

		return "fileUploads/list-filemeasurements";
	}

	@GetMapping (value="publish" )
	public String publishFileImageMeasurements(@RequestParam(value = "antennaCode") String antennaCode, Model model) {
		log.info("ANTENNA | UploadController | publishImageFileMeasurements | called");
		FileImageMeasurementPersistResponse response = uploadServiceManager.publishFileImageMeasurements(antennaCode);
		String fileImagePublishMeasurementsMessage = "";

		if (response == null) {
			fileImagePublishMeasurementsMessage = "Could not publish file image measurements.";
			log.error("ANTENNA | UploadController | publishImageFileMeasurements | Publish measurements response is null");
		}
		else {
			fileImagePublishMeasurementsMessage = response.getResponseMessage();
			log.info("ANTENNA | UploadController| publishImageFileMeasurements | Publish measurements response: " + fileImagePublishMeasurementsMessage);
		}
		
		model.addAttribute("fileImagePublishMeasurementsMessage", fileImagePublishMeasurementsMessage);
		return "redirect:/antenna-dashboard/fileuploads/measurements/?antennaCode=" + antennaCode + "&publishedMessage=" + fileImagePublishMeasurementsMessage;
	}
}
