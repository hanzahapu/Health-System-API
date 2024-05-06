/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resource;

/**
 * Resource class for handling Appointment objects.
 * 
 * @author HANSAJA
 */

import DAO.AppointmentDAO;
import Model.Appointment;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



@Path("/appointments")
public class AppointmentResource {

    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(AppointmentResource.class.getName());
    
    // Instance of AppointmentDAO for handling appointment data
    private AppointmentDAO appointmentDAO = new AppointmentDAO();

    
    // Endpoint to fetch all appointments
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Appointment> getAllAppointments() {
        logger.log(Level.INFO, "Fetching all appointments");
        try {
            return appointmentDAO.getAllAppointments();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch appointments: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch appointments", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    // Endpoint to fetch a specific appointment by ID
    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Appointment getAppointmentById(@PathParam("appointmentId") String appointmentId) {
        logger.log(Level.INFO, "Fetching appointment by ID: " + appointmentId);
        try {
            Appointment appointment = appointmentDAO.getAppointmentById(appointmentId);
            if (appointment != null) {
                return appointment;
            } else {
                logger.log(Level.WARNING, "Appointment not found with ID: " + appointmentId);
                throw new WebApplicationException("Appointment not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch appointment with ID: " + appointmentId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch appointment", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    // Endpoint to add a new appointment
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        logger.log(Level.INFO, "Adding new appointment: " + appointment);
        try {
            appointmentDAO.addAppointment(appointment);
            return Response.status(Response.Status.CREATED).entity("Appointment added successfully").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add appointment: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to add appointment", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    // Endpoint to update an existing appointment
    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("appointmentId") String appointmentId, Appointment updatedAppointment) {
        logger.log(Level.INFO, "Updating appointment with ID: " + appointmentId);
        try {
            Appointment existingAppointment = appointmentDAO.getAppointmentById(appointmentId);
            if (existingAppointment != null) {
                updatedAppointment.setId(appointmentId);
                appointmentDAO.updateAppointment(updatedAppointment);
                return Response.ok().entity("Appointment updated successfully").build();
            } else {
                logger.log(Level.WARNING, "Appointment not found with ID: " + appointmentId);
                throw new WebApplicationException("Appointment not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update appointment with ID: " + appointmentId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to update appointment", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    // Endpoint to delete an appointment by ID
    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") String appointmentId) {
        logger.log(Level.INFO, "Deleting appointment with ID: " + appointmentId);
        try {
            appointmentDAO.deleteAppointment(appointmentId);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete appointment with ID: " + appointmentId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to delete appointment", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}

