package za.co.antenna.dtos;

public class FileImageRetrieveMultipleRequest {
	private String antennaCode;
	private String antennaTypeCode;
	private String status;
	private String orderBy;
	
	public FileImageRetrieveMultipleRequest(){}

	public FileImageRetrieveMultipleRequest(String antennaCode,
											String antennaTypeCode,
											String status,
											String orderBy) {
		super();
		this.antennaCode = antennaCode;
		this.antennaTypeCode = antennaTypeCode;
		this.status = status;
		this.orderBy = orderBy;
	}

	public String getAntennaCode() {
		return antennaCode;
	}

	public void setAntennaCode(String antennaCode) {
		this.antennaCode = antennaCode;
	}

	public String getAntennaTypeCode() {
		return antennaTypeCode;
	}

	public void setAntennaTypeCode(String antennaTypeCode) {
		this.antennaTypeCode = antennaTypeCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "FileImageRetrieveMultipleRequest [antennaTypeCode=" +
				antennaTypeCode +
				"orderBy=" +
				orderBy +
				"]";
	}
}
