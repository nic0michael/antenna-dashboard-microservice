package za.co.antenna.servicemanagers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.springframework.stereotype.Component;

import za.co.antenna.data.adapters.AntennaAdapter;
import za.co.antenna.dtos.AntennaDto;
import za.co.antenna.dtos.AntennaPersistRequest;
import za.co.antenna.dtos.AntennaPersistResponse;
import za.co.antenna.dtos.AntennaRetrieveMultipleRequest;
import za.co.antenna.dtos.AntennaRetrieveMultipleResponse;
import za.co.antenna.dtos.AntennaRetrieveSingleRequest;
import za.co.antenna.dtos.AntennaRetrieveSingleResponse;
import za.co.antenna.utils.Utils;

@Component
public class AntennaServiceManager {
	private static final Logger log = LoggerFactory.getLogger(AntennaServiceManager.class);
	
	@Value("${persistence.service.uri}")
	String persistenceServiceUri;

	/**
	 * John your Persistence project is user hostile
	 * 
	 * @return
	 */
	public List<AntennaDto> findAll() {
		List<AntennaDto> antennas =new ArrayList<>();
		
		List<AntennaDto> subAntennas=findAllSubAntennas();
		List<AntennaDto> baseAntennas =findAllBaseAntennas();
		if(subAntennas!=null && ! subAntennas.isEmpty()) {
			antennas.addAll(subAntennas);
		}
		if(baseAntennas!=null && ! baseAntennas.isEmpty()) {
			antennas.addAll(baseAntennas);	
		}

		return antennas;
	}
	
	public List<AntennaDto> findAllBaseAntennas() {
		String url=persistenceServiceUri+"/baseantenna/findall";
		List<AntennaDto> baseAntennas =new ArrayList<>();
		AntennaRetrieveMultipleResponse response = null;
	    AntennaRetrieveMultipleRequest request=new AntennaRetrieveMultipleRequest();
	    
	    log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | url: "+url);
	    
	    request.setAntennaTypeCode("B");
	    request.setOrderBy("antenna_code");
	    log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | AntennaRetrieveMultipleRequest : "+request); 
	    
	    try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(url, request, AntennaRetrieveMultipleResponse.class);

			baseAntennas = AntennaAdapter.makeAntennaDtos(response);
			if(baseAntennas!=null) {
				log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | number of antennas: "+baseAntennas.size());
			} else {
				log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | number of antennas: No antennas retrieved");			
			}
			
	    } catch (RestClientException e) {
	    	log.error("ANTENNA | PersistenceServiceManager| findAllSubAntennas | Failed to retrieve AntennaRetrieveMultipleResponse",e);
	    }
	    
	    log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | AntennaRetrieveMultipleResponse : "+ response);
	    
		return baseAntennas;
	}

	public List<AntennaDto> findAllSubAntennas() {
		String url=persistenceServiceUri+"/subantenna/findall";
		List<AntennaDto> subAntennas =new ArrayList<>();
		AntennaRetrieveMultipleResponse response = null;
	    AntennaRetrieveMultipleRequest request=new AntennaRetrieveMultipleRequest();
	    
	    log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | url: "+url);
	    
	    request.setAntennaTypeCode("S");
	    request.setOrderBy("antenna_code");
	    log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | AntennaRetrieveMultipleRequest : "+request); 
	    
	    try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(url, request, AntennaRetrieveMultipleResponse.class);

			subAntennas = AntennaAdapter.makeAntennaDtos(response);
			if(subAntennas!=null) {
				log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | number of antennas: "+subAntennas.size());
			} else {
				log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | number of antennas: No antennas retrieved");			
			}
			
	    } catch (RestClientException e) {
	    	log.error("ANTENNA | PersistenceServiceManager| findAllSubAntennas | Failed to retrieve AntennaRetrieveMultipleResponse",e);
	    }
	    
	    log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | AntennaRetrieveMultipleResponse : "+ response);
	    
		return subAntennas;
	}
	
	public AntennaDto findByAntennaCode(String antennaCode) {
		String url=persistenceServiceUri+"/subantenna/findbyantennacode";		

		AntennaDto antennaDto=null;;
		AntennaRetrieveSingleResponse response = null;
		AntennaRetrieveSingleRequest request=new AntennaRetrieveSingleRequest();
		request.setAntennaCode(antennaCode);
		request.setOrderBy("'antenna_code'");
	    
	    log.info("ANTENNA | PersistenceServiceManager| findByAntennaCode | url: "+url);
	 
	    log.info("ANTENNA | PersistenceServiceManager| findByAntennaCode | AntennaRetrieveSingleRequest : "+request); 
	    
	    try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(url, request, AntennaRetrieveSingleResponse.class);
			if(response!=null) {
				antennaDto=response.getAntennaDto();
			}
			log.info("ANTENNA | PersistenceServiceManager| findByAntennaCode | AntennaDto : "+antennaDto);

			
	    } catch (RestClientException e) {
	    	log.error("ANTENNA | PersistenceServiceManager| findAllSubAntennas | Failed to retrieve AntennaRetrieveMultipleResponse",e);
	    }
	    
	    log.info("ANTENNA | PersistenceServiceManager| findAllSubAntennas | AntennaRetrieveMultipleResponse : "+ response);
	    
		return antennaDto;	
		
	}

	public void deleteAntenna(String antennaCode) {
		log.info("ANTENNA | PersistenceServiceManager| deleteAntenna | antennaCode : "+antennaCode);
		String url=persistenceServiceUri+"/subantenna/delete";	

		AntennaPersistResponse response=null;
		AntennaPersistRequest request=new AntennaPersistRequest();
		
		request.setAntennaCode(antennaCode);
		request.setAction("DELETE");
	    
	    log.info("ANTENNA | PersistenceServiceManager| deleteAntenna | url: "+url);
	 
	    log.info("ANTENNA | PersistenceServiceManager| deleteAntenna | AntennaRetrieveSingleRequest : "+request); 
	    
	    try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(url, request, AntennaPersistResponse.class);
			if(response!=null) {
				log.info("ANTENNA | PersistenceServiceManager| deleteAntenna | AntennaPersistResponse : "+response);
			} else {
				log.error("ANTENNA | PersistenceServiceManager| deleteAntenna | AntennaPersistResponse : "+response);
			}

			
	    } catch (RestClientException e) {
	    	log.error("ANTENNA | PersistenceServiceManager| deleteAntenna | Failed to retrieve AntennaRetrieveMultipleResponse",e);
	    }

		log.info("ANTENNA | PersistenceServiceManager| deleteAntenna| response: "+response);
	}

	public void insertSubAntenna(AntennaPersistRequest request) {
		String url=persistenceServiceUri+"/baseantenna/insertbaseantenna";
		
		String dateCreated=request.getDateCreated();
		if(StringUtils.isEmpty(dateCreated)) {
			String sqlDate=Utils.makeMysqlDateNow();
			request.setDateCreated(sqlDate);
		}

		AntennaPersistResponse response=null;
	    
	    log.info("ANTENNA | PersistenceServiceManager| insertSubAntenna | url: "+url);
	 
	    log.info("ANTENNA | PersistenceServiceManager| insertSubAntenna | AntennaRetrieveSingleRequest : "+request); 
	    
	    try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(url, request, AntennaPersistResponse.class);
			if(response!=null) {
				log.info("ANTENNA | PersistenceServiceManager| insertSubAntenna | AntennaPersistResponse : "+response);
			} else {
				log.error("ANTENNA | PersistenceServiceManager| insertSubAntenna | AntennaPersistResponse : "+response);
			}

			
	    } catch (RestClientException e) {
	    	log.error("ANTENNA | PersistenceServiceManager| insertSubAntenna | Failed to insert Sub Antenna",e);
	    }

		log.info("ANTENNA | PersistenceServiceManager| insertSubAntenna| response: "+response);
	}

	public void insertBaseAntenna(AntennaPersistRequest request) {
		String url=persistenceServiceUri+"/baseantenna/insertbaseantenna";	
		
		String dateCreated=request.getDateCreated();
		if(StringUtils.isEmpty(dateCreated)) {
			String sqlDate=Utils.makeMysqlDateNow();
			request.setDateCreated(sqlDate);
		}

		AntennaPersistResponse response=null;
	    
	    log.info("ANTENNA | PersistenceServiceManager| insertBaseAntenna | url: "+url);
	 
	    log.info("ANTENNA | PersistenceServiceManager| insertBaseAntenna | AntennaRetrieveSingleRequest : "+request); 
	    
	    try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(url, request, AntennaPersistResponse.class);
			if(response!=null) {
				log.info("ANTENNA | PersistenceServiceManager| insertBaseAntenna | AntennaPersistResponse : "+response);
			} else {
				log.error("ANTENNA | PersistenceServiceManager| insertBaseAntenna | AntennaPersistResponse : "+response);
			}

			
	    } catch (RestClientException e) {
	    	log.error("ANTENNA | PersistenceServiceManager| insertBaseAntenna | Failed to insert base antenna",e);
	    }

		log.info("ANTENNA | PersistenceServiceManager| insertBaseAntenna| response: "+response);						
	}

	public void updateAntenna(AntennaPersistRequest request) {
		String url=persistenceServiceUri+"/subantenna/updatesubantenna";

		AntennaPersistResponse response=null;
	    
	    log.info("ANTENNA | PersistenceServiceManager| updateAntenna | url: "+url);
	 
	    log.info("ANTENNA | PersistenceServiceManager| updateAntenna | AntennaPersistRequest : "+request); 
	    
	    try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(url, request, AntennaPersistResponse.class);
			if(response!=null) {
				log.info("ANTENNA | PersistenceServiceManager| updateAntenna | AntennaPersistResponse : "+response);
			} else {
				log.error("ANTENNA | PersistenceServiceManager| updateAntenna | AntennaPersistResponse : "+response);
			}

			
	    } catch (RestClientException e) {
	    	log.error("ANTENNA | PersistenceServiceManager| updateAntenna | Failed to update  antenna : "+e.getCause());
	    }
	    
		log.info("ANTENNA | PersistenceServiceManager| updateAntenna| response: "+response);					
	}
}
