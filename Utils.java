/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hw1.ayyalasomayajula;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    // Task 1: Create and init JDBC URL, username, and password of MySQL server
    public String getURL() {
        return "jdbc:mysql://localhost:3306/cst467";
    }

    public String getUSER() {
        return "cst467";
    }

    public String getPASSWORD() {
        return "cst467";
    }

    // Task 2: Retrieve and display all records from the "employees" table
    public void displayEmployees(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_ID, DATE_FORMAT(hire_date, '%m/%d/%Y') AS HIRE_DATE FROM employees");
            while (rs.next()) {
                System.out.println("Employee ID: " + rs.getString("EMPLOYEE_ID") + ", First Name: " + rs.getString("FIRST_NAME") + ", Last Name: " + rs.getString("LAST_NAME") + ", Department ID: " + rs.getString("DEPARTMENT_ID") + ", Hire Date: " + rs.getString("HIRE_DATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Task 3: Display names and department names of employees
    public void displayEmployeeNamesAndDepartments(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT e.FIRST_NAME AS employee_first_name, e.LAST_NAME AS employee_last_name, d.DEPARTMENT_NAME AS department_name FROM employees e INNER JOIN departments d ON e.department_id = d.department_id");
            while (rs.next()) {
                System.out.println("Employee First Name: " + rs.getString("employee_first_name") + ", Employee Last Name: " + rs.getString("employee_last_name") + ", Department Name: " + rs.getString("department_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Task 4: Find and display the highest salary
    public void displayHighestSalary(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(SALARY) AS max_salary FROM salaries s INNER JOIN employees e ON e.EMPLOYEE_ID = s.EMPLOYEE_ID");
            if (rs.next()) {
                System.out.println("Highest Salary: " + rs.getDouble("max_salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Task 5: Retrieve employees hired since 2023
    public void displayEmployeesHiredin2023(Connection conn) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_ID, DATE_FORMAT(hire_date, '%m/%d/%Y') AS HIRE_DATE FROM employees WHERE hire_date >= ?");
            pstmt.setString(1, "2023-01-01");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Employee ID: " + rs.getString("EMPLOYEE_ID") + ", First Name: " + rs.getString("FIRST_NAME") + ", Last Name: " + rs.getString("LAST_NAME") + ", Department ID: " + rs.getString("DEPARTMENT_ID") + ", Hire Date: " + rs.getString("HIRE_DATE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Task 6: Update the salary of an employee by ID
    public void updateEmployeeSalary(Connection conn, int employeeId, double newSalary) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE salaries SET salary = ? WHERE EMPLOYEE_ID = ?");
            pstmt.setDouble(1, newSalary);
            pstmt.setInt(2, employeeId);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Task 7: Update the salary of an employee by Name
    public void updateEmployeeSalaryByName(Connection conn, String employeeFullName, double newSalary) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE salaries SET salary = ? WHERE EMPLOYEE_ID IN (SELECT EMPLOYEE_ID FROM employees WHERE CONCAT(FIRST_NAME, '-', LAST_NAME) = ?)");
            pstmt.setDouble(1, newSalary);
            pstmt.setString(2, employeeFullName);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Task 8: Delete employees hired before a date.
    public void deleteEmployeesBeforeTheDate(Connection conn, String hiredate) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employees WHERE DATE_FORMAT(hire_date, '%m/%d/%Y') < ?");
            pstmt.setString(1, hiredate);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
