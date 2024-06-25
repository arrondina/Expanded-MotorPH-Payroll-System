/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rosh
 */
public class Employee {
    private int employeeID;
    private int departmentID;
    private String address;
    private String position;
    private String lastName;
    private String firstName;
    private String birthday;
    private String phoneNumber;
    private String immediateSupervisor;
    private String employeeStatus;
    private String sssNumber;
    private String philhealthNumber;
    private String tinNumber;
    private String pagibigNumber;
    
    // Getters and Setters
    public int getEmployeeID() {
        return employeeID;
    }
    public String getAddress() {
	return address;
    }
    public int getDepartmentID() {
	return departmentID;
    }
    public String getPosition() {
	return position;
    }
    public String getLastName() {
	return lastName;
    }
    public String getFirstName() {
	return firstName;
    }
    public String getFullName() {
        return firstName + "" + lastName;
    }
    public String getBirthday() {
	return birthday;
    }
    public String getPhoneNumber() {
	return phoneNumber;
    }
    public String getSupervisor() {
	return immediateSupervisor;
    }
    public String getStatus() {
	return employeeStatus;
    }
    public String getSSS() {
	return sssNumber;
    }
    public String getPhilhealth() {
	return philhealthNumber;
    }
    public String getTIN() {
	return tinNumber;
    }
    public String getPagibig() {
	return pagibigNumber;
    }
    
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }
    public void setImmediateSupervisor(String immediateSupervisor) {
	this.immediateSupervisor = immediateSupervisor;
    }  
    public void setStatus(String employeeStatus) {
	this.employeeStatus = employeeStatus;
    }
    public void setSSS(String sssNumber) {
	this.sssNumber = sssNumber;
    }   
    public void setPhilhealth(String philhealthNumber) {
	this.philhealthNumber = philhealthNumber;
    } 
    public void setTIN(String tinNumber) {
	this.tinNumber = tinNumber;
    }   
    public void setPagibig(String pagibigNumber) {
	this.pagibigNumber = pagibigNumber;
    }
    
    // Load details from the database based on username
    public static Employee loadDetails(String username) {
        String jdbcURL = "jdbc:mysql://localhost:3306/aoop_db";
        String dbUsername = "root";
        String dbPassword = "arron";

        String sql = "SELECT employeeID, username, lastName, firstName, birthday, address, phoneNumber, immediateSupervisor, status, positionName, sssNumber, philHealthNumber, tinNumber, pagibigNumber "
               + "FROM employee_details "
               + "WHERE username = ?";
        
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            // Set the username parameter
            statement.setString(1, username);
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                // Create and populate an Employee object
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("employeeID"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setBirthday(resultSet.getString("birthday"));
                employee.setAddress(resultSet.getString("address"));
                employee.setPhoneNumber(resultSet.getString("phoneNumber"));
                employee.setImmediateSupervisor(resultSet.getString("immediateSupervisor"));
                employee.setStatus(resultSet.getString("status"));
                employee.setPosition(resultSet.getString("positionName"));
                employee.setSSS(resultSet.getString("sssNumber"));
                employee.setPhilhealth(resultSet.getString("philHealthNumber"));
                employee.setTIN(resultSet.getString("tinNumber"));
                employee.setPagibig(resultSet.getString("pagibigNumber"));

                return employee;
        } else {
                System.out.println("Employee details not found for username: " + username);
                return null;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while fetching employee details from the database.");
            return null;
        }
    }   
}
