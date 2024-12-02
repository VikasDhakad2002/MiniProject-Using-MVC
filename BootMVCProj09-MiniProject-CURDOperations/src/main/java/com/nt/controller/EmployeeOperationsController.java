package com.nt.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.Employee;
import com.nt.service.IEmployeeMgmtService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeOperationsController {
	@Autowired
	private IEmployeeMgmtService empService;
	@GetMapping("/")
	public String showHome() {
		return"home";
	}
	
	@GetMapping("/report")
	public String showEmployeeReport(Map<String,Object>map)
	{
		System.out.println("EmployeeOperationsController.showEmployeeReport()");
		//user service
		Iterable<Employee> itEmps=empService.getAllEmployees();
		//put result in model attribute
		map.put("empList", itEmps);
		//return LVN
		return "show_employee_report";
	}
	
	@GetMapping("/emp_add")
	public String showFormForsaveEmployee(@ModelAttribute("emp")Employee emp) {
		System.out.println("EmployeeOperationsController.showFormForsaveEmployee()");
		return "register_employee";
	}
	
	/*@PostMapping("/emp_add") //problem
	public String saveEmployee(@ModelAttribute("emp")Employee emp,Map<String,Object> map)
	{
		//use service
		String msg=empService.registerEmployee(emp);
		Iterable<Employee> itEmps=empService.getAllEmployees();
		//keep the result in ModelAttribute
		map.put("resultMsg", msg);
		map.put("empList", itEmps);
		return "show_employee_report";
	}*/
	
/*	@PostMapping("/emp_add") //soultion1 with limitation
	public String saveEmployee(@ModelAttribute("emp")Employee emp,Map<String,Object> map)
	{
		System.out.println("EmployeeOperationsController.saveEmployee()");
		//use service
		String msg=empService.registerEmployee(emp);
		//keep the result in ModelAttribute
		map.put("resultMsg", msg);
		return "redirect:report";
	} */
	
/*	@PostMapping("/emp_add") //soultion2 (best)
	public String saveEmployee(@ModelAttribute("emp")Employee emp,RedirectAttributes attrs)
	{
		System.out.println("EmployeeOperationsController.saveEmployee()");
		//use service
		String msg=empService.registerEmployee(emp);
		//keep the result in flashAttribute
		attrs.addFlashAttribute("resultMsg", msg);
		return "redirect:report";
	} */
	
	@PostMapping("/emp_add") //soultion3 
	public String saveEmployee(@ModelAttribute("emp")Employee emp,HttpSession ses)
	{
		System.out.println("EmployeeOperationsController.saveEmployee()");
		//use service
		String msg=empService.registerEmployee(emp);
		//keep the result in flashAttribute
		ses.setAttribute("resultMsg", msg);
		return "redirect:report";
	}
	
	@GetMapping("/emp_edit")
	public String showEditEmployeeFormPage(@RequestParam("no")int no,
			                                @ModelAttribute("emp")Employee emp) {
		//use service
		Employee emp1=empService.getEmployeeByNo(no);
		//copy data
		BeanUtils.copyProperties(emp1,emp);
		//return LVN
		return "update_employee";
	}
	
	@PostMapping("/emp_edit")
	public String editEmployee(RedirectAttributes attrs,@ModelAttribute("emp")Employee emp) {
		//use service
		String msg =empService.updateEmployee(emp);
		//add the result message as Flash Attribute
		attrs.addFlashAttribute("resultMsg",msg);
		return"redirect:report";
	}
	
	@GetMapping("/emp_delete")
	public String deleteEmployee(RedirectAttributes attrs,@RequestParam int no) {
		
		//use service
		String msg=empService.deleteEmployeeById(no);
		//keep the result in FlashAttribute
		attrs.addFlashAttribute("resultMsg",msg);
		return"redirect:report";
	}

}
