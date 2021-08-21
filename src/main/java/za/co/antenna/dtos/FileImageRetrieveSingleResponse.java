package za.co.antenna.dtos;

public class FileImageRetrieveSingleResponse {
	private String responseCode;
	private String responseMessage;	
	private FileImageDto fileImageDto;
	
	public FileImageRetrieveSingleResponse() {}

	public FileImageRetrieveSingleResponse(String responseCode, String responseMessage, FileImageDto fileImageDto) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.fileImageDto = fileImageDto;
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
		return fileImageDto;
	}

	public void setFileImageDto(FileImageDto fileImageDto) {
		this.fileImageDto = fileImageDto;
	}

	@Override
	public String toString() {
		return "FileImageRetrieveSingleResponse [responseCode=" +
				responseCode +
				", responseMessage=" +
				responseMessage +
				", fileImageDto=" +
				fileImageDto +
				"]";
	}
}
