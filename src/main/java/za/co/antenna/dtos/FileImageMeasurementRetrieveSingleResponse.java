package za.co.antenna.dtos;

public class FileImageMeasurementRetrieveSingleResponse {
	private String responseCode;
	private String responseMessage;	
	private FileImageDto fileImageMeasurementDto;
	
	public FileImageMeasurementRetrieveSingleResponse() {}

	public FileImageMeasurementRetrieveSingleResponse(String responseCode, String responseMessage, FileImageDto fileImageMeasurementDto) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.fileImageMeasurementDto = fileImageMeasurementDto;
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

	public FileImageDto getFileImageDto() {
		return fileImageMeasurementDto;
	}

	public void setFileImageDto(FileImageDto fileImageMeasurementDto) {
		this.fileImageMeasurementDto = fileImageMeasurementDto;
	}

	@Override
	public String toString() {
		return "FileImageMeasurementRetrieveSingleResponse [responseCode=" +
				responseCode +
				", responseMessage=" +
				responseMessage +
				", fileImageMeasurementDto=" +
				fileImageMeasurementDto +
				"]";
	}
}
