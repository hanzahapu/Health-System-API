/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HANSAJA
 */
public class Patient extends Person {
    private String medicalHistory;
    private String currentHealthStatus;

    
    // Constructors
    
    // Default constructor
    public Patient() {
    }

    // Parameterized constructor
    public Patient(String id, String name, String contactInfo, String address, String medicalHistory, String currentHealthStatus) {
        super(id, name, contactInfo, address);
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }

    // getters and setters
    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }
}