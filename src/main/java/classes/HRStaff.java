/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Rosh
 */
public class HRStaff extends Employee { 
    
    private int staffID;
    private String department;
    
    public int getStaffID() {
        return staffID;
    }
    
    public String getDepartment() {
        return department;
    }    
    
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public void addEntries() {
        
    }

    public void updateEntries() {
        
    }

    public void deleteEntries() {
        
    }
}