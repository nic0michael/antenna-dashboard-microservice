package za.co.antenna.dtos;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MeasurementRequest")
public class MeasurementRequest {
	private String measurementType;
	private String antennaCodes;
	
	public MeasurementRequest() {}

	public MeasurementRequest(String measurementType, String antennaCodes) {
		super();
		this.measurementType = measurementType;
		this.antennaCodes = antennaCodes;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}

	public String getAntennaCodes() {
		return antennaCodes;
	}

	public void setAntennaCodes(String antennaCodes) {
		this.antennaCodes = antennaCodes;
	}

	@Override
	public String toString() {
		return "MeasurementRequest [measurementType=" + measurementType + ", antennaCodes=" + antennaCodes + "]";
	}
	
	
	
}
