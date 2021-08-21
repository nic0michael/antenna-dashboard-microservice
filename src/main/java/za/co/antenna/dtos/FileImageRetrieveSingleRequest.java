package za.co.antenna.dtos;

public class FileImageRetrieveSingleRequest {
	private String antennaCode;
	private String orderBy;
	
	public FileImageRetrieveSingleRequest() {}

	public FileImageRetrieveSingleRequest(String antennaId) {
		super();
	}
	
	public FileImageRetrieveSingleRequest(String antennaCode,
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
		return "FileImageRetrieveSingleRequest [antennaCode=" +
				antennaCode +
				", orderBy=" +
				orderBy +
				"]";
	}
}
