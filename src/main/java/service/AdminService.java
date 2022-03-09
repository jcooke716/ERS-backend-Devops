package service;

import java.util.List;

import dao.DButil;
import pojo.AdminPojo;
import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.FinalPojo;
import pojo.PendingPojo;

public interface AdminService {
	
	EmployeePojo fetchEmployee(int empId);
	
	AdminPojo fetchAdmin(int adminId);
	
	List<EmployeePojo> fetchAllEmployees();
	
	List<ExpensePojo> fetchAllExpenses(int empId);
	
	List<ExpensePojo> fetchAllPendingExpenses();
	
	List<ExpensePojo> fetchAllFinalExpenses();
	
	PendingPojo fetchPendingExpense(int expenseId);
	
	FinalPojo approveExpense(PendingPojo pendPojo);
	
	FinalPojo denyExpense(PendingPojo pendPojo);
	
	ExpensePojo deletePendingExpense(int expenseId);
	
	ExpensePojo fetchNewestExpense();
	
	AdminPojo loginAdmin(AdminPojo pojoIn);
	
	default void exitApplication() {};

}
