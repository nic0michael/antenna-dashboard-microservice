package za.co.antenna.dtos;


public class MeasurementPersistRequest {
	private String measurementId;
	private String measurementCode;
	private String name;
	private String description;
	private String dateCreated;
	private String antennaCode;
	
	public MeasurementPersistRequest() {}

	public MeasurementPersistRequest(String measurementCode, String name, String description,
			String dateCreated, String antennaCode) {
		super();
		this.measurementCode = measurementCode;
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.antennaCode = antennaCode;
	}

	public String getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(String measurementId) {
		this.measurementId = measurementId;
	}

	public String getMeasurementCode() {
		return measurementCode;
	}

	public void setMeasurementCode(String measurementCode) {
		this.measurementCode = measurementCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAntennaCode() {
		return antennaCode;
	}

	public void setAntennaCode(String antennaCode) {
		this.antennaCode = antennaCode;
	}

	@Override
	public String toString() {
		return "MeasurementPersistRequest [measurementId=" + measurementId + ", measurementCode=" + measurementCode
				+ ", name=" + name + ", description=" + description + ", dateCreated=" + dateCreated + ", antennaCode="
				+ antennaCode + "]";
	}

	

}
