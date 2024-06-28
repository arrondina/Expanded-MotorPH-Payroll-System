/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rosh
 */
public class Request {
    
    private int requestID;
    private int employeeID;
    private String fullName;
    private Date requestDate;
    private String leaveType;
    private int leaveTypeID;
    private int days;
    private Date startDate;
    private Date endDate;
    private int statusID;
    private String status;
    private String reason;
    
    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    
    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public int getLeaveTypeID() {
        return leaveTypeID;
    }

    public void setLeaveTypeID(int leaveTypeID) {
        this.leaveTypeID = leaveTypeID;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public void fetchLeaveRequestDetails(int requestID) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_db", "root", "arron")) {
            String sql = "SELECT lr.dateRequested, lr.employeeID, e.fullName, lt.typeName, lr.days, lr.startDate, lr.endDate, lr.reason, ls.statusName " +
                         "FROM request lr " +
                         "JOIN employee e ON lr.employeeID = e.employeeID " +
                         "JOIN leave_type lt ON lr.leaveTypeID = lt.leaveTypeID " +
                         "JOIN leave_status ls ON lr.statusID = ls.statusID " +
                         "WHERE lr.requestID = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, requestID);
                ResultSet resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    this.requestID = requestID;
                    this.employeeID = resultSet.getInt("employeeID");
                    this.fullName = resultSet.getString("fullName");
                    this.requestDate = resultSet.getDate("dateRequested");
                    this.leaveType = resultSet.getString("typeName");
                    this.leaveTypeID = resultSet.getInt("leaveTypeID"); 
                    this.days = resultSet.getInt("days");
                    this.startDate = resultSet.getDate("startDate");
                    this.endDate = resultSet.getDate("endDate");
                    this.reason = resultSet.getString("reason");
                    this.status = resultSet.getString("statusName");
                    this.statusID = resultSet.getInt("statusID");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean applyForLeave(int employeeID, int leaveTypeID, int leaveDays) throws SQLException {
        // Establish a connection to the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_db", "root", "arron")) {
            
            // SQL query to fetch the leave balance for the specified leave type
            String sql = "SELECT sickLeave, vacationLeave, emergencyLeave "
                    + "FROM leave_balances "
                    + "WHERE employeeID = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeID);
                ResultSet resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    int availableDays;
                    
                    // Determine the available leave balance based on the leave type
                    switch (leaveTypeID) {
                        case 1: // Sick Leave
                            availableDays = resultSet.getInt("sickLeave");
                            break;
                        case 2: // Vacation Leave
                            availableDays = resultSet.getInt("vacationLeave");
                            break;
                        case 3: // Emergency Leave
                            availableDays = resultSet.getInt("emergencyLeave");
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid leave type ID: " + leaveTypeID);
                    }
                    
                    return leaveDays <= availableDays;
                }
            }
        } catch (SQLException ex) {
            
        }
        
        return false;
    }
    
    public void approveLeave(int requestID) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_db", "root", "arron")) {
            String sql = "UPDATE request SET statusID = ? WHERE requestID = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, 2); 
                statement.setInt(2, requestID);
                
                int rowsUpdated = statement.executeUpdate();
                
                if (rowsUpdated > 0) {
                    System.out.println("Request with ID " + requestID + " has been approved.");
                } else {
                    System.out.println("No request found with ID " + requestID);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error updating request status: " + ex.getMessage());
        }
    }
    
    public void rejectLeave(int requestID) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_db", "root", "arron")) {
            String sql = "UPDATE request SET statusID = ? WHERE requestID = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, 3); 
                statement.setInt(2, requestID);
                
                int rowsUpdated = statement.executeUpdate();
                
                if (rowsUpdated > 0) {
                    System.out.println("Request with ID " + requestID + " has been rejecteded.");
                } else {
                    System.out.println("No request found with ID " + requestID);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error updating request status: " + ex.getMessage());
        }
    }
    
}
