package za.co.antenna.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the file_image database table.
 * 
 */
@Entity
@Table(name = "file_image")
//@NamedQuery(name="FileImage.findAll", query="SELECT f FROM FileImage f")
public class FileImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "file_id")
	private Long fileId;

	@Column(name = "file_name", length=256)
	private String fileName;

	@Column(name = "number_of_records")
	private int numberOfRecords;

	@Column(name = "date_uploaded")
	private Timestamp dateUploaded;

	@Column(name = "antenna_code", length=128)
	private String antennaCode;

	@Column(name = "antenna_type_code", length=128)
	private String antennaTypeCode;

	@Column(name = "measurement_code", length=128)
	private String measurementCode;

	@Column(name = "status", length=64)
	private String status;

	// Bidirectional many-to-one association to FileImageMeasurement
	@OneToMany(mappedBy = "fileImage", fetch = FetchType.LAZY)
	private List<FileImageMeasurement> fileImageMeasurements;

	// Unidirectional one-to-one association to Antenna
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "antenna_code", nullable = false, updatable=false, insertable=false, referencedColumnName = "antenna_code")
    private Antenna antenna;

	// Unidirectional one-to-one association to AntennaType
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "antenna_type_code", nullable = false, updatable=false, insertable=false, referencedColumnName = "antenna_type_code")
    private AntennaType antennaType;

	// Unidirectional one-to-one association to Measurement
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_code", nullable = false, updatable=false, insertable=false, referencedColumnName = "measurement_code")
    private Measurement measurement;

	public FileImage() {
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public Timestamp getDateUploaded() {
		return dateUploaded;
	}

	public void setDateUploaded(Timestamp dateUploaded) {
		this.dateUploaded = dateUploaded;
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

	public String getMeasurementCode() {
		return measurementCode;
	}

	public void setMeasurementCode(String measurementCode) {
		this.measurementCode = measurementCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<FileImageMeasurement> getFileImageMeasurements() {
		return fileImageMeasurements;
	}

	public void setFileImageMeasurements(List<FileImageMeasurement> fileImageMeasurements) {
		this.fileImageMeasurements = fileImageMeasurements;
	}

	public Antenna getAntenna() {
		return antenna;
	}

	public void setAntenna(Antenna antenna) {
		this.antenna = antenna;
	}

	public AntennaType getAntennaType() {
		return antennaType;
	}

	public void setAntennaType(AntennaType antennaType) {
		this.antennaType = antennaType;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	@Override
	public String toString() {
		return "FileImage [fileId=" +
				fileId +
				", fileName=" +
				fileName +
				", numberOfRecords=" +
				numberOfRecords +
				", dateUploaded=" +
				dateUploaded +
				", antennaCode=" +
				antennaCode +
				", antennaTypeCode=" +
				antennaTypeCode +
				", measurementCode=" +
				measurementCode +
				", status=" +
				status +
				"]";
	}
}