package za.co.antenna.servicemanagers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import za.co.antenna.dtos.FileImageDto;
import za.co.antenna.dtos.FileImageMeasurementDto;
import za.co.antenna.dtos.FileImageMeasurementPersistRequest;
import za.co.antenna.dtos.FileImageMeasurementPersistResponse;
import za.co.antenna.dtos.FileImageMeasurementRetrieveMultipleRequest;
import za.co.antenna.dtos.FileImageMeasurementRetrieveMultipleResponse;
import za.co.antenna.dtos.FileImagePersistRequest;
import za.co.antenna.dtos.FileImagePersistResponse;
import za.co.antenna.dtos.FileImageRetrieveMultipleRequest;
import za.co.antenna.dtos.FileImageRetrieveMultipleResponse;

@Component
public class UploadServiceManager {
	private static final Logger log = LoggerFactory.getLogger(UploadServiceManager.class);

	@Value("${persistence.service.uri}")
	String persistenceServiceUri;

	public boolean processCsvRecords(MultipartFile file) {
		try {
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
			FileImagePersistRequest fileImagePersistRequest = new FileImagePersistRequest();
			FileImageMeasurementPersistRequest fileImageMeasurementPersistRequest;
			FileImageMeasurementDto fileImageMeasurementDto;
			List<FileImageMeasurementDto> measurements = new ArrayList<FileImageMeasurementDto>();
			CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			boolean isHeaderRecordFound = false,
					isMeasurementRecordFound = false,
					isTrailerRecordFound = false;
			int numberOfCsvRecords = 0,
				numberOfCsvRecordsOnFile = 0;
			Date dateNow = new Date();
			String pattern = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			String mysqlDateNow = formatter.format(dateNow);

			for (CSVRecord csvRecord : csvRecords) {
				String column = "";
				numberOfCsvRecords++;

				for (int i = 0; i < csvRecord.size(); i++) {
					if (i == 0) {
						column = csvRecord.get(i).trim();

						switch (column) {
							case "0" : {
								if (csvRecord.size() == 5) {
									fileImagePersistRequest.setAntennaCode(csvRecord.get(i + 1).trim());
									fileImagePersistRequest.setAntennaTypeCode(csvRecord.get(i + 2).trim());
									fileImagePersistRequest.setMeasurementCode(csvRecord.get(i + 4).trim());
									fileImagePersistRequest.setFileName(file.getOriginalFilename().trim());
									fileImagePersistRequest.setDateUploaded(mysqlDateNow);
									fileImagePersistRequest.setStatus("UPLOADED");
									isHeaderRecordFound = true;
									break;
								}
								else {
									log.error("ANTENNA | UploadServiceManager| processCsvRecords | response: Invalid header record.");
									return false;
								}
							}
	
							case "1" : {
								if (csvRecord.size() == 3) {
									fileImageMeasurementDto = new FileImageMeasurementDto();
									fileImageMeasurementDto.setDeterminant(csvRecord.get(i + 1).trim());
									fileImageMeasurementDto.setValue(csvRecord.get(i + 2).trim());
									measurements.add(fileImageMeasurementDto);
									isMeasurementRecordFound = true;
									break;
								}
								else {
									log.error("ANTENNA | UploadServiceManager| processCsvRecords | response: Invalid measurement record.");
									return false;
								}
							}

							case "999" : {
								if (csvRecord.size() == 2) {
									
									try {
										numberOfCsvRecordsOnFile = (Integer.valueOf(csvRecord.get(i + 1).trim())).intValue();
									}
									catch (Exception e) {
										log.error("ANTENNA | UploadServiceManager| processCsvRecords | response: Number of records on trailer record must be numeric.");
										return false;
									}

									fileImagePersistRequest.setNumberOfRecords(csvRecord.get(i + 1).trim());
									isTrailerRecordFound = true;
									break;
								}
								else {
									log.error("ANTENNA | UploadServiceManager| processCsvRecords | response: Invalid trailer record.");
									return false;
								}
							}

							default : {
								log.error("ANTENNA | UploadServiceManager| processCsvRecords | response: Invalid record type.");
								return false;
							}
						}
					}
				}
			}

			csvParser.close();

			if (numberOfCsvRecords < 3 ||
				numberOfCsvRecords > 999 ||
				numberOfCsvRecordsOnFile != numberOfCsvRecords) {
				log.error("ANTENNA | UploadServiceManager| processCsvRecords | response: Invalid number of records.");
				return false;
			}

			if (!isHeaderRecordFound) {
				log.error("ANTENNA | UploadServiceManager| processCsvRecords | response: Missing header record.");
				return false;
			}

			if (!isMeasurementRecordFound) {
				log.error("ANTENNA | UploadServiceManager| processCsvRecords | response: Missing measurement record.");
				return false;
			}

			if (!isTrailerRecordFound) {
				log.error("ANTENNA | UploadServiceManager| processCsvRecords | response: Missing trailer record.");
				return false;
			}

			FileImagePersistResponse response;
			
			response = deleteFileImage(fileImagePersistRequest);
			log.info("ANTENNA | UploadServiceManager| processCsvRecords (Delete FI) | response: " + response);

			response = persistFileImage(fileImagePersistRequest);
			log.info("ANTENNA | UploadServiceManager| processCsvRecords (Insert FI) | response: " + response);
			
			fileImageMeasurementPersistRequest = new FileImageMeasurementPersistRequest();
			fileImageMeasurementPersistRequest.setAntennaCode(fileImagePersistRequest.getAntennaCode());
			response = deleteFileImageMeasurements(fileImageMeasurementPersistRequest);
			log.info("ANTENNA | UploadServiceManager| processCsvRecords (Delete FIM) | response: " + response);

			FileImageMeasurementPersistResponse fileImageMeasurementPersistResponse;

			for (int i = 0; i < measurements.size(); i++) {
				fileImageMeasurementPersistRequest = new FileImageMeasurementPersistRequest();
				fileImageMeasurementPersistRequest.setAntennaCode(fileImagePersistRequest.getAntennaCode());
				fileImageMeasurementPersistRequest.setDeterminant(measurements.get(i).getDeterminant());
				fileImageMeasurementPersistRequest.setValue(measurements.get(i).getValue());
				fileImageMeasurementPersistResponse = persistFileImageMeasurement(fileImageMeasurementPersistRequest);
				log.info("ANTENNA | UploadServiceManager| processCsvRecords (Insert FIM) | response: " + fileImageMeasurementPersistResponse);
			}

			return true;
		}
		catch (IOException ioe) {
			log.error("ANTENNA | UploadServiceManager| processCsvRecords | response: " + ioe);
			return false;
		}
	}

	private FileImagePersistResponse deleteFileImage(FileImagePersistRequest request) {
		String url = persistenceServiceUri + "/fileimage/delete";
		FileImagePersistResponse fileImagePersistResponse = new FileImagePersistResponse();

		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
	
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<FileImagePersistRequest> entity = new HttpEntity<FileImagePersistRequest>(request, headers);
	
			fileImagePersistResponse = restTemplate.exchange(url, HttpMethod.POST, entity, FileImagePersistResponse.class).getBody();
	
			log.info("ANTENNA | UploadServiceManager| deleteFileImage | response: " + fileImagePersistResponse);
	    }
	    catch (RestClientException e) {
	    	log.error("ANTENNA | UploadServiceManager | deleteFileImage | Failed to delete file image",e);
	    }

		return fileImagePersistResponse;
	}

	private FileImagePersistResponse persistFileImage(FileImagePersistRequest request) {
		String url = persistenceServiceUri + "/fileimage/insert";
		FileImagePersistResponse fileImagePersistResponse = new FileImagePersistResponse();

		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
	
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<FileImagePersistRequest> entity = new HttpEntity<FileImagePersistRequest>(request, headers);
	
			fileImagePersistResponse = restTemplate.exchange(url, HttpMethod.POST, entity, FileImagePersistResponse.class).getBody();
	
			log.info("ANTENNA | UploadServiceManager| persistFileImage | response: " + fileImagePersistResponse);
	    }
	    catch (RestClientException e) {
	    	log.error("ANTENNA | UploadServiceManager | persistFileImage | Failed to persist file image", e);
	    }
		
		return fileImagePersistResponse;
	}

	private FileImagePersistResponse deleteFileImageMeasurements(FileImageMeasurementPersistRequest request) {
		String url = persistenceServiceUri + "/fileimagemeasurement/delete";
		FileImagePersistResponse fileImagePersistResponse = new FileImagePersistResponse();

		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
	
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<FileImageMeasurementPersistRequest> entity = new HttpEntity<FileImageMeasurementPersistRequest>(request, headers);
	
			fileImagePersistResponse = restTemplate.exchange(url, HttpMethod.POST, entity, FileImagePersistResponse.class).getBody();
	
			log.info("ANTENNA | UploadServiceManager| deleteFileImageMeasurements | response: " + fileImagePersistResponse);
	    }
	    catch (RestClientException e) {
	    	log.error("ANTENNA | UploadServiceManager | deleteFileImageMeasurements | Failed to delete file image measurement", e);
	    }

		return fileImagePersistResponse;
	}

	private FileImageMeasurementPersistResponse persistFileImageMeasurement(FileImageMeasurementPersistRequest request) {
		String url = persistenceServiceUri + "/fileimagemeasurement/insert";
		FileImageMeasurementPersistResponse fileImageMeasurementPersistResponse = new FileImageMeasurementPersistResponse();

		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
	
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<FileImageMeasurementPersistRequest> entity = new HttpEntity<FileImageMeasurementPersistRequest>(request, headers);
	
			fileImageMeasurementPersistResponse = restTemplate.exchange(url, HttpMethod.POST, entity, FileImageMeasurementPersistResponse.class).getBody();
	
			log.info("ANTENNA | UploadServiceManager| persistFileImageMeasurement | response: " + fileImageMeasurementPersistResponse);
	    }
	    catch (RestClientException e) {
	    	log.error("ANTENNA | UploadServiceManager | persistFileImageMeasurement | Failed to persist file image measurement", e);
	    }
		
		return fileImageMeasurementPersistResponse;
	}

	public List<FileImageDto> findAllFileImages() {
		String url = persistenceServiceUri + "/fileimage/findall";
		List<FileImageDto> fileImages = new ArrayList<>();
		FileImageRetrieveMultipleResponse response = null;
	    FileImageRetrieveMultipleRequest request = new FileImageRetrieveMultipleRequest();
	    log.info("ANTENNA | UploadServiceManager | findAllFileImages | url: " + url);
	    request.setOrderBy("antenna_code");
	    log.info("ANTENNA | UploadServiceManager | findAllFileImages | FileImageRetrieveMultipleRequest : " + request); 
	    
	    try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<FileImageRetrieveMultipleRequest> entity = new HttpEntity<FileImageRetrieveMultipleRequest>(request, headers);

			response = restTemplate.exchange(url, HttpMethod.POST, entity, FileImageRetrieveMultipleResponse.class).getBody();

			if (response != null) {
				fileImages = response.getFileImageDtos();

				if (fileImages != null) {
					log.info("ANTENNA | UploadServiceManager| findAllFileImages | Number of file images: " + fileImages.size());
				}
				else {
					log.info("ANTENNA | UploadServiceManager| findAllFileImages | File images collection is null");					
				}
			}
			else {
				log.info("ANTENNA | UploadServiceManager| findAllFileImages | Number of file images: No file images retrieved");			
			}
			
	    }
	    catch (RestClientException e) {
	    	log.error("ANTENNA | UploadServiceManager| findAllFileImages | Failed to retrieve FileImageRetrieveMultipleResponse", e);
	    }
	    
	    log.info("ANTENNA | UploadServiceManager| findAllFileImages | FileImageRetrieveMultipleResponse : " + response);
	    
		return fileImages;
	}

	public List<FileImageMeasurementDto> viewFileImageMeasurements(String antennaCode) {
		String url = persistenceServiceUri + "/fileimagemeasurement/findallbyantennacode";
		List<FileImageMeasurementDto> fileImageMeasurements = new ArrayList<>();
		FileImageMeasurementRetrieveMultipleResponse response = null;
	    FileImageMeasurementRetrieveMultipleRequest request = new FileImageMeasurementRetrieveMultipleRequest();
	    log.info("ANTENNA | UploadServiceManager | viewFileImageMeasurements | url: " + url);
	    request.setAntennaCode(antennaCode);
	    request.setOrderBy("antenna_code");
	    log.info("ANTENNA | UploadServiceManager | viewFileImageMeasurements | FileImageMeasurementRetrieveMultipleRequest : " + request); 

	    try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<FileImageMeasurementRetrieveMultipleRequest> entity = new HttpEntity<FileImageMeasurementRetrieveMultipleRequest>(request, headers);

			response = restTemplate.exchange(url, HttpMethod.POST, entity, FileImageMeasurementRetrieveMultipleResponse.class).getBody();

			if (response != null) {
				fileImageMeasurements = response.getFileImageMeasurementDtos();

				if (fileImageMeasurements != null) {
					log.info("ANTENNA | UploadServiceManager| viewFileImageMeasurements | Number of file image measurements: " + fileImageMeasurements.size());
				}
				else {
					log.info("ANTENNA | UploadServiceManager| viewFileImageMeasurements | File image measurements collection is null");					
				}
			}
			else {
				log.info("ANTENNA | UploadServiceManager| viewFileImageMeasurements | Number of file image measurements: No file image measurements retrieved");			
			}
			
	    }
	    catch (RestClientException e) {
	    	log.error("ANTENNA | UploadServiceManager| viewFileImageMeasurements | Failed to retrieve FileImageRetrieveMultipleResponse", e);
	    }
	    
	    log.info("ANTENNA | UploadServiceManager| viewFileImageMeasurements | FileImageRetrieveMultipleResponse : " + response);
	    
		return fileImageMeasurements;
	}

	public FileImageMeasurementPersistResponse publishFileImageMeasurements(String antennaCode) {
		String url = persistenceServiceUri + "/fileimagemeasurement/publish";
		FileImageMeasurementPersistResponse fileImageMeasurementPersistResponse = null;
		FileImageMeasurementPersistRequest request = new FileImageMeasurementPersistRequest();
	    log.info("ANTENNA | UploadServiceManager | publishFileImageMeasurements | url: " + url);
	    request.setAntennaCode(antennaCode);
	    log.info("ANTENNA | UploadServiceManager | publishFileImageMeasurements | FileImageMeasurementPersistRequest : " + request); 
	    
	    try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<FileImageMeasurementPersistRequest> entity = new HttpEntity<FileImageMeasurementPersistRequest>(request, headers);

			fileImageMeasurementPersistResponse = restTemplate.exchange(url, HttpMethod.POST, entity, FileImageMeasurementPersistResponse.class).getBody();

			log.info("ANTENNA | UploadServiceManager| publishFileImageMeasurements | response: " + fileImageMeasurementPersistResponse);
	    }
	    catch (RestClientException e) {
	    	log.error("ANTENNA | UploadServiceManager | publishFileImageMeasurements | Failed to publish file image measurements", e);
	    }
	    
	    log.info("ANTENNA | UploadServiceManager| publishFileImageMeasurements | FileImageMeasurementPersistResponse : " + fileImageMeasurementPersistResponse);
	    
		return fileImageMeasurementPersistResponse;
	}
}
