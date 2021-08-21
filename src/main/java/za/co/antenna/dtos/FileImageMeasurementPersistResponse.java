package za.co.antenna.dtos;

public class FileImageMeasurementPersistResponse {
	private String responseCode;
	private String responseMessage;	
	private String antennaCode;
	
	public FileImageMeasurementPersistResponse(){}

	public FileImageMeasurementPersistResponse(String responseCode,
								  String responseMessage,
								  String antennaCode) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.antennaCode = antennaCode;
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

	public String getAntennaCode() {
		return antennaCode;
	}

	public void setAntennaCode(String antennaCode) {
		this.antennaCode = antennaCode;
	}

	@Override
	public String toString() {
		return "FileImageMeasurementPersistResponse [responseCode=" +
				responseCode +
				", responseMessage=" +
				responseMessage +
				", antennaCode="	+
				antennaCode +
				"]";
	}
}
