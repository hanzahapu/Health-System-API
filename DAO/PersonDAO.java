/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 * Data Access Object (DAO) class for handling Person objects.
 * 
 * @author HANSAJA
 */
import Model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonDAO {
    
    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(PersonDAO.class.getName());

    // Static list to hold Person objects
    private static List<Person> persons = new ArrayList<>();

    // Static block to initialize some sample Person objects
    static {
        persons.add(new Person("1", "Leo Messi", "101010101", "123 Barcelona, Spain"));
        persons.add(new Person("2", "Luis Suarez", "987654321", "456 lake st, Uruguay"));
        
    }

    // Method to retrieve all persons
    public List<Person> getAllPersons() {
        logger.log(Level.INFO, "Fetching all persons");
        return persons;
    }

    
    // Method to retrieve a person by ID
    public Person getPersonById(String id) {
        logger.log(Level.INFO, "Fetching person by ID: " + id);
        for (Person person : persons) {
            if (person.getId().equals(id)) {
                logger.log(Level.INFO, "Person found with ID: " + id);
                return person;
            }
        }
        logger.log(Level.WARNING, "Person not found with ID: " + id);
        return null;
    }

    
    // Method to add a new person
    public void addPerson(Person person) {
        logger.log(Level.INFO, "Adding new person: " + person);
        persons.add(person);
    }

    
    // Method to update an existing person
    public void updatePerson(Person updatedPerson) {
        logger.log(Level.INFO, "Updating person with ID: " + updatedPerson.getId());
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getId().equals(updatedPerson.getId())) {
                logger.log(Level.INFO, "Person found with ID: " + updatedPerson.getId() + ", updating...");
                persons.set(i, updatedPerson);
                logger.log(Level.INFO, "Person updated: " + updatedPerson);
                return;
            }
        }
        logger.log(Level.WARNING, "Person not found with ID: " + updatedPerson.getId() + ", update failed");
    }

    
    // Method to delete a person by ID
    public void deletePerson(String id) {
        logger.log(Level.INFO, "Deleting person with ID: " + id);
        persons.removeIf(person -> person.getId().equals(id));
    }
}

