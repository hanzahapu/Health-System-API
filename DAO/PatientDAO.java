/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 * Data Access Object (DAO) class for handling Patient objects.
 * 
 * @author HANSAJA
 */
import Model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PatientDAO {
    
    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(PatientDAO.class.getName());

    // Static list to hold Patient objects
    private static List<Patient> patients = new ArrayList<>();

    // Static block to initialize some sample Patient objects
    static {
        patients.add(new Patient("P1", "Kylian Mbappe", "777777777", "123 Paris, France", "Heart disease", "Stable"));
        patients.add(new Patient("P2", "Erling Haaland", "999999999", "456 Manchester, UK", "Asthma", "Improving"));
        
    }

    // Method to retrieve all patients
    public List<Patient> getAllPatients() {
        logger.log(Level.INFO, "Fetching all patients");
        return patients;
    }

    // Method to retrieve a patient by ID
    public Patient getPatientById(String id) {
        logger.log(Level.INFO, "Fetching patient by ID: " + id);
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                logger.log(Level.INFO, "Patient found with ID: " + id);
                return patient;
            }
        }
        logger.log(Level.WARNING, "Patient not found with ID: " + id);
        return null;
    }

    // Method to add a new patient
    public void addPatient(Patient patient) {
        logger.log(Level.INFO, "Adding new patient: " + patient);
        patients.add(patient);
    }

    // Method to update an existing patient
    public void updatePatient(Patient updatedPatient) {
        logger.log(Level.INFO, "Updating patient with ID: " + updatedPatient.getId());
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            if (patient.getId().equals(updatedPatient.getId())) {
                logger.log(Level.INFO, "Patient found with ID: " + updatedPatient.getId() + ", updating...");
                patients.set(i, updatedPatient);
                logger.log(Level.INFO, "Patient updated: " + updatedPatient);
                return;
            }
        }
        logger.log(Level.WARNING, "Patient not found with ID: " + updatedPatient.getId() + ", update failed");
    }

    // Method to delete a patient by ID
    public void deletePatient(String id) {
        logger.log(Level.INFO, "Deleting patient with ID: " + id);
        patients.removeIf(patient -> patient.getId().equals(id));
    }
}