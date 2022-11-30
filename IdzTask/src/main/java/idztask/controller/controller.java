package idztask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import idztask.entity.Employee;
import idztask.response.ResponseApi;
import idztask.service.EmployeeService;

@Controller
public class controller {

	@Autowired
	EmployeeService service;

	@GetMapping("/")
	public String getEmployees(Model model) {
		model.addAttribute("ListEmployee", service.findAllEmp());
		return "index";

	}

	@GetMapping("EmployeeForm")
	public String registerEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_Employee";
	}

	@PostMapping("saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		service.saveEmployee(employee);
		return "redirect:/";
	}

	@GetMapping("/showFormUpdate/{id}")
	public String Update(@PathVariable(value = "id") int id, Model model) {
		// get emp from service
		Employee employeeById = service.updateEmployee(id);
		// set emp as model attributr to form
		model.addAttribute("employee", employeeById);
		return "update_employee";
	}

	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable(value = "id") int id, Model model) {
		String deleteEmployee = service.deleteEmployee(id);
		model.addAttribute("employee", deleteEmployee);
		return "redirect:/";
	}

	@GetMapping("/getall")
	public ResponseEntity<ResponseApi> getAll() {
		List<Employee> employee = service.findAllEmp();
		return new ResponseEntity<>(
				ResponseApi.builder().data(employee).message("Employee Data Fetched Successfuly").error(false).build(),
				HttpStatus.OK);
	}

}
