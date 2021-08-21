package za.co.antenna.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the data database table.
 * 
 */
@Entity
@Table(name = "data")
//@NamedQuery(name="Data.findAll", query="SELECT d FROM Data d")
public class Data implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "data_id")
	private Long dataId;

	@Column(name = "date_created")
	private Timestamp dateCreated;

	@Column(name = "determinant")
	private double determinant;

	@Column(name = "value")
	private double value;

	// Bidirectional many-to-one association to Antenna
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "antenna_code", nullable = false, updatable = false, insertable = false, referencedColumnName = "antenna_code")
	private Antenna antenna;

	// Bidirectional many-to-one association to Measurement
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "measurement_code", nullable = false, updatable = false, insertable = false, referencedColumnName = "measurement_code")
	private Measurement measurement;

	public Data() {
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
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

	public Antenna getAntenna() {
		return antenna;
	}

	public void setAntenna(Antenna antenna) {
		this.antenna = antenna;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Data [dataId=" +
				dataId +
				", dateCreated=" +
				dateCreated +
				", determinant=" +
				determinant +
				", value=" +
				value +
				", antenna=" +
				antenna +
				", measurement=" +
				measurement +
				"]";
	}
}