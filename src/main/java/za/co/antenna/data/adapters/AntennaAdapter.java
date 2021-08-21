package za.co.antenna.data.adapters;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import za.co.antenna.dtos.AntennaDto;
import za.co.antenna.dtos.AntennaPersistRequest;
import za.co.antenna.dtos.AntennaRetrieveMultipleResponse;
import za.co.antenna.dtos.AntennaRetrieveSingleResponse;



@Component
public class AntennaAdapter  {
	private static final Logger log = LoggerFactory.getLogger(AntennaAdapter.class);

	public static AntennaPersistRequest makeAntennaPersistRequest(AntennaDto antennaDto) {

		log.info("ANTENNA : AntennaAdapter : makeAntennaPersistRequest : got  antennaDto: "+antennaDto);
		AntennaPersistRequest antennaPersistRequest=new AntennaPersistRequest();
		antennaPersistRequest.setAntennaCode(antennaDto.getAntennaCode());
		antennaPersistRequest.setAntennaId(antennaDto.getAntennaId());
		antennaPersistRequest.setDateCreated(antennaDto.getDateCreated());
		antennaPersistRequest.setDescription(antennaDto.getDescription());
		antennaPersistRequest.setMainAntennaCode(antennaDto.getMainAntennaCode());
		antennaPersistRequest.setName(antennaDto.getName());
		antennaPersistRequest.setAntennaTypeCode(antennaDto.getAntennaTypeCode());
		
		if(StringUtils.isEmpty(antennaPersistRequest.getDateCreated())){
			antennaPersistRequest.setDateCreated(dateTostring());
		}
		
		if(StringUtils.isNotEmpty(antennaDto.getAntennaTypeCode()) && StringUtils.isNotEmpty(antennaDto.getAntennaCode()) 
				&& StringUtils.isEmpty(antennaDto.getMainAntennaCode()) 
				&& "B".equalsIgnoreCase(antennaDto.getAntennaTypeCode())) {
			
				antennaDto.setMainAntennaCode(antennaDto.getAntennaCode());
		}
		
		if(StringUtils.isEmpty(antennaDto.getAntennaId())){
			antennaPersistRequest.setAction("INSERT");
		}else {			
			antennaPersistRequest.setAction("UPDATE");
		}

		log.info("ANTENNA : AntennaAdapter : makeAntennaPersistRequest : got  antennaDto: "+antennaDto);
		return antennaPersistRequest;
	}
	
	public static String dateTostring() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return timeStamp;
	}
	
	public static AntennaDto makeAntennaDto(AntennaRetrieveSingleResponse response) {
		AntennaDto  antennaDto=null;
		if(response!=null) {
			antennaDto=response.getAntennaDto();
		}
		return antennaDto;
	}

	public static List<AntennaDto> makeAntennaDtos(AntennaRetrieveMultipleResponse response) {
		List<AntennaDto> antennaDtos=null;
		if(response!=null) {
			antennaDtos=response.getAntennaDtos();
		}
		return antennaDtos;
	}

}
