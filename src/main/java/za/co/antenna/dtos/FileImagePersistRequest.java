package za.co.antenna.dtos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FileImagePersistRequest")
public class FileImagePersistRequest {
	private String fileId;
	private String antennaCode;
	private String antennaTypeCode;
	private String dateUploaded;
	private String fileName;
	private String measurementCode;
	private String numberOfRecords;
	private String status;
	
	public FileImagePersistRequest() {}

	public FileImagePersistRequest(String antennaCode,
			 					   String antennaTypeCode,
								   String dateUploaded,
								   String fileName,
								   String measurementCode,
								   String numberOfRecords,
								   String status) {
		super();
		this.antennaCode = antennaCode;
		this.antennaTypeCode = antennaTypeCode;
		this.dateUploaded = dateUploaded;
		this.fileName = fileName;
		this.measurementCode = measurementCode;
		this.numberOfRecords = numberOfRecords;
		this.status = status;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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

	public String getDateUploaded() {
		return dateUploaded;
	}

	public void setDateUploaded(String dateUploaded) {
		this.dateUploaded = dateUploaded;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMeasurementCode() {
		return measurementCode;
	}

	public void setMeasurementCode(String measurementCode) {
		this.measurementCode = measurementCode;
	}

	public String getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(String numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FileImagePersistRequest [fileId=" +
				fileId +
				", antennaCode=" +
				antennaCode +
				", antennaTypeCode=" +
				antennaTypeCode +
				", dateUploaded="
				+ dateUploaded +
				", fileName=" +
				fileName +
				", measurementCode=" +
				measurementCode +
				", numberOfRecords=" +
				numberOfRecords +
				", action=" +
				status +
				"]";
	}
}
