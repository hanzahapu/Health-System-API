/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Resource;

/**
 * Resource class for handling MedicalRecord objects.
 * 
 * @author HANSAJA
 */

import DAO.MedicalRecordDAO;
import Model.MedicalRecord;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Path("/medicalrecords")
public class MedicalRecordResource {

    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(MedicalRecordResource.class.getName());
    
    // Instance of MedicalRecordDAO for handling medical record data
    private MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();

    
    // Endpoint to fetch all medical records
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicalRecord> getAllMedicalRecords() {
        logger.log(Level.INFO, "Fetching all medical records");
        try {
            return medicalRecordDAO.getAllMedicalRecords();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch medical records: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch medical records", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    // Endpoint to fetch a specific medical record by ID 
    @GET
    @Path("/{medicalRecordId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MedicalRecord getMedicalRecordById(@PathParam("medicalRecordId") String medicalRecordId) {
        logger.log(Level.INFO, "Fetching medical record by ID: " + medicalRecordId);
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecordById(medicalRecordId);
            if (medicalRecord != null) {
                return medicalRecord;
            } else {
                logger.log(Level.WARNING, "Medical record not found with ID: " + medicalRecordId);
                throw new WebApplicationException("Medical record not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fetch medical record with ID: " + medicalRecordId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to fetch medical record", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    // Endpoint to add a new medical record
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        logger.log(Level.INFO, "Adding new medical record: " + medicalRecord);
        try {
            medicalRecordDAO.addMedicalRecord(medicalRecord);
            return Response.status(Response.Status.CREATED).entity("Medical record added successfully").build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to add medical record: " + e.getMessage(), e);
            throw new WebApplicationException("Failed to add medical record", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    // Endpoint to update an existing medical record
    @PUT
    @Path("/{medicalRecordId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("medicalRecordId") String medicalRecordId, MedicalRecord updatedMedicalRecord) {
        logger.log(Level.INFO, "Updating medical record with ID: " + medicalRecordId);
        try {
            MedicalRecord existingMedicalRecord = medicalRecordDAO.getMedicalRecordById(medicalRecordId);
            if (existingMedicalRecord != null) {
                updatedMedicalRecord.setId(medicalRecordId);
                medicalRecordDAO.updateMedicalRecord(updatedMedicalRecord);
                return Response.ok().entity("Medical record updated successfully").build();
            } else {
                logger.log(Level.WARNING, "Medical record not found with ID: " + medicalRecordId);
                throw new WebApplicationException("Medical record not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update medical record with ID: " + medicalRecordId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to update medical record", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    
    // Endpoint to delete a medical record by ID
    @DELETE
    @Path("/{medicalRecordId}")
    public Response deleteMedicalRecord(@PathParam("medicalRecordId") String medicalRecordId) {
        logger.log(Level.INFO, "Deleting medical record with ID: " + medicalRecordId);
        try {
            medicalRecordDAO.deleteMedicalRecord(medicalRecordId);
            return Response.noContent().build();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to delete medical record with ID: " + medicalRecordId + ", " + e.getMessage(), e);
            throw new WebApplicationException("Failed to delete medical record", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
