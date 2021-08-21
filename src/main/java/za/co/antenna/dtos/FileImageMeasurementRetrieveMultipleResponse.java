package za.co.antenna.dtos;

import java.util.List;

public class FileImageMeasurementRetrieveMultipleResponse {
	private String responseCode;
	private String responseMessage;	
	private List<FileImageMeasurementDto> fileImageMeasurementDtos;
	

	public FileImageMeasurementRetrieveMultipleResponse(){}


	public FileImageMeasurementRetrieveMultipleResponse(String responseCode,
														String responseMessage,
														List<FileImageMeasurementDto> fileImageMeasurementDtos) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.fileImageMeasurementDtos = fileImageMeasurementDtos;
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


	public List<FileImageMeasurementDto> getFileImageMeasurementDtos() {
		return fileImageMeasurementDtos;
	}


	public void setFileImageMeasurementDtos(List<FileImageMeasurementDto> fileImageMeasurementDtos) {
		this.fileImageMeasurementDtos = fileImageMeasurementDtos;
	}


	@Override
	public String toString() {
		return "FileImageMeasurementRetrieveMultipleResponse [responseCode=" +
				responseCode +
				", responseMessage=" +
				responseMessage +
				", fileImageMeasurementDtos=" +
				fileImageMeasurementDtos +
				"]";
	}
}
