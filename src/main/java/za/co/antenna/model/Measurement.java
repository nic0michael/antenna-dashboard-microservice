package za.co.antenna.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the measurement database table.
 * 
 */
@Entity
@Table(name = "measurement")
//@NamedQuery(name="Measurement.findAll", query="SELECT m FROM Measurement m")
public class Measurement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "measurement_id")
	private Long measurementId;

	@Column(name = "measurement_code", unique = true, nullable = false, updatable = false, insertable = false, length=128)
	private String measurementCode;

	@Column(name = "date_created")
	private Timestamp dateCreated;

	@Column(name = "description", length=2048)
	private String description;

	@Column(name = "name", length=256)
	private String name;

	// Bidirectional many-to-one association to Data
	@OneToMany(mappedBy = "measurement", fetch = FetchType.LAZY)
	private List<Data> data;

	public Measurement() {
	}

	public Long getMeasurementId() {
		return measurementId;
	}

	public void setMeasurementId(Long measurementId) {
		this.measurementId = measurementId;
	}

	public String getMeasurementCode() {
		return measurementCode;
	}

	public void setMeasurementCode(String measurementCode) {
		this.measurementCode = measurementCode;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
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

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Measurement [measurementId=" +
				measurementId +
				", measurementCode=" +
				measurementCode +
				", dateCreated=" +
				dateCreated +
				", description=" +
				description +
				", name=" +
				name +
				", data=" +
				data +
				"]";
	}
}