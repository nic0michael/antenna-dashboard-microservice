package za.co.antenna.dtos;

public class FileImageMeasurementRetrieveMultipleRequest {
	private String antennaCode;
	private String antennaTypeCode;
	private String orderBy;
	
	public FileImageMeasurementRetrieveMultipleRequest(){}

	public FileImageMeasurementRetrieveMultipleRequest(String antennaCode,
													   String antennaTypeCode,
													   String orderBy) {
		super();
		this.antennaCode = antennaCode;
		this.antennaTypeCode = antennaTypeCode;
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

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "FileImageMeasurementRetrieveMultipleRequest [antennaTypeCode=" +
				antennaTypeCode +
				"orderBy=" +
				orderBy +
				"]";
	}
}
