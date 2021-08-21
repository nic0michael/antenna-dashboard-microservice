package za.co.antenna.dtos;

public class AntennaRetrieveSingleResponse {
	private String responseCode;
	private String responseMessage;	
	private AntennaDto antennaDto;
	
	public AntennaRetrieveSingleResponse() {}

	public AntennaRetrieveSingleResponse(String responseCode, String responseMessage, AntennaDto antennaDto) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.antennaDto = antennaDto;
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

	public AntennaDto getAntennaDto() {
		return antennaDto;
	}

	public void setAntennaDto(AntennaDto antennaDto) {
		this.antennaDto = antennaDto;
	}

	@Override
	public String toString() {
		return "AntennaSingleRetrieveResponse [responseCode=" +
				responseCode +
				", responseMessage=" +
				responseMessage +
				", antennaDto=" +
				antennaDto +
				"]";
	}
}
