package edu.mum.cs.waa.controller;

import edu.mum.cs.waa.domain.Employee;
import edu.mum.cs.waa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping({ "/employees" })
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

//	@Autowired
//	private EmployeeValidator employeeValidator;

	@RequestMapping("/list")
	public String listEmployees(Model model) {
		model.addAttribute("employees", employeeService.findAll());
		return "employees";
	}

	@RequestMapping("/employee")
	public String getEmployeeById(Model model, @RequestParam("id") Long employeeId) {
		Employee employee = employeeService.findByEmployeeNumber(employeeId);
		model.addAttribute("employee", employee);
		return "employee";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewEmployeeForm(@ModelAttribute("newEmployee") Employee newEmployee) {
		return "addEmployee";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewEmployeeForm(@Valid @ModelAttribute("newEmployee") Employee employeeToBeAdded,
			BindingResult result) {
		if (result.hasErrors()) {
			return "addEmployee";
		}

		try {
			employeeService.save(employeeToBeAdded);
		} catch (Exception up) {
			System.out.println("Transaction Failed!!!");

		}

		return "redirect:/employees/list";
	}

}
