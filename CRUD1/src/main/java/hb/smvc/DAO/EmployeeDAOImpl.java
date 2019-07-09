package hb.smvc.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hb.smvc.Model.*;
import org.hibernate.criterion.*;
import org.hibernate.*;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addEmployee(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {

		/*
		 * return
		 * sessionFactory.getCurrentSession().createQuery("from Employee").list();
		 */
		
		 return sessionFactory.getCurrentSession().createCriteria(Employee.class).list();
		

	}

	@Override
	public void deleteEmployee(Integer employeId) {
		Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employeId);
		if (null != employee) {
			this.sessionFactory.getCurrentSession().delete(employee);
		}
	}

	public Employee getEmployee(int empId) {
		System.out.println("test");
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, empId);
	}

	public Employee updateEmployee(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
		return employee;
	}

	
	  @SuppressWarnings("unchecked") 
	  public List<Employee> checkEmployee(String name) { 
		/*
		 * List<Employee> employees = new ArrayList<Employee>();
		 * System.out.println("Inside CheckEmployee Method"); Criteria cri
		 * =sessionFactory.getCurrentSession().createCriteria(Employee.class).add(
		 * Restrictions.like("name", name).ignoreCase()); List<Employee> objlst =
		 * cri.list(); if (objlst.size() > 0){ employees = objlst; } return employees;
		 */
		  
		  System.out.println("Inside checkEmployee Method now\nThe name Entered is " + name);
		  
		  
		  
		  return sessionFactory.getCurrentSession().createCriteria(Employee.class).add(Restrictions.like("name", name).ignoreCase()).list();
		  
		  
	  
	  }
	  
	  
	  
	  
	  
	 

}
