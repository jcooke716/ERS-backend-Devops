package endpoints;

import java.util.List;

import io.javalin.Javalin;
import pojo.AdminPojo;
import pojo.EmployeePojo;
import pojo.ExpensePojo;
import pojo.PendingPojo;
import service.AdminService;
import service.AdminServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class EndPointsMain {

	public static void main(String[] args) {
		
		
		AdminService adminService = new AdminServiceImpl();
		Javalin myServer = Javalin.create((config)->config.enableCorsForAllOrigins()).start(4040);
		System.out.println();
		
		// Fetch employee endpoint
		myServer.get("/api/v1/employees/{eid}", (ctx) -> {
			String empId = ctx.pathParam("eid");
			EmployeePojo fetchedEmployee = adminService.fetchEmployee(Integer.parseInt(empId));
			ctx.json(fetchedEmployee);
		});
		
		// fetch admin endpoint
		myServer.get("/api/v1/admins/{aid}", (ctx) -> {
			String adminId = ctx.pathParam("aid");
			AdminPojo fetchedAdmin = adminService.fetchAdmin(Integer.parseInt(adminId));
			ctx.json(fetchedAdmin);
		});
		
		// fetch all employees endpoint
		myServer.get("/api/v1/employees", (ctx) -> {
			List<EmployeePojo> allEmployees = adminService.fetchAllEmployees();
			ctx.json(allEmployees);
		});
		
		// fetch all employee expenses endpoint
		myServer.get("/api/v1/expenses/{eid}", (ctx) -> {
			String empId = ctx.pathParam("eid");
			List<ExpensePojo> allEmpExpenses = adminService.fetchAllExpenses(Integer.parseInt(empId));
			ctx.json(allEmpExpenses);
		});
		
		// fetch all pending expenses endpoint
		myServer.get("/api/v1/expenses/pending", (ctx) -> {
			List<ExpensePojo> allPendingExpenses = adminService.fetchAllPendingExpenses();
			ctx.json(allPendingExpenses);
		});
		
		// fetch all final expenses endpoint
		myServer.get("/api/v1/expenses/resolved", (ctx) -> {
			List<ExpensePojo> allFinalExpenses = adminService.fetchAllFinalExpenses();
			ctx.json(allFinalExpenses);
		});
		
		// admin login endpoint
		myServer.post("/api/v1/admins/{aid}", (ctx) -> {
			String adminId = ctx.pathParam("aid");
			AdminPojo adminPojo = ctx.bodyAsClass(AdminPojo.class);
			AdminPojo loginPojo = adminService.loginAdmin(adminPojo);
			ctx.json(loginPojo);
		});
		
		// fetch pending expense endpoint
		myServer.get("/api/v1/expenses/pending/{pid}", (ctx) -> {
			String pendId = ctx.pathParam("pid");
			PendingPojo pendPojo = adminService.fetchPendingExpense(Integer.parseInt(pendId));
			ctx.json(pendPojo);
		});
		
		// delete pending pojo endpoint
		myServer.delete("/api/v1/expenses/pending/{pid}", (ctx) -> {
			String pendId = ctx.pathParam("pid");
			ExpensePojo deletedPojo = adminService.deletePendingExpense(Integer.parseInt(pendId));
			ctx.result("Resolution successful!");
		});
		
		
		//-----------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------
		//Employees Menu
		
		EmployeeService employeeService = new EmployeeServiceImpl();
		
		//Employee Info screen
		myServer.get("/api/v1/employee/{eid}", (ctx) -> {
			String employeeId = ctx.pathParam("eid");
			EmployeePojo fetchedEmployee = employeeService.fetchEmployee(Integer.parseInt(employeeId));
			ctx.json(fetchedEmployee);
		});
		
		 
		// fetch all pending request for employee endpoint
		myServer.get("/api/v1/pending/{eid}", (ctx) -> {
			String employeeId = ctx.pathParam("eid");
			List<ExpensePojo> allPendingExpenses = employeeService.viewPending(Integer.parseInt(employeeId));
			ctx.json(allPendingExpenses);
		});
		
		
		// fetch all resolved Request endpoint
		myServer.get("/api/v1/resolved/{eid}", (ctx) -> {
			String employeeId = ctx.pathParam("eid");
			List<ExpensePojo> allFinalExpenses = employeeService.viewResolved(Integer.parseInt(employeeId));
			ctx.json(allFinalExpenses);
				});
		
		myServer.put("/api/v1/update", (ctx) -> {
			EmployeePojo updateEmployee = ctx.bodyAsClass(EmployeePojo.class); 
			//Update book in the database
			EmployeePojo updatedEmployee = employeeService.updateEmployeeInfo(updateEmployee);
			//returned a response of the book with the generated bookId 
			ctx.json(updatedEmployee);
			
		});
			
		//Submit a request
			
			myServer.post("/api/v1/submitrequest", (ctx) -> {
				PendingPojo submitR = ctx.bodyAsClass(PendingPojo.class);
				PendingPojo submitRequest = employeeService.submitRequest(submitR);
				
				ctx.json(submitRequest);
		});
		

		
		
	}
}
