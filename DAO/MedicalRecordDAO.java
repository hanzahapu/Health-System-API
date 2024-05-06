/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 * Data Access Object (DAO) class for handling MedicalRecord objects.
 * 
 * @author HANSAJA
 */
import Model.MedicalRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicalRecordDAO {
    
    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(MedicalRecordDAO.class.getName());

    // Static list to hold MedicalRecord objects
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();
    
    // Instance of PatientDAO for fetching patients
    private static PatientDAO patientDAO = new PatientDAO();

    // Static block to initialize some sample MedicalRecord objects
    static {
        medicalRecords.add(new MedicalRecord("M1", patientDAO.getPatientById("P2"), "fever", "paracetamol"));
        medicalRecords.add(new MedicalRecord("M2", patientDAO.getPatientById("P1"), "Stomach ache", "leperamide"));
    }

    // Method to retrieve all medical records
    public List<MedicalRecord> getAllMedicalRecords() {
        logger.log(Level.INFO, "Fetching all medical records");
        return medicalRecords;
    }

    // Method to retrieve a medical record by ID
    public MedicalRecord getMedicalRecordById(String id) {
        logger.log(Level.INFO, "Fetching medical record by ID: " + id);
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getId().equals(id)) {
                logger.log(Level.INFO, "Medical record found with ID: " + id);
                return medicalRecord;
            }
        }
        logger.log(Level.WARNING, "Medical record not found with ID: " + id);
        return null;
    }

    // Method to add a new medical record
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        logger.log(Level.INFO, "Adding new medical record: " + medicalRecord);
        medicalRecords.add(medicalRecord);
    }

    // Method to update an existing medical record
    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        logger.log(Level.INFO, "Updating medical record with ID: " + updatedMedicalRecord.getId());
        for (int i = 0; i < medicalRecords.size(); i++) {
            MedicalRecord medicalRecord = medicalRecords.get(i);
            if (medicalRecord.getId().equals(updatedMedicalRecord.getId())) {
                medicalRecords.set(i, updatedMedicalRecord);
                logger.log(Level.INFO, "Medical record updated: " + updatedMedicalRecord);
                return;
            }
        }
        logger.log(Level.WARNING, "Medical record not found with ID: " + updatedMedicalRecord.getId() + ", update failed");
    }

    // Method to delete a medical record by ID
    public void deleteMedicalRecord(String id) {
        logger.log(Level.INFO, "Deleting medical record with ID: " + id);
        medicalRecords.removeIf(medicalRecord -> medicalRecord.getId().equals(id));
    }
}

