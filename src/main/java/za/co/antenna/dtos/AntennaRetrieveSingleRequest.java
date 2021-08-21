package za.co.antenna.dtos;

public class AntennaRetrieveSingleRequest {
	private String antennaCode;
	private String orderBy;
	
	public AntennaRetrieveSingleRequest() {}

	public AntennaRetrieveSingleRequest(String antennaId) {
		super();
	}
	
	public AntennaRetrieveSingleRequest(String antennaCode,
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
		return "AntennaRetrieveSingleRequest [antennaCode=" +
				antennaCode +
				", orderBy=" +
				orderBy +
				"]";
	}
}
