package hb.smvc.Controller;
import java.io.IOException;
import hb.smvc.DAO.*;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import hb.smvc.Model.Employee;
import hb.smvc.Service.EmployeeService;
import hb.smvc.Service.EmployeeServiceImpl;
import hb.smvc.DAO.*;








@Controller
public class EmployeeController {

	private static final Logger logger = Logger
			.getLogger(EmployeeController.class);
	SessionFactory sessionFactory;
	
	public EmployeeController() {
		System.out.println("EmployeeController()");
	}

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {
		List<Employee> listEmployee = employeeService.getAllEmployees();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Employee employee = new Employee();
		model.addObject("employee", employee);
		model.setViewName("EmployeeForm");
		return model;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@Valid @ModelAttribute Employee employee, BindingResult result) {
		
		
		if(result.hasErrors()) {
			return new ModelAndView("EmployeeForm");
		}
		
		if (employee.getId() == 0) { // if employee id is 0 then creating the
			// employee other updating the employee
			employeeService.addEmployee(employee);
		} else {
			employeeService.updateEmployee(employee);
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		employeeService.deleteEmployee(employeeId);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		Employee employee = employeeService.getEmployee(employeeId);
		ModelAndView model = new ModelAndView("EmployeeForm");
		model.addObject("employee", employee);

		return model;
	}

	
	  @RequestMapping(value= "Controller/GetServlet", method = RequestMethod.GET)
	  public void checkEmpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String name =request.getParameter("name");
	  System.out.println("BLUR FUCTION CALLED THE JAVA FUNCTION IN EmployeeController.java \n");
	  System.out.println("Checking whether the Name " + name + "Exists or not\n Now going into EmployeeDAO \n");
	  response.setContentType("text/plain"); 
	  response.setCharacterEncoding("UTF-8");
	  
	  try { 
			/*
			 * List<Employee> val1 = listEmployee(name); System.out.println(val1);
			 */
		  List<Employee> checkEmpl = (employeeService.checkEmployee(name));
		  System.out.println("username Entered is " + checkEmpl);
		  if(checkEmpl.isEmpty()) {
			  throw new NullPointerException();
		  }
		  
		  response.getWriter().write("Name Already Exists. Please Enter Another One");
	  
	  }catch(NullPointerException e) { 
		  System.out.println("\nInside NullPointerException CATCH BLOCK NOW\n\nUsername doesn't exist and Hence can be used\n "); 
		  response.getWriter().write("Name is Valid");
	  
	  }catch(Exception n) {
		  System.out.println("\nIm inside only Exception CATCH BLOCK now");
		  
	  }
	  finally { 
	  System.out.println("\nInside FINALLY BLOCK now"); 
	  System.gc(); 
	  }
	  
 


}
}
