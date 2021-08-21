package za.co.antenna.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the antenna_type database table.
 * 
 */
@Entity
@Table(name = "antenna_type")
//@NamedQuery(name="Type.findAll", query="SELECT t FROM Type t")
public class AntennaType implements Serializable { // the name was meaningless
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "antenna_type_id")
	private Long antennaTypeId;

	@Column(name = "antenna_type_code", unique = true, nullable = false, updatable = false, insertable = false, length=128)
	private String antennaTypeCode;

	@Column(name = "date_created")
	private Timestamp dateCreated;

	@Column(name = "description", length=2048)
	private String description;

	@Column(name = "name", length=256)
	private String name;

	// Bidirectional many-to-one association to Antenna
	@OneToMany(mappedBy = "antennaType", fetch = FetchType.LAZY)
	private List<Antenna> antennas;

	public AntennaType() {
	}

	public Long getAntennaTypeId() {
		return antennaTypeId;
	}

	public void setAntennaTypeId(Long antennaTypeId) {
		this.antennaTypeId = antennaTypeId;
	}

	public String getAntennaTypeCode() {
		return antennaTypeCode;
	}

	public void setAntennaTypeCode(String antennaTypeCode) {
		this.antennaTypeCode = antennaTypeCode;
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

	public List<Antenna> getAntennas() {
		return antennas;
	}

	public void setAntennas(List<Antenna> antennas) {
		this.antennas = antennas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AntennaType [antennaTypeId=" +
				antennaTypeId +
				", antennaTypeCode=" +
				antennaTypeCode +
				", dateCreated=" +
				dateCreated +
				", description=" +
				description +
				", name=" +
				name +
				", antennas=" +
				antennas +
				"]";
	}
}