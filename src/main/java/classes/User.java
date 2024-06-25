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
public class User extends Employee {
    private String username;
    private String password;
    private String role;
    private boolean isLoggedIn;
    
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isLoggedIn = false;
    }
    
    // Getter and Setter    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public boolean login(String enteredUsername, String enteredPassword) {
        if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
            isLoggedIn = true;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean authenticate(String enteredUsername, String enteredPassword) {
        return username.equals(enteredUsername) && password.equals(enteredPassword);
    }
    
    public boolean validateRole(String enteredRole) {
        return role.equalsIgnoreCase(enteredRole);
    }
    
    public static User loadFromDatabase(String enteredUsername, String enteredPassword) throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/aoop_db";
        String dbUsername = "root";
        String dbPassword = "arron";
        
        String sql = "SELECT username, password, roleName FROM employee_credentials WHERE username = ? AND password = ?";
        
        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, enteredUsername);
            statement.setString(2, enteredPassword);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String username = resultSet.getString("userName");
                String password = resultSet.getString("password");
                String role = resultSet.getString("roleName");
                return new User(username, password, role);
            } else {
                return null;
            }
        }
    }
}