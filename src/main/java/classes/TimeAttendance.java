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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rosh
 */
public class TimeAttendance {

    private int attendanceID;
    private LocalDate startDate;
    private LocalDate endDate;
    private Date time;
    private LocalTime timeIn;
    private LocalTime timeOut;
    
    // Getters and Setters
    public int getAttendanceID() {
        return attendanceID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    
    public Date getTime() {
        return time;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }
    
    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }
    
    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }
    
    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }
    
    // Methods
    
    private Date parseDateTime(String dateStr, String timeStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        return dateFormat.parse(dateStr + " " + timeStr);
    }
    
    private boolean isWithinRange(Date date, Date startDate, Date endDate) {
        return !date.before(startDate) && !date.after(endDate);
    }
    
    public boolean isClockedIn(int employeeID) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_db", "root", "arron")) {
            LocalDateTime currentTime = LocalDateTime.now();
            String dateString = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            String sql = "SELECT COUNT(*) "
                    + "FROM time_attendance "
                    + "WHERE employeeID = ? AND DATE(timeIn) = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeID);
                statement.setString(2, dateString);
                ResultSet resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Employee is clocked in if count is greater than 0
                }
            }
        } catch (SQLException ex) {
            
        }
        
        return false; // Return false if an error occurs or no record found
    }
    
    public void clockIn(int employeeID) throws SQLException {
        LocalDateTime currentTime = LocalDateTime.now();
        String date = currentTime.toLocalDate().toString(); // yyyy-MM-dd
        String timeIn = currentTime.toLocalTime().toString(); // HH:mm:ss
        recordClockIn(employeeID, date, timeIn);
    }
    
    private boolean recordClockIn(int employeeID, String date, String timeIn) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_db", "root", "arron")) {
            // Check if there is already a clock-in record
            String checkSql = "SELECT COUNT(*) FROM time_attendance WHERE employeeID = ? AND DATE(timeIn) = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setInt(1, employeeID);
                checkStatement.setString(2, date);
                ResultSet resultSet = checkStatement.executeQuery();
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    // Already clocked in today, do not insert again
                    return false;
                }
            }
            
            String sql = "INSERT INTO time_attendance (employeeID, timeIn) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeID);
                statement.setString(2, date + " " + timeIn); // Combine date and time for datetime column
                
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0; // Return true if a row was inserted/updated
            }
        }
    }
    
    public void clockOut(int employeeID) throws SQLException {
        LocalDateTime currentTime = LocalDateTime.now();
        String date = currentTime.toLocalDate().toString(); // yyyy-MM-dd
        String timeOut = currentTime.toLocalTime().toString(); // HH:mm:ss
        recordClockOut(employeeID, date, timeOut);
    }
    
    private boolean recordClockOut(int employeeID, String date, String timeOut) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_db", "root", "arron")) {
            String sql = "UPDATE time_attendance SET timeOut = ? WHERE employeeID = ? AND DATE(timeIn) = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, date + " " + timeOut); // Combine date and time for datetime column
                statement.setInt(2, employeeID);
                statement.setString(3, date);
                
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0; // Return true if a row was inserted/updated
            }
        }
    }
    
    public DefaultTableModel fetchEmployeeAttendance(String employeeID) {
        DefaultTableModel attendance = new DefaultTableModel(new String[]{"Employee ID", "Date", "Time In", "Time Out"}, 0);
        
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aoop_db", "root", "arron")) {
            String sql = "SELECT employeeID, date, timeIn, timeOut "
                    + "FROM attendance_record "
                    + "WHERE employeeID = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, employeeID);
                ResultSet resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    String[] record = new String[4];
                    record[0] = resultSet.getString("employeeID");
                    record[1] = resultSet.getString("date");
                    record[2] = resultSet.getString("timeIn");
                    record[3] = resultSet.getString("timeOut");
                    attendance.addRow(record);
                }
            }
        } catch (SQLException ex) {
            
        }
        
        return attendance;
    }
   
    public void generateAttendanceReport() {
        
    }
    
    public void calculateMonthlyAttendance() {
        
    }
}
