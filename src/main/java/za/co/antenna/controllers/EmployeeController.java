package za.co.antenna.controllers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import za.co.antenna.dtos.EmployeePersistRequest;
import za.co.antenna.model.Employee;
import za.co.antenna.servicemanagers.EmployeeServiceManager;
import za.co.antenna.utils.Utils;



@Controller
@RequestMapping("/antenna-dashboard/employees")
public class EmployeeController {
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired 
	EmployeeServiceManager emplmod;	
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = emplmod.findAll();
		model.addAttribute("employeesList", employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/list")
	public String displayHome(Model model) {
		List<Employee> employees = emplmod.findAll();
		model.addAttribute("employeesList", employees);
		return "employees/list-employees";
	}

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		Employee employee=new Employee();
		EmployeePersistRequest  employeetPersistRequest=Utils.convertToEmployeePersistRequest(employee);
		model.addAttribute("employeetPersistRequest", employeetPersistRequest);		
		return "employees/new-employee";
	}


	@PostMapping("/save")
	public String createEmployee(EmployeePersistRequest  employeePersistRequest,Model model) {
		log.info("ANTENNA : EmployeeController : createEmployee : saving employee from  EmployeePersistRequest: "+employeePersistRequest);
		
		if(StringUtils.isNotBlank(employeePersistRequest.getEmployeeId() )  && StringUtils.isNumeric(employeePersistRequest.getEmployeeId()) ) {
			log.info("ANTENNA : EmployeeController : createEmployee : updating employee");
			emplmod.update(employeePersistRequest);
			
			
		} else {
			log.info("ANTENNA : EmployeeController : createEmployee : saving new employee");
			emplmod.save(employeePersistRequest);
		}
		// use a redirect to prevent duplicate submissions
		log.info("ANTENNA : EmployeeController : createEmployee : redirecting to employees page");
		return "redirect:/antenna-plotter/employees";
	}

	
	@GetMapping("/remove}")
	public String deleteEmployee(@RequestParam(value = "id") Long employeeId) {
		emplmod.delete(employeeId);
		return "redirect:/projects";
	}
	

	@GetMapping("/change}")
	public String updateEmployee(@RequestParam(value = "id") Long employeeId,Model model) {
		Employee employee=emplmod.findByEmployeeId(employeeId);
		model.addAttribute("employee",employee);
		return "redirect:/employees/new";
	}

	@GetMapping("/maakdood")
	public String removeEmployee(@RequestParam(value = "id") Long employeeId,Model model) {
		log.info("ANTENNA : EmployeeController : removeEmployee : to update project with project_id : "+employeeId);
		emplmod.delete(employeeId);
		return "redirect:/employees";
	}
	
	@GetMapping("/verander")
	public String displayEmployeetFormToUpdate(@RequestParam(value = "id") Long employeeId,Model model) {
		log.info("ANTENNA : EmployeeController : displayEmployeetFormToUpdate : to update project with project_id : "+employeeId);		
		if(employeeId!=null) {
			Employee employee=emplmod.findByEmployeeId(employeeId);
			EmployeePersistRequest  employeetPersistRequest=Utils.convertToEmployeePersistRequest(employee);
			log.info("ANTENNA : EmployeeController : displayEmployeetFormToUpdate : created EmployeePersistRequest : "+employeetPersistRequest);
			model.addAttribute("employeetPersistRequest", employeetPersistRequest);
		}
		log.info("ANTENNA : EmployeeController : displayEmployeetFormToUpdate : displaying form");
		return "employees/new-employee";	
	}
	
	@GetMapping("/workflow")
	public String displayEmployeetFormToWorkflow(@RequestParam(value = "id") Long employeeId,Model model) {
		log.info("ANTENNA : EmployeeController : displayEmployeetFormToUpdate : to update project with project_id : "+employeeId);		
		if(employeeId!=null) {
			Employee employee=emplmod.findByEmployeeId(employeeId);
			EmployeePersistRequest  employeetPersistRequest=Utils.convertToEmployeePersistRequest(employee);
			log.info("ANTENNA : EmployeeController : displayEmployeetFormToUpdate : created EmployeePersistRequest : "+employeetPersistRequest);
			model.addAttribute("employeetPersistRequest", employeetPersistRequest);
		}
		log.info("ANTENNA : EmployeeController : displayEmployeetFormToUpdate : displaying form");
		return "employees/workflow-employee";	
	}
}
