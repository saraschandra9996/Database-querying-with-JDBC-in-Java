/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hw1.ayyalasomayajula;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hw1Ayyalasomayajula {

    public static void main(String[] args) {
        
        try {
            //Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Utils myUtil = new Utils();
            
            //Connect to the database
            Connection conn;
            conn = DriverManager.getConnection(myUtil.getURL(), myUtil.getUSER(), myUtil.getPASSWORD());
            System.out.println("Connection Successful!");
            
            System.out.println("\nTASK2\n-------\nEmployees Table:");
            myUtil.displayEmployees(conn);

            System.out.println("\nTASK3\n-------\nEmployee Names and Department Names:");
            myUtil.displayEmployeeNamesAndDepartments(conn);

            System.out.println("\nTASK4\n-------");
            myUtil.displayHighestSalary(conn);

            System.out.println("\nTASK5\n-------\nEmployees Hired in since 2023:");
            myUtil.displayEmployeesHiredin2023(conn);

            System.out.println("\nTASK6\n-------\nUpdated Salary by ID:");
            myUtil.updateEmployeeSalary(conn, 8, 80000.00);
           
            System.out.println("\nTASK7\n-------\nUpdated Salary by Name:");
            myUtil.updateEmployeeSalaryByName(conn, "John-Doe", 85000.00);
            
            System.out.println("\nTASK8\n-------\nDelete Employees:");
            myUtil.deleteEmployeesBeforeTheDate(conn, "01/15/2004");

            // Close the connection
            conn.close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Hw1Ayyalasomayajula.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

