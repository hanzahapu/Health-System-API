/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 * Data Access Object (DAO) class for handling Appointment objects.
 * 
 * @author HANSAJA
 */

import Model.Appointment;
import Model.Patient;
import Model.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppointmentDAO {
    
    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(AppointmentDAO.class.getName());

    // Static list to hold Appointment objects
    private static List<Appointment> appointments = new ArrayList<>();
    
    // Instances of PatientDAO and DoctorDAO for fetching patients and doctors
    private static PatientDAO patientDAO = new PatientDAO();
    private static DoctorDAO doctorDAO = new DoctorDAO();

    
    // Static block to initialize some sample Appointment objects
    static {
        appointments.add(new Appointment("APP 1", "2024/02/16", "10.00 A.M", patientDAO.getPatientById("P1"), doctorDAO.getDoctorById("Doc 2")));
    }
    

    // Method to retrieve all appointments
    public List<Appointment> getAllAppointments() {
        logger.log(Level.INFO, "Fetching all appointments");
        return appointments;
    }
    

    // Method to retrieve an appointment by ID
    public Appointment getAppointmentById(String id) {
        logger.log(Level.INFO, "Fetching appointment by ID: " + id);
        for (Appointment appointment : appointments) {
            if (appointment.getId().equals(id)) {
                logger.log(Level.INFO, "Appointment found with ID: " + id);
                return appointment;
            }
        }
        logger.log(Level.WARNING, "Appointment not found with ID: " + id);
        return null;
    }
    

    // Method to add a new appointment
    public void addAppointment(Appointment appointment) {
        logger.log(Level.INFO, "Adding new appointment: " + appointment);
        appointments.add(appointment);
    }

    
    // Method to update an existing appointment
    public void updateAppointment(Appointment updatedAppointment) {
        logger.log(Level.INFO, "Updating appointment with ID: " + updatedAppointment.getId());
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getId().equals(updatedAppointment.getId())) {
                logger.log(Level.INFO, "Appointment found with ID: " + updatedAppointment.getId() + ", updating...");
                appointments.set(i, updatedAppointment);
                logger.log(Level.INFO, "Appointment updated: " + updatedAppointment);
                return;
            }
        }
        logger.log(Level.WARNING, "Appointment not found with ID: " + updatedAppointment.getId() + ", update failed");
    }

    
    // Method to delete an appointment by ID
    public void deleteAppointment(String id) {
        logger.log(Level.INFO, "Deleting appointment with ID: " + id);
        appointments.removeIf(appointment -> appointment.getId().equals(id));
    }
}
