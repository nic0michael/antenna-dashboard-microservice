package za.co.antenna.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the file_image_measurement database table.
 * 
 */
@Entity
@Table(name = "file_image_measurement")
//@NamedQuery(name="FileImageMeasurement.findAll", query="SELECT fim FROM FileImageMeasurement fim")
public class FileImageMeasurement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "file_image_measurement_id")
	private Long fileImageMeasurementId;

	@Column(name = "determinant")
	private double determinant;

	@Column(name = "value")
	private double value;

	@Column(name = "antenna_code")
	private double antennaCode;

	// Bidirectional many-to-one association to FileImage
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "antenna_code", nullable = false, updatable = false, insertable = false, referencedColumnName = "antenna_code")
	private FileImage fileImage;

	public FileImageMeasurement() {
	}

	public Long getFileImageMeasurementId() {
		return fileImageMeasurementId;
	}

	public void setFileImageMeasurementId(Long fileImageMeasurementId) {
		this.fileImageMeasurementId = fileImageMeasurementId;
	}

	public double getDeterminant() {
		return determinant;
	}

	public void setDeterminant(double determinant) {
		this.determinant = determinant;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getAntennaCode() {
		return antennaCode;
	}

	public void setAntennaCode(double antennaCode) {
		this.antennaCode = antennaCode;
	}

	@Override
	public String toString() {
		return "Data [fileImageMeasurementId=" +
				fileImageMeasurementId +
				", determinant=" +
				determinant +
				", value=" +
				value +
				", antennaCode=" +
				antennaCode +
				"]";
	}
}