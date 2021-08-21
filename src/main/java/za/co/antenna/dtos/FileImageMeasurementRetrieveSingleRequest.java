package za.co.antenna.dtos;

public class FileImageMeasurementRetrieveSingleRequest {
	private String antennaCode;
	private String orderBy;
	
	public FileImageMeasurementRetrieveSingleRequest() {}

	public FileImageMeasurementRetrieveSingleRequest(String antennaId) {
		super();
	}
	
	public FileImageMeasurementRetrieveSingleRequest(String antennaCode,
													 String orderBy) {
		super();
		this.antennaCode = antennaCode;
		this.orderBy = orderBy;
	}

	public String getAntennaCode() {
		return antennaCode;
	}

	public void setAntennaCode(String antennaCode) {
		this.antennaCode = antennaCode;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "FileImageMeasurementRetrieveSingleRequest [antennaCode=" +
				antennaCode +
				", orderBy=" +
				orderBy +
				"]";
	}
}
