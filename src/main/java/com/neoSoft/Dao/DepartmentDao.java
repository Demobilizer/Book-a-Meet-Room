package com.neoSoft.Dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neoSoft.model.DepartmentsBean;

@Repository
public class DepartmentDao {
	
	@Autowired
	SessionFactory mySessionFactory;
	
	public void setMySessionFactory(SessionFactory mySessionFactory) {
		this.mySessionFactory = mySessionFactory;
	}
	

	public List<DepartmentsBean> getAllDepartments() {

		return mySessionFactory.getCurrentSession().createQuery("from DepartmentsBean",DepartmentsBean.class).getResultList();
	}

	public DepartmentsBean getDepartmentById(int id) {

		return mySessionFactory.getCurrentSession().createQuery("select d from DepartmentsBean d where d.departmentId="+id,DepartmentsBean.class).getSingleResult();
	}

	public DepartmentsBean addDepartment(DepartmentsBean department) {

		mySessionFactory.getCurrentSession().save(department);
		return department;
	}

	public DepartmentsBean updateDepartment(DepartmentsBean department) {

		mySessionFactory.getCurrentSession().saveOrUpdate(department);
		
		return department;
	}

	public DepartmentsBean deleteDepartment(int id) {

		DepartmentsBean department = this.getDepartmentById(id);
		mySessionFactory.getCurrentSession().delete(department);
		return department;
	}

}
