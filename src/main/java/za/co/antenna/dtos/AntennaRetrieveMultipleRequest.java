package za.co.antenna.dtos;


public class AntennaRetrieveMultipleRequest {
	private String antennaTypeCode;
	private String orderBy;
	
	public AntennaRetrieveMultipleRequest(){}

	public AntennaRetrieveMultipleRequest(String antennaTypeCode, String orderBy) {
		super();
		this.antennaTypeCode = antennaTypeCode;
		this.orderBy = orderBy;
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
		return "AntennaRetrieveMultipleRequest [antennaTypeCode=" + antennaTypeCode + ", orderBy=" + orderBy + "]";
	}

}
