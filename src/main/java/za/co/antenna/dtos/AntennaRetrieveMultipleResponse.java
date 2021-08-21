package za.co.antenna.dtos;

import java.util.List;


public class AntennaRetrieveMultipleResponse {
	private String responseCode;
	private String responseMessage;	
	private List<AntennaDto> antennaDtos;
	

	public AntennaRetrieveMultipleResponse(){}


	public AntennaRetrieveMultipleResponse(String responseCode, String responseMessage, List<AntennaDto> antennaDtos) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.antennaDtos = antennaDtos;
	}


	public String getResponseCode() {
		return responseCode;
	}


	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}


	public String getResponseMessage() {
		return responseMessage;
	}


	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}


	public List<AntennaDto> getAntennaDtos() {
		return antennaDtos;
	}


	public void setAntennaDtos(List<AntennaDto> antennaDtos) {
		this.antennaDtos = antennaDtos;
	}


	@Override
	public String toString() {
		return "AntennaRetrieveMultipleResponse [responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", antennaDtos=" + antennaDtos + "]";
	}
	


}
