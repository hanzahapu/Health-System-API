/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 * Data Access Object (DAO) class for handling Billing objects.
 * 
 * @author HANSAJA
 */

import Model.Billing;
import Model.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillingDAO {
    
    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(BillingDAO.class.getName());

    // Static list to hold Billing objects
    private static List<Billing> billings = new ArrayList<>();
    
    // Instance of PatientDAO for fetching patients
    private static PatientDAO patientDAO = new PatientDAO();

    // Static block to initialize some sample Billing objects
    static {
        billings.add(new Billing("B1", patientDAO.getPatientById("P1"), 45500, 45550, "2021.12.21"));
        billings.add(new Billing("B2", patientDAO.getPatientById("P2"), 63650, 75000, "2020.04.24"));
    }

    
    // Method to retrieve all billings
    public List<Billing> getAllBillings() {
        logger.log(Level.INFO, "Fetching all billings");
        return billings;
    }

    // Method to retrieve a billing by ID
    public Billing getBillingById(String id) {
        logger.log(Level.INFO, "Fetching billing by ID: " + id);
        for (Billing billing : billings) {
            if (billing.getId().equals(id)) {
                logger.log(Level.INFO, "Billing found with ID: " + id);
                return billing;
            }
        }
        logger.log(Level.WARNING, "Billing not found with ID: " + id);
        return null;
    }

    // Method to add a new billing
    public void addBilling(Billing billing) {
        logger.log(Level.INFO, "Adding new billing: " + billing);
        billings.add(billing);
    }

    // Method to update an existing billing
    public void updateBilling(Billing updatedBilling) {
        logger.log(Level.INFO, "Updating billing with ID: " + updatedBilling.getId());
        for (int i = 0; i < billings.size(); i++) {
            Billing billing = billings.get(i);
            if (billing.getId().equals(updatedBilling.getId())) {
                billings.set(i, updatedBilling);
                logger.log(Level.INFO, "Billing updated: " + updatedBilling);
                return;
            }
        }
        logger.log(Level.WARNING, "Billing not found with ID: " + updatedBilling.getId() + ", update failed");
    }

    // Method to delete a billing by ID
    public void deleteBilling(String id) {
        logger.log(Level.INFO, "Deleting billing with ID: " + id);
        billings.removeIf(billing -> billing.getId().equals(id));
    }
}
