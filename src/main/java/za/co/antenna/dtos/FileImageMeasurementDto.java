package za.co.antenna.dtos;

public class FileImageMeasurementDto {
	private String fileMeasurementId;
	private String antennaCode;
	private String determinant;
	private String value;

	public FileImageMeasurementDto() {}

	public FileImageMeasurementDto(String antennaCode,
			 					   String determinant,
			 					   String value) {
		super();
		this.antennaCode = antennaCode;
		this.determinant = determinant;
		this.value = value;
	}

	public String getFileMeasurementId() {
		return fileMeasurementId;
	}

	public void setFileMeasurementId(String fileMeasurementId) {
		this.fileMeasurementId = fileMeasurementId;
	}

	public String getAntennaCode() {
		return antennaCode;
	}

	public void setAntennaCode(String antennaCode) {
		this.antennaCode = antennaCode;
	}

	public String getDeterminant() {
		return determinant;
	}

	public void setDeterminant(String determinant) {
		this.determinant = determinant;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "FileImageMeasurementDto [fileMeasurementId=" +
				fileMeasurementId +
				", antennaCode=" +
				antennaCode +
				", determinant=" +
				determinant +
				", value=" +
				value +
				"]";
	}
}
