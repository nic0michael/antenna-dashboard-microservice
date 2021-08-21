package za.co.antenna.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the 'antenna' database table.
 * 
 */
@Entity
@Table(name = "antenna")
//@NamedQuery(name="Antenna.findAll", query="SELECT a FROM Antenna a")
public class Antenna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "antenna_id")
	private Long antennaId;

	@Column(name = "antenna_code", unique = true, nullable = false, updatable = false, insertable = false, length=128)
	private String antennaCode;

	@Column(name = "date_created")
	private Timestamp dateCreated;

	@Column(name = "description", length=2048)
	private String description;

	@Column(name = "name", length=256)
	private String name;

	@Column(name = "antenna_type_code", length=128)
	private String antennaTypeCode;

	@Column(name = "main_antenna_code", length=128)
	private String mainAntennaCode;

	// Bidirectional many-to-one association to Antenna
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "main_antenna_code", nullable = false, updatable = false, insertable = false, referencedColumnName = "antenna_code")
	private Antenna antenna;

	// Bidirectional many-to-one association to Antenna
	@OneToMany(mappedBy = "antenna", fetch = FetchType.LAZY)
	private List<Antenna> antennas;

	// Bidirectional many-to-one association to Type
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "antenna_type_code", nullable = false, updatable=false, insertable=false, referencedColumnName = "antenna_type_code")
	private AntennaType antennaType;

	// Bidirectional many-to-one association to Data
	@OneToMany(mappedBy = "antenna", fetch = FetchType.LAZY)
	private List<Data> antennaData;

	public Antenna() {
	}

	public Long getAntennaId() {
		return antennaId;
	}

	public void setAntennaId(Long antennaId) {
		this.antennaId = antennaId;
	}

	public String getAntennaCode() {
		return antennaCode;
	}

	public void setAntennaCode(String antennaCode) {
		this.antennaCode = antennaCode;
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

	public Antenna getAntenna() {
		return antenna;
	}

	public void setAntenna(Antenna antenna) {
		this.antenna = antenna;
	}

	public List<Antenna> getAntennas() {
		return antennas;
	}

	public void setAntennas(List<Antenna> antennas) {
		this.antennas = antennas;
	}

	public AntennaType getAntennaType() {
		return antennaType;
	}

	public void setAntennaType(AntennaType antennaType) {
		this.antennaType = antennaType;
	}

	public List<Data> getAntennaData() {
		return antennaData;
	}

	public void setAntennaData(List<Data> antennaData) {
		this.antennaData = antennaData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Antenna [antennaId=" +
				antennaId +
				", antennaCode=" +
				antennaCode +
				", dateCreated=" +
				dateCreated +
				", description=" +
				description +
				", name=" +
				name +
				",antenna=" +
				antenna +
				", antennas=" +
				antennas +
				", antennaTypeCode=" +
				antennaTypeCode +
				", antennaData=" +
				antennaData +
				"]";
	}
}