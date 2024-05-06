/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resource;

/**
 * Resource class for handling Prescription objects.
 * 
 * @author HANSAJA
 */

import DAO.PrescriptionDAO;
import Model.Prescription;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



@Path("/prescriptions")
public class PrescriptionResource {

    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(PrescriptionResource.class.getName());
    
    // Instance of PrescriptionDAO for handling prescription data
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    
    // Endpoint to fetch all prescriptions
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Prescription> getAllPrescriptions() {
        logger.log(Level.INFO, "Fetching all prescriptions");
        try {
            return prescriptionDAO.getAllPrescriptions();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch prescriptions: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch prescriptions", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    // Endpoint to fetch a specific prescription by ID
    @GET
    @Path("/{prescriptionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Prescription getPrescriptionById(@PathParam("prescriptionId") String prescriptionId) {
        logger.log(Level.INFO, "Fetching prescription by ID: " + prescriptionId);
        try {
            Prescription prescription = prescriptionDAO.getPrescriptionById(prescriptionId);
            if (prescription != null) {
                return prescription;
            } else {
                logger.log(Level.WARNING, "Prescription not found with ID: " + prescriptionId);
                throw new WebApplicationException("Prescription not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch prescription with ID: " + prescriptionId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch prescription", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    // Endpoint to add a new prescription
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        logger.log(Level.INFO, "Adding new prescription: " + prescription);
        try {
            prescriptionDAO.addPrescription(prescription);
            return Response.status(Response.Status.CREATED).entity("Prescription added successfully").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add prescription: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to add prescription", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    // Endpoint to update an existing prescription
    @PUT
    @Path("/{prescriptionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("prescriptionId") String prescriptionId, Prescription updatedPrescription) {
        logger.log(Level.INFO, "Updating prescription with ID: " + prescriptionId);
        try {
            Prescription existingPrescription = prescriptionDAO.getPrescriptionById(prescriptionId);
            if (existingPrescription != null) {
                updatedPrescription.setId(prescriptionId);
                prescriptionDAO.updatePrescription(updatedPrescription);
                return Response.ok().entity("Prescription updated successfully").build();
            } else {
                logger.log(Level.WARNING, "Prescription not found with ID: " + prescriptionId);
                throw new WebApplicationException("Prescription not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update prescription with ID: " + prescriptionId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to update prescription", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Endpoint to delete a prescription by ID
    @DELETE
    @Path("/{prescriptionId}")
    public Response deletePrescription(@PathParam("prescriptionId") String prescriptionId) {
        logger.log(Level.INFO, "Deleting prescription with ID: " + prescriptionId);
        try {
            prescriptionDAO.deletePrescription(prescriptionId);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete prescription with ID: " + prescriptionId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to delete prescription", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
