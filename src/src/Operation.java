package src;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import src.Patient;

public class Operation {
    
    Connection con = null;
    Statement sta = null;
    PreparedStatement psta = null;
    
    public Operation(){
        
        String url = "jdbc:mysql://"+Database.host+":"+Database.port+"/"+Database.db_name;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,Database.id,Database.password);
            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connection failed.");
        }
    }
    
    public boolean loginForDoctors(String securityNumber, String password){
        String q = "Select * from doctor_database where securityNumber=? and password=?";
        
        try {
            psta = con.prepareStatement(q);
            psta.setString(1,securityNumber);
            psta.setString(2,password);  
            ResultSet rs = psta.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void updatePatient(String ssn,int age, int height, int weight, String gender){
        String q = "Update patient_database SET age=?, height=?, weight=?,gender=? WHERE ssn=?";
        
        try {
            psta = con.prepareStatement(q);
            psta.setInt(1,age);
            psta.setInt(2,height);
            psta.setInt(3,weight);
            psta.setString(4,gender);
            psta.setString(5,ssn);
            psta.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public boolean loginForPatients(String securityNumber, String password){
        String q = "Select * from patient_database where securityNumber=? and password=?";
        
        try {
            psta = con.prepareStatement(q);
            psta.setString(1,securityNumber);
            psta.setString(2,password);  
            ResultSet rs = psta.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void addPatient(String securityNumber,String name,String password,String surname,int age,int height,int weight,String gender){
        String q = "Insert into patient_database(securityNumber,password,name,surname,age,height,weight,gender) VALUES (?,?,?,?,?,?,?,?)";
        try {   
            psta = con.prepareStatement(q);
            psta.setString(1,securityNumber);
            psta.setString(2,password);
            psta.setString(3,name);
            psta.setString(4,surname);
            psta.setInt(5,age);
            psta.setInt(6,height);
            psta.setInt(7,weight);
            psta.setString(8,gender);
            psta.executeUpdate();
            Patient p = new Patient(securityNumber,password,name,surname,height,weight,age,gender);
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String selectPName(String ssn){
        
        String q = "Select * from patient_database";
        String result=null;

        try {
            
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery(q);
            while(rs.next()){
                if(rs.getString("securityNumber").equals(ssn)){
                    result = rs.getString("name");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String selectPSurname(String ssn){
        
        String q = "Select * from patient_database";
        String result=null;

        try {
            
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery(q);
            while(rs.next()){
                if(rs.getString("securityNumber").equals(ssn)){
                    result = rs.getString("surname");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String selectPHeight(String ssn){
        
        String q = "Select * from patient_database";
        String result=null;

        try {
            
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery(q);
            while(rs.next()){
                if(rs.getString("securityNumber").equals(ssn)){
                    result = rs.getString("height");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String selectPWeight(String ssn){
        
        String q = "Select * from patient_database";
        String result=null;

        try {
            
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery(q);
            while(rs.next()){
                if(rs.getString("securityNumber").equals(ssn)){
                    result = rs.getString("weight");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String selectPGender(String ssn){
        
        String q = "Select * from patient_database";
        String result=null;

        try {
            
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery(q);
            while(rs.next()){
                if(rs.getString("securityNumber").equals(ssn)){
                    result = rs.getString("gender");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public String selectPAge(String ssn){
        
        String q = "Select * from patient_database";
        String result=null;

        try {
            
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery(q);
            while(rs.next()){
                if(rs.getString("securityNumber").equals(ssn)){
                    result = rs.getString("age");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static void main(String[] args){
        Operation op = new Operation();
    }
}

