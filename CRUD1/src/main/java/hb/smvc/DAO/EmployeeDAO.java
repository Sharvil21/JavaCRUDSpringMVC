package hb.smvc.DAO;
import java.util.List;


import hb.smvc.Model.*;

public interface EmployeeDAO {
	
		
	public void addEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public void deleteEmployee(Integer employeeId);
	public Employee updateEmployee(Employee employee);
	public Employee getEmployee(int employeeId);
	
	 public List<Employee> checkEmployee(String name);
	
}
