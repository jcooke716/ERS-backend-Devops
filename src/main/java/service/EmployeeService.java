package service;

import java.util.List;

import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.PendingPojo;

public interface EmployeeService {

	//Submit request ----> View employee info
		EmployeePojo fetchEmployee(int employeeId);
		
		//View pending request ---
		List<ExpensePojo> viewPending(int employeeId);
		
		//view resolved request
		List<ExpensePojo> viewResolved(int employeeId);
		
		//View Employee Info
		//EmployeePojo ViewEmployeeInfo(int employeeId);
		
		//Update employee info ----> UPDATE * WHERE 
		EmployeePojo updateEmployeeInfo(EmployeePojo employeePojo);
		
		//Submit request
		PendingPojo submitRequest(PendingPojo pendingPojo);
		
	
}
