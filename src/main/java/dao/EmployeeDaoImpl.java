package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.EmployeePojo;
import pojo.ExpensePojo;

public class EmployeeDaoImpl implements EmployeeDao {

	public EmployeePojo fetchEmployee(int employeeId) {
		
		EmployeePojo employeePojo = null;
		
		Connection conn =DButil.obtainConnection();
		
		try {
		Statement stmt = conn.createStatement();
		
		String query = "SELECT * FROM employee_details " + "WHERE emp_id=" + employeeId;
		
		ResultSet rs = stmt.executeQuery(query);
		
		if(rs.next()) {
			employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getLong(5), rs.getString(6), rs.getString(7), rs.getInt(8));
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employeePojo;
	}
	
	
	//------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------
	

	public List<ExpensePojo> viewPending(int employeeId) {
		
		List<ExpensePojo> allPending = new ArrayList<>();
		
		Connection conn = DButil.obtainConnection(); 
		ExpensePojo expensePojo = null;
		
		try {
			Statement stmt = null;
			
			stmt = conn.createStatement();
		
			String query = "SELECT reimbursements_pending.pend_id AS expense_id, employee_details.emp_first_name AS employee_name, reimbursements_pending.pend_amount AS amount, reimbursements_pending.pend_reason AS reason, reimbursements_pending.pend_request_time AS created_at, admin_details.admin_first_name AS manager, reimbursements_pending.pend_status AS status FROM reimbursements_pending LEFT JOIN employee_details ON reimbursements_pending.pend_request=employee_details.emp_id LEFT JOIN admin_details ON reimbursements_pending.pend_response=admin_details.admin_id WHERE pend_request=" + employeeId;

			ResultSet rs = stmt.executeQuery(query);
						
				while (rs.next())
					 expensePojo = new ExpensePojo(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), 
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
					
						allPending.add(expensePojo);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return allPending;
	}
	
	//-------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------
	
	

	public List<ExpensePojo> viewResolved(int employeeId) {
		
		List<ExpensePojo> allResolved = new ArrayList<>();
		
		Connection conn = DButil.obtainConnection(); 
		ExpensePojo expensePojo = null;
		
		try {
		Statement stmt = null;
		
		stmt = conn.createStatement();
	
		String query = "SELECT reimbursements_final.final_id AS expense_id, employee_details.emp_first_name AS employee_name, reimbursements_final.final_amount AS amount, reimbursements_final.final_reason AS reason, reimbursements_final.final_request_time AS resolved_at, admin_details.admin_first_name AS manager, reimbursements_final.final_status AS status FROM reimbursements_final LEFT JOIN employee_details ON reimbursements_final.final_request=employee_details.emp_id LEFT JOIN admin_details ON reimbursements_final.final_response=admin_details.admin_id WHERE final_request=" + employeeId;

		ResultSet rs = stmt.executeQuery(query);
					
			while (rs.next())
				 expensePojo = new ExpensePojo(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4), 
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				
					allResolved.add(expensePojo);
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return allResolved;
	}
	
	
	//-------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------
	
	
	

	public EmployeePojo updateEmployeeInfo(EmployeePojo employeePojo) {
		
		EmployeePojo updatedEmployeePojo = null;
		
		Connection conn = DButil.obtainConnection();
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String queryUpdate = "UPDATE employee_details SET employee_password=" + "'" + employeePojo.getEmployeePassword() + "'" + "AND emp_first_name=" + "'" + employeePojo.getEmployeeFirstName()+ "'"
				        + "AND emp_last_name=" + "'" + employeePojo.getEmployeeLastName() + "'" + "AND emp_contact=" + employeePojo.getEmployeeContact() + "AND emp_email=" + 
				        "'" + employeePojo.getEmployeeEmail() + "'" + "AND emp_address=" + "'" + employeePojo.getEmployeeAddress() + "'" + "WHERE emp_id=" + employeePojo.getEmployeeId();
		
		try {
			int rowsTo = stmt.executeUpdate(queryUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updatedEmployeePojo;
	}

}
