/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 * Data Access Object (DAO) class for handling Prescription objects.
 * 
 * @author HANSAJA
 */
import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrescriptionDAO {
    
    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(PrescriptionDAO.class.getName());

    // Static list to hold Prescription objects
    private static List<Prescription> prescriptions = new ArrayList<>();
    
    // Instance of PatientDAO for fetching patients
    private static PatientDAO patientDAO = new PatientDAO();

    // Static block to initialize some sample Prescription objects
    static {
        prescriptions.add(new Prescription("Pres 1", patientDAO.getPatientById("P1"), "dexamethasol", "12 per week", "2 tablets per day", 06));
        prescriptions.add(new Prescription("Pres 2", patientDAO.getPatientById("P2"), "Astheline Inheiler", "twice per day", "use proper inhail", 12));
    }

    // Method to retrieve all prescriptions
    public List<Prescription> getAllPrescriptions() {
        logger.log(Level.INFO, "Fetching all prescriptions");
        return prescriptions;
    }

    // Method to retrieve a prescription by ID
    public Prescription getPrescriptionById(String id) {
        logger.log(Level.INFO, "Fetching prescription by ID: " + id);
        for (Prescription prescription : prescriptions) {
            if (prescription.getId().equals(id)) {
                logger.log(Level.INFO, "Prescription found with ID: " + id);
                return prescription;
            }
        }
        logger.log(Level.WARNING, "Prescription not found with ID: " + id);
        return null;
    }

    // Method to add a new prescription
    public void addPrescription(Prescription prescription) {
        logger.log(Level.INFO, "Adding new prescription: " + prescription);
        prescriptions.add(prescription);
    }

    // Method to update an existing prescription
    public void updatePrescription(Prescription updatedPrescription) {
        logger.log(Level.INFO, "Updating prescription with ID: " + updatedPrescription.getId());
        for (int i = 0; i < prescriptions.size(); i++) {
            Prescription prescription = prescriptions.get(i);
            if (prescription.getId().equals(updatedPrescription.getId())) {
                prescriptions.set(i, updatedPrescription);
                logger.log(Level.INFO, "Prescription updated: " + updatedPrescription);
                return;
            }
        }
        logger.log(Level.WARNING, "Prescription not found with ID: " + updatedPrescription.getId() + ", update failed");
    }

    // Method to delete a prescription by ID
    public void deletePrescription(String id) {
        logger.log(Level.INFO, "Deleting prescription with ID: " + id);
        prescriptions.removeIf(prescription -> prescription.getId().equals(id));
    }
}

