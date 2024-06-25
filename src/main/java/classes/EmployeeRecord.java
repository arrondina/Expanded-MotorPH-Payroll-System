/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rosh
 */
public class EmployeeRecord extends Employee {
    
    public void createEmployeeFile(String CSVFileName) throws IOException {
        
    }
    
    public void createLeaveFile(String dateRequested, String employeeID, String lastName, String firstName, String leaveType, String days, String start, String end, String remainSL, String remainVL, String remainEL, String reason) throws IOException {
        
    }
    
    public void createPayrollFile(String filename, String startDate, String endDate, String employeeID, double monthlyRate, double dailyRate, float daysWorked, double overTime, double grossIncome, double riceSubsidy, double phoneAllowance, double totalBenefits, double sss, double philhealth, double pagibig, double tax, double totalDeductions, double takeHomePay) throws IOException {
        
    }
    
    private boolean fileIsEmpty(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine() == null;
        } catch (IOException e) {
            return true; 
        }
    }
    
    public boolean isRecordExists(String file, String startDate, String endDate, String employeeID) throws IOException {
        
        return false;
        
    }
    
    private String formatNumber(double number) {
        // Format the number with two decimal places
        DecimalFormat df = new DecimalFormat("###0.00");
        return df.format(number);
    }
    
    public DefaultTableModel fetchEmployees(String CSVFilename) throws IOException {
        
        return null;
        
    }
    
    public DefaultTableModel fetchAttendance(String CSVFilename) throws IOException {
        
        return null;
        
    }
    
    public DefaultTableModel fetchEmployeeAttendance(String CSVFilename, String employeeID) throws IOException {
        
        return null;
        
    }
    
    public DefaultTableModel fetchPayroll(String CSVFilename) throws IOException {
        
        return null;
        
    }
    
    public DefaultTableModel fetchPayslipReport(String CSVFilename) throws IOException {
        
        return null;
        
    }
    
    public DefaultTableModel fetchLeave (String CSVFilename) throws IOException {
        
        return null;
        
    }
    
    public DefaultTableModel fetchRequest (String CSVFilename) throws IOException {
        
        return null;
        
    }
    
    public DefaultTableModel fetchCredit (String CSVFilename) throws IOException {
        
        return null;
        
    }
    
    public String[] fetchDetail(String CSVFilename) throws FileNotFoundException, IOException {
        
        return null;
        
    }
    
    public String[] fetchPayslip(String CSVFilename, String selectedStartDate, String selectedEndDate, int employeeID) throws FileNotFoundException, IOException, ParseException {
        
        return null;
        
    }
    
    public String[] loadLeaveBalances(String csvFilename, int employeeID) throws IOException {
        
        return null;
        
    }
    
}
