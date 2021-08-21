package za.co.antenna.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import za.co.antenna.dtos.EmployeePersistRequest;
import za.co.antenna.model.Employee;

public class Utils {
	private static final Logger log = LoggerFactory.getLogger(Utils.class);

	@Value("${project.date.format}")
	static String dateUsaFormat;	// 06/17/2020

	@Value("${project.date.iso.format}")
	static String dateIsoFormat;	// "yyyy-MM-dd

	@Value("${project.date.rsa.format}")
	static String dateRsaFormat;	// "dd-MM-yyyy"

	@Value("${project.time.rsa.format}")
	static String timeRsaFormat;	// "HH:mm:ss"

	@Autowired
	public static BCryptPasswordEncoder passwordEncoder;

	/**
	 * project.date.rsa.format=dd-MM-yyyy
	 */
	public static String getDateFormatRsa() {
		if (dateRsaFormat == null) {
			dateRsaFormat = "dd-MM-yyyy";
		}

		return dateRsaFormat.trim();
	}

	/**
	 * project.time.rsa.format=HH:mm:ss
	 */
	public static String getTimeFormatRsa() {
		if (timeRsaFormat == null) {
			timeRsaFormat = "HH:mm:ss";
		}

		return timeRsaFormat.trim();
	}

	/**
	 * project.date.rsa.format=dd-MM-yyyy
	 * project.time.rsa.format=HH:mm:ss
	 */
	public static String getDateTimeFormatRsa() {
		if (dateRsaFormat == null) {
			dateRsaFormat = "dd-MM-yyyy";
		}

		if (timeRsaFormat == null) {
			timeRsaFormat = "HH:mm:ss";
		}

		return (dateRsaFormat.trim() + " " + timeRsaFormat.trim());
	}

	public static String makeMysqlDateNow() {
		Date dateNow = new Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String mysqlDateNow = formatter.format(dateNow);

		return mysqlDateNow;
	}

	public static String dateToString(Date dateToConvert) {
		String dateToString=null;
		log.info("ANTENNA : Utils : dateToString : converting date:" + dateToConvert);
		
		if (dateUsaFormat == null) {
			dateUsaFormat = "MM/dd/yyyy";
		}
		if (dateIsoFormat == null) {
			dateIsoFormat = "yyyy-MM-dd";
		}
		if(dateToConvert!=null) {
			DateTimeFormatter newPattern = DateTimeFormatter.ofPattern(dateUsaFormat);
			LocalDateTime datetime = dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			dateToString = datetime.format(newPattern);
		}

		log.info("ANTENNA : Utils : dateToString : converted to :" + dateToString);
		
		return dateToString;
	}

	/**
	 * 
	 * project.date.usa.format=MM/dd/yyyy
	 * project.date.iso.format=yyyy-MM-dd
	 * 
	 */
	public static Date convertStringToDate(String sDate) {
		String saDateFormat = "dd/MM/yyyy";
		
		if (dateUsaFormat == null) {
			dateUsaFormat = "MM/dd/yyyy";
		}
		
		if (dateIsoFormat == null) {
			dateIsoFormat = "yyyy-MM-dd";
		}
		
		String dateFormt = dateUsaFormat;
		Date date = null;
		
		if (StringUtils.isNotEmpty(sDate)) {
			if (sDate.contains("/")) {
				dateFormt = dateUsaFormat;
		
				try {
					date = new SimpleDateFormat(dateFormt).parse(sDate);
				}
				catch (ParseException e) {
					dateFormt =saDateFormat;
				}
			}
			else if (sDate.contains("-")) {
				dateFormt = dateIsoFormat;
			}
			
			if (sDate.length() > 10) {
				sDate = sDate.substring(0, 10);
			}
			try {
				date = new SimpleDateFormat(dateFormt).parse(sDate);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}

		log.info("ANTENNA : Utils : convertStringToDate : converting date:" + date);
		return date;
	}
	
	//////////////////// EMPLOYEE ////////////////////

	public static List<EmployeePersistRequest> makeEmployeePersistRequestList(List<Employee> employees) {
		List<EmployeePersistRequest> employeePersistRequests = new ArrayList<>();
		for (Employee employee : employees) {
			EmployeePersistRequest employeePersistRequest = convertToEmployeePersistRequest(employee);
			employeePersistRequests.add(employeePersistRequest);
		}

		return employeePersistRequests;
	}

	public static List<Employee> makeEmployeeList(List<EmployeePersistRequest> employeePersistRequests) {
		List<Employee> employees = new ArrayList<>();
		for (EmployeePersistRequest employeePersistRequest : employeePersistRequests) {
			Employee employee = convertToEmployee(employeePersistRequest);
			employees.add(employee);
		}

		return employees;
	}

	public static EmployeePersistRequest convertToEmployeePersistRequest(Employee employee) {
		log.info("ANTENNA : Utils : convertToEmployeePersistRequest : Employee :" + employee);
		EmployeePersistRequest employeePersistRequest = new EmployeePersistRequest();
		if (employee.getEmployeeId() != null) {
			employeePersistRequest.setEmployeeId(employee.getEmployeeId().toString());
		}

		if (employee.getFullName() != null) {
			employeePersistRequest.setFullName(employee.getFullName().toUpperCase());
		}
		
		if (employee.getIdNumber() != null) {
			employeePersistRequest.setIdNumber(employee.getIdNumber());
		}
		
		if (employee.getDetails() != null) {
			employeePersistRequest.setDetails(employee.getDetails());
		}
		
		if (employee.getTelephone() != null) {
			employeePersistRequest.setTelephone(employee.getTelephone());
		}
		
		if (employee.getCellphone() != null) {
			employeePersistRequest.setCellphone(employee.getCellphone());
		}
		
		if (employee.getEmail() != null) {
			employeePersistRequest.setEmail(employee.getEmail());
		}
		
		if (employee.getPassword() != null) {
		    employeePersistRequest.setPassword(employee.getPassword());
		}
		
		if (employee.getAuthority() != null) {
		    employeePersistRequest.setAuthority(employee.getAuthority());
		}
		
		if (employee.getUserId() != null) {
		    employeePersistRequest.setUserId(employee.getUserId());
		}
		
		if (employee.getDateCreated() != null) {
		    employeePersistRequest.setDateCreated(dateToString(employee.getDateCreated()));
		}

		if (employee.getEnabled() != null && employee.getEnabled()==1) {
			employeePersistRequest.setEnabled("1");
		}
		else {
			employeePersistRequest.setEnabled("0");
		}
		
		return employeePersistRequest;
	}

	public static Employee convertToEmployee(EmployeePersistRequest employeePersistRequest) {
		Employee employee = convertToEmployee(employeePersistRequest, new Employee());
		return employee;
	}

	public static Employee convertToEmployee(EmployeePersistRequest employeePersistRequest, Employee employee) {		
		log.info("ANTENNA : Utils : convertToEmployee : EmployeePersistRequest :" + employeePersistRequest);
		employee.setFullName(employeePersistRequest.getFullName().toUpperCase());
		employee.setIdNumber(employeePersistRequest.getIdNumber());
		employee.setDetails(employeePersistRequest.getDetails());
		employee.setTelephone(employeePersistRequest.getTelephone());
		employee.setCellphone(employeePersistRequest.getCellphone());
		employee.setEmail(employeePersistRequest.getEmail());
	    employee.setPassword(employeePersistRequest.getPassword());
	    employee.setAuthority(employeePersistRequest.getAuthority());
	    employee.setUserId(employeePersistRequest.getUserId());
	    
	    if(StringUtils.isNotEmpty(employeePersistRequest.getDateCreated())){
	    	employee.setDateCreated(convertStringToDate(employeePersistRequest.getDateCreated()));
	    }

		if (StringUtils.isNotEmpty(employeePersistRequest.getPassword())) {
			if (passwordEncoder == null) {
				passwordEncoder = new BCryptPasswordEncoder();
			}

			employee.setPassword(passwordEncoder.encode(employeePersistRequest.getPassword()));
		}

		if (StringUtils.isNotEmpty(employeePersistRequest.getEnabled()) &&
								  "1".equals(employeePersistRequest.getEnabled())) {
			employee.setEnabled(1);
		}
		else {
			employee.setEnabled(0);
		}

		log.info("ANTENNA : Utils : convertToEmployee : Employee :" + employee);
		
		return employee;
	}
}
