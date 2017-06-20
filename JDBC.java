/*
	Author : Kangmin Kim
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC {
	static final String ID = "root";
	static final String PW = "1111";
	static final String DRIVER = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost/";

	public static void main(String[] args) {
		String SQL = "";

		try {
			Class.forName(DRIVER);

			Connection con = DriverManager.getConnection(URL, ID, PW);
			Statement stmt = con.createStatement();

			System.out.println("Connection Created!");

			// Create Databse
			SQL = QuerySet.CREATE_DATABASE_EMPLOYEE_SYSTEM;
			stmt.executeUpdate(SQL);
			System.out.println("DB Created");

			SQL = "USE Employee_System";
			stmt.execute(SQL);
			System.out.println("DB Selected");

			SQL = QuerySet.CREATE_EMPLOYEE_TABLE;
			stmt.executeUpdate(SQL);
			SQL = QuerySet.CREATE_DEPARTMENT_TABLE;
			stmt.executeUpdate(SQL);
			SQL = QuerySet.CREATE_ADDRESS_TABLE;
			stmt.executeUpdate(SQL);
			SQL = QuerySet.CREATE_EMPLOYEEADDRESS_TABLE;
			stmt.executeUpdate(SQL);
			SQL = QuerySet.CREATE_SALARY_TABLE;
			stmt.executeUpdate(SQL);
			SQL = QuerySet.CREATE_MANAGER_TABLE;
			stmt.executeUpdate(SQL);
			System.out.println("TABLES Created");

			while (true) {
				Scanner sc = new Scanner(System.in);
				
				// MENUs
				System.out.println("1. Update Salary(id, new salary)");
				System.out.println("2. Delete Employee(id)");
				System.out.println("3. Display An Employee's Detail(id)");
				System.out.println("4. Dispaly All Employees' Detail");
				System.out.println("5. Add New Employee");
				System.out.println("6. EXIT\n");
				
				int input = sc.nextInt();
				int id;
				
				// ResultSet to Handle SELECT Commands
				ResultSet rs;
				switch (input) {
				case 1:
					System.out.println("\tEnter id and new salary(ex. 2 10000)");
					id = sc.nextInt();
					int salary = sc.nextInt();
					SQL = QuerySet.updateSalary(id, salary);
					stmt.executeUpdate(SQL);
					break;

				case 2:
					System.out.println("\tEnter id (ex. 2)");
					id = sc.nextInt();
					SQL = QuerySet.deleteEmployee(id);
					stmt.executeUpdate(SQL);
					break;

				case 3:
					System.out.println("\tEnter id (ex. 2)");
					id = sc.nextInt();
					SQL = QuerySet.displayEmployee(id);
					rs = stmt.executeQuery(SQL);
					System.out.println("\t\t[ EmployeeID : " + id + " ]");
					while (rs.next()) {
						System.out.println("\t\tLoginID : " + rs.getString("Employee.LoginID"));
						System.out.println("\t\tDepartmentID : " + rs.getString("Employee.DepartmentID"));
						System.out.println("\t\tManagerID : " + rs.getString("Employee.ManagerID"));
						System.out.println("\t\tTitle : " + rs.getString("Employee.Title"));
						System.out.println("\t\tBirthDate : " + rs.getString("Employee.BirthDate"));
						System.out.println("\t\tMartialStatus : " + rs.getString("Employee.MartialStatus"));
						System.out.println("\t\tGender : " + rs.getString("Employee.Gender"));
						System.out.println("\t\tHireDate : " + rs.getString("Employee.HireDate"));
						System.out.println("\t\tVacationHours : " + rs.getString("Employee.VacationHours"));
						System.out.println("\t\tSickLeaveHours : " + rs.getString("Employee.SickLeaveHours"));
						System.out.println("\t\tSalary : " + rs.getString("Salary.Salary") + "\n");
					}
					break;

				case 4:
					SQL = QuerySet.displayAllEmployee();
					rs = stmt.executeQuery(SQL);
					while (rs.next()) {
						System.out.println("\t\t[ EmployeeID : " + rs.getString("Employee.EmployeeID") + " ]");
						System.out.println("\t\tLoginID : " + rs.getString("Employee.LoginID"));
						System.out.println("\t\tDepartmentID : " + rs.getString("Employee.DepartmentID"));
						System.out.println("\t\tManagerID : " + rs.getString("Employee.ManagerID"));
						System.out.println("\t\tTitle : " + rs.getString("Employee.Title"));
						System.out.println("\t\tBirthDate : " + rs.getString("Employee.BirthDate"));
						System.out.println("\t\tMartialStatus : " + rs.getString("Employee.MartialStatus"));
						System.out.println("\t\tGender : " + rs.getString("Employee.Gender"));
						System.out.println("\t\tHireDate : " + rs.getString("Employee.HireDate"));
						System.out.println("\t\tVacationHours : " + rs.getString("Employee.VacationHours"));
						System.out.println("\t\tSickLeaveHours : " + rs.getString("Employee.SickLeaveHours"));
						System.out.println("\t\tSalary : " + rs.getString("Salary.Salary") + "\n");
					}
					break;

				case 5:
					String[] dataArr = new String[11];
					System.out.println("\tEnter Employee id");
					dataArr[0] = sc.next();
					System.out.println("\tEnter Login id ");
					dataArr[1] = sc.next();
					System.out.println("\tEnter Department id ");
					dataArr[2] = sc.next();
					System.out.println("\tEnter Manager id ");
					dataArr[3] = sc.next();
					System.out.println("\tEnter Title ");
					dataArr[4] = sc.next();
					System.out.println("\tEnter Birth Date(yyyy-mm-dd)");
					dataArr[5] = sc.next();
					System.out.println("\tEnter Martial Status(Y/N)");
					dataArr[6] = sc.next();
					System.out.println("\tEnter Gender(M/F)");
					dataArr[7] = sc.next();
					System.out.println("\tEnter Hiere Date(yyyy-mm-dd)");
					dataArr[8] = sc.next();
					System.out.println("\tEnter Vacation Hours(int)");
					dataArr[9] = sc.next();
					System.out.println("\tEnter Sick Leave Hours(int)");
					dataArr[10] = sc.next();

					SQL = QuerySet.addEmployee(dataArr);
					stmt.executeUpdate(SQL);
					break;
				case 6 :
					System.out.println("Good Bye");
					System.exit(1);
					break;
				default:
					System.out.println("Wrong input");
					break;
				}

			}
		} catch (SQLException ex) {
			System.out.println(ex);
		} catch (Exception ex) {
			System.out.println(ex);

		}

	}

}
