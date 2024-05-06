/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 * Data Access Object (DAO) class for handling Doctor objects.
 * 
 * @author HANSAJA
 */

import Model.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDAO {
    
    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(DoctorDAO.class.getName());
    
    // Static list to hold Doctor objects
    private static List<Doctor> doctors = new ArrayList<>();

    // Static block to initialize some sample Doctor objects
    static {
        doctors.add(new Doctor("Doc 1", "Paris Smith", "456321478", "Rosario Santa Fe, Argentina ", "Cardiology"));
        doctors.add(new Doctor("Doc 2", "JP Morgan", "745816366", "Downing Street, London", "Pediatrics"));
        
        
    }

    // Method to retrieve all doctors
    public List<Doctor> getAllDoctors() {
        logger.log(Level.INFO, "Fetching all doctors");
        return doctors;
    }

    // Method to retrieve a doctor by ID
    public Doctor getDoctorById(String id) {
        logger.log(Level.INFO, "Fetching doctor by ID: " + id);
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                logger.log(Level.INFO, "Doctor found with ID: " + id);
                return doctor;
            }
        }
        logger.log(Level.WARNING, "Doctor not found with ID: " + id);
        return null;
    }

    // Method to add a new doctor
    public void addDoctor(Doctor doctor) {
        logger.log(Level.INFO, "Adding new doctor: " + doctor);
        doctors.add(doctor);
    }

    // Method to update an existing doctor
    public void updateDoctor(Doctor updatedDoctor) {
        logger.log(Level.INFO, "Updating doctor with ID: " + updatedDoctor.getId());
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            if (doctor.getId().equals(updatedDoctor.getId())) {
                logger.log(Level.INFO, "Doctor found with ID: " + updatedDoctor.getId() + ", updating...");
                doctors.set(i, updatedDoctor);
                logger.log(Level.INFO, "Doctor updated: " + updatedDoctor);
                return;
            }
        }
        logger.log(Level.WARNING, "Doctor not found with ID: " + updatedDoctor.getId() + ", update failed");
    }

    // Method to delete a doctor by ID
    public void deleteDoctor(String id) {
        logger.log(Level.INFO, "Deleting doctor with ID: " + id);
        doctors.removeIf(doctor -> doctor.getId().equals(id));
    }
}

