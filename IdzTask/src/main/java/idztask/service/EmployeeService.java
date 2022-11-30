package idztask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idztask.entity.Employee;
import idztask.repository.UserRepository;

@Service
public class EmployeeService {

	@Autowired
	UserRepository userRepository;

	/**
	 * findAllEmp() API is used to fetched All Employee Data in Json Formate.
	 */
	public List<Employee> findAllEmp() {
		List<Employee> findAll = userRepository.findAll();
		return findAll;
	}

	/**
	 * saveEmployee() API is used to Add Employee Data.
	 */
	public void saveEmployee(Employee employee) {
		userRepository.save(employee);

	}

	/**
	 * updateEmployee() API is used to Update Employee Data.
	 */
	public Employee updateEmployee(int id) {
		Optional<Employee> findById = userRepository.findById(id);

		Employee employee = null;
		if (findById.isPresent()) {
			employee = findById.get();
			employee.setEmail(employee.getEmail());
			employee.setFirstName(employee.getFirstName());
			employee.setLastName(employee.getLastName());
			employee.setSalary(employee.getSalary());
			userRepository.save(employee);
		} else {
			throw new RuntimeException("Please insert currect data");
		}
		return employee;
	}

	/**
	 * deleteEmployee() API is used to Delete Employee Data.
	 */
	public String deleteEmployee(int id) {
		userRepository.deleteById(id);
		return "Employee delete successfully";
	}

}
