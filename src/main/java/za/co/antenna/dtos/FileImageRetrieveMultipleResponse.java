package za.co.antenna.dtos;

import java.util.List;

public class FileImageRetrieveMultipleResponse {
	private String responseCode;
	private String responseMessage;	
	private List<FileImageDto> fileImageDtos;
	

	public FileImageRetrieveMultipleResponse(){}


	public FileImageRetrieveMultipleResponse(String responseCode, String responseMessage, List<FileImageDto> fileImageDtos) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.fileImageDtos = fileImageDtos;
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


	public List<FileImageDto> getFileImageDtos() {
		return fileImageDtos;
	}


	public void setFileImageDtos(List<FileImageDto> fileImageDtos) {
		this.fileImageDtos = fileImageDtos;
	}


	@Override
	public String toString() {
		return "FileImageRetrieveMultipleResponse [responseCode=" +
				responseCode +
				", responseMessage=" +
				responseMessage +
				", fileImageDtos=" +
				fileImageDtos +
				"]";
	}
}
