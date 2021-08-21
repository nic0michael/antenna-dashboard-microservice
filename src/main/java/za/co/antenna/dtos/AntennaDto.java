package za.co.antenna.dtos;

import java.sql.Timestamp;

public class AntennaDto {

	private String antennaId;

	private String antennaCode;

	private String dateCreated;

	private String description;

	private String name;

	private String antennaTypeCode;

	private String mainAntennaCode;

	public AntennaDto() {}

	public AntennaDto(String antennaId, String antennaCode, String dateCreated, String description, String name,
			String antennaTypeCode, String mainAntennaCode) {
		super();
		this.antennaId = antennaId;
		this.antennaCode = antennaCode;
		this.dateCreated = dateCreated;
		this.description = description;
		this.name = name;
		this.antennaTypeCode = antennaTypeCode;
		this.mainAntennaCode = mainAntennaCode;
	}

	public String getAntennaId() {
		return antennaId;
	}

	public void setAntennaId(String antennaId) {
		this.antennaId = antennaId;
	}

	public String getAntennaCode() {
		return antennaCode;
	}

	public void setAntennaCode(String antennaCode) {
		this.antennaCode = antennaCode;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAntennaTypeCode() {
		return antennaTypeCode;
	}

	public void setAntennaTypeCode(String antennaTypeCode) {
		this.antennaTypeCode = antennaTypeCode;
	}

	public String getMainAntennaCode() {
		return mainAntennaCode;
	}

	public void setMainAntennaCode(String mainAntennaCode) {
		this.mainAntennaCode = mainAntennaCode;
	}

	@Override
	public String toString() {
		return "AntennaDto [antennaId=" + antennaId + ", antennaCode=" + antennaCode + ", dateCreated=" + dateCreated
				+ ", description=" + description + ", name=" + name + ", antennaTypeCode=" + antennaTypeCode
				+ ", mainAntennaCode=" + mainAntennaCode + "]";
	}

	
}
