package com.neoSoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neoSoft.Dao.DepartmentDao;
import com.neoSoft.model.DepartmentsBean;

@Service
@Transactional
public class DepartmentService {
	
	@Autowired
	DepartmentDao departmentDao;

	public List<DepartmentsBean> getDepartments() {
		
		return departmentDao.getAllDepartments();
	}

	public DepartmentsBean getDepartmentById(int id) {
		
		return departmentDao.getDepartmentById(id);
	}

	public DepartmentsBean addDepartment(DepartmentsBean department) {

		return departmentDao.addDepartment(department);
	}

	public DepartmentsBean updateDepartment(DepartmentsBean department) {

		return departmentDao.updateDepartment(department);
	}

	public DepartmentsBean deleteDepartment(int id) {

		return departmentDao.deleteDepartment(id);
	}

}
