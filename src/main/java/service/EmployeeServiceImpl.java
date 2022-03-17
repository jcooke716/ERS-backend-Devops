package service;

import java.util.List;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.PendingPojo;

public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeDao employeeDao;
	
public EmployeeServiceImpl() {
		
		employeeDao = new EmployeeDaoImpl();
}



	@Override
	public EmployeePojo fetchEmployee(int employeeId) {
		
		return employeeDao.fetchEmployee(employeeId);
	}

	@Override
	public List<ExpensePojo> viewPending(int employeeId) {
		
		return employeeDao.viewPending(employeeId);
	}

	@Override
	public List<ExpensePojo> viewResolved(int employeeId) {
		
		return employeeDao.viewResolved(employeeId);
	}

	@Override
	public EmployeePojo updateEmployeeInfo(EmployeePojo employeePojo) {
		
		return employeeDao.updateEmployeeInfo(employeePojo);
	}



	@Override
	public PendingPojo submitRequest(PendingPojo pendingPojo) {
		
		return employeeDao.submitRequest(pendingPojo);
	}

}
