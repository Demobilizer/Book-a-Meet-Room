package com.neoSoft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neoSoft.model.DepartmentsBean;
import com.neoSoft.service.DepartmentService;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping(value = "/departments", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<DepartmentsBean> getAllDepartments()
	{
		return departmentService.getDepartments();
	}
	
	@RequestMapping(value = "/departments/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public DepartmentsBean getDepartmentById(@PathVariable("id") int id)
	{
		return departmentService.getDepartmentById(id);
	}
	
	@RequestMapping(value = "departments", method = RequestMethod.POST, headers = "Accept=application/json")
	public DepartmentsBean addDepartment(@RequestBody DepartmentsBean department)
	{
		return departmentService.addDepartment(department);
	}
	
	@RequestMapping(value = "departments", method = RequestMethod.PUT, headers = "Accept=application/json")
	public DepartmentsBean updateDepartment(@RequestBody DepartmentsBean department)
	{
		return departmentService.updateDepartment(department);	
	}
	
	@RequestMapping(value = "departments/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public DepartmentsBean deleteDepartment(@PathVariable("id") int id)
	{
		return departmentService.deleteDepartment(id);	
	}
	
}
