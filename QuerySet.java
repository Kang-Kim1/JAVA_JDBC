/*
	Author : Kangmin Kim
*/

public class QuerySet {
	// final static String CHECK_TABLES = "DROP TABLE IF EXISTS Employee,
	// Department, Address, EmployeeAddress, Salary, Manager";
	final static String CREATE_DATABASE_EMPLOYEE_SYSTEM = "CREATE DATABASE IF NOT EXISTS EMPLOYEE_SYSTEM";
	final static String CREATE_EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS  Employee ( EmployeeID Int NOT NULL, LoginID nvarchar(256) NOT NULL,DepartmentID Int NOT NULL, ManagerID Int ,Title nvarchar(50) NOT NULL,BirthDate datetime NOT NULL,MartialStatus nchar(1) NOT NULL,Gender nchar(1) NOT NULL,HireDate datetime NOT NULL,VacationHours smallint NOT NULL,SickLeaveHours smallint NOT NULL,ModifiedData datetime NOT NULL)";
	final static String CREATE_DEPARTMENT_TABLE = "CREATE TABLE IF NOT EXISTS  Department (DepartmentID int NOT NULL,Name nvarchar(50) NOT NULL,GroupName nvarchar(50) NOT NULL,ModifiedData datetime NOT NULL)";
	final static String CREATE_ADDRESS_TABLE = "CREATE TABLE IF NOT EXISTS  Address (AddressID int NOT NULL,Name nvarchar(60) NOT NULL,AddressLine1 nvarchar(60) NOT NULL,AddressLine2 nvarchar(60), City nvarchar(30) NOT NULL, StateProvinceID int NOT NULL, PostalCode nvarchar(15) NOT NULL, ModifiedDate datetime NOT NULL)";
	final static String CREATE_EMPLOYEEADDRESS_TABLE = "CREATE TABLE IF NOT EXISTS  EmployeeAddress (EmployeeID int NOT NULL, AddressID int NOT NULL, ModifiedDate datetime NOT NULL)";
	final static String CREATE_SALARY_TABLE = "CREATE TABLE IF NOT EXISTS  Salary (EmployeeID int NOT NULL, Salary int NOT NULL, ModifiedDate datetime NOT NULL)";
	final static String CREATE_MANAGER_TABLE = "CREATE TABLE IF NOT EXISTS  Manager (EmployeeID int NOT NULL, Name nvarchar(30) NOT NULL, ModifiedDate datetime NOT NULL)";

	// Update Salary With EmployeeID
	public static String updateSalary(int id, int newSalary) {
		String SQL = String.format("UPDATE Salary SET Salary=%s WHERE EmployeeID = %s", newSalary + "", id + "");
		System.out.println("\tThrown Query => " +SQL);
		return SQL;
	}

	// Delete An Employee By EmployeeID
	public static String deleteEmployee(int id) {
		String SQL = String.format("DELETE FROM Employee WHERE EmployeeID = %s", id + "");
		System.out.println("\tThrown Query => " +SQL);
		return SQL;
	}

	// Display An Employee Info With ID
	public static String displayEmployee(int id) {
		String SQL = String.format(
				"SELECT Employee.LoginID, Employee.DepartmentID, Employee.ManagerID, Employee.Title, Employee.BirthDate, Employee.MartialStatus, Employee.Gender, Employee.HireDate, employee.VacationHours, employee.SickLeaveHours, Salary.Salary FROM employee INNER JOIN Salary ON Employee.EmployeeID = Salary.EmployeeID WHERE Employee.EmployeeID = %s",
				id + "");
		System.out.println("\tThrown Query => " +SQL);
		return SQL;
	}

	// Display All Registered Employees
	public static String displayAllEmployee() {
		String SQL = "SELECT Employee.EmployeeID, Employee.LoginID, Employee.DepartmentID, Employee.ManagerID, Employee.Title, Employee.BirthDate, Employee.MartialStatus, Employee.Gender, Employee.HireDate, employee.VacationHours, employee.SickLeaveHours, Salary.Salary FROM employee INNER JOIN Salary ON Employee.EmployeeID = Salary.EmployeeID";
		System.out.println("\tThrown Query => " +SQL);
		return SQL;
	}

	// Add Employee With Data
	public static String addEmployee(String[] data) {
		String SQL = String.format(
				"INSERT INTO Employee VALUES(%s, '%s', %s, %s, '%s', '%s', '%s', '%s', '%s', %s, %s, CURTIME())",
				data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9],
				data[10]);
		System.out.println("\tThrown Query => " +SQL);
		return SQL;
	}
}
