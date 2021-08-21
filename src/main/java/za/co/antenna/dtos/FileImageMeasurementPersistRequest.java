package za.co.antenna.dtos;

import javax.xml.bind.annotation.XmlRootElement;


public class FileImageMeasurementPersistRequest {
	private String fileMeasurementId;
	private String antennaCode;
	private String determinant;
	private String value;
	
	public FileImageMeasurementPersistRequest() {}

	public FileImageMeasurementPersistRequest(String antennaCode,
			 								  String determinant,
			 								  String value) {
		super();
		this.antennaCode = antennaCode;
		this.determinant = determinant;
		this.value = value;
	}

	public String getFileImageId() {
		return fileMeasurementId;
	}

	public void setFileImageId(String fileMeasurementId) {
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
		return "FileImageMeasurementPersistRequest [fileMeasurementId=" +
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
