package cat.proven.javaio01;

import cat.proven.javaio01.model.Contact;
import cat.proven.javaio01.persist.PhoneBook;
import java.util.*;
import java.io.*;

/**
 * ContactObjectStream.java
 *
 * @author ProvenSoft
 */
public class ContactObjectStreamMain {

    private static final String FILE_NAME = "phonebook.txt";

    public static void main(String[] args) {
        PhoneBook phbk1 = new PhoneBook();
        //populate phonebook with some contacts
        phbk1.addContact(new Contact("Peter", "555111111", 25));
        phbk1.addContact(new Contact("Mary", "555222222", 33));
        phbk1.addContact(new Contact("Angie", "555333333", 27));
        phbk1.addContact(new Contact("Mark", "555444444", 42));
        phbk1.addContact(new Contact("Ann", "555555555", 29));
        //display list
        System.out.println("Initial data:");
        showPhoneBook(phbk1.getAllContacts());
        //save list to file
        int counter = phbk1.savePhoneBook(FILE_NAME);
        //int counter = phbk1.savePhoneBook2(FILE_NAME);
        System.out.format("%d elements saved to file %s\n", counter, FILE_NAME);
        //instantiate a new empty phonebook
        PhoneBook phbk2 = new PhoneBook();
        //read phonebook data from file
        counter = phbk2.readPhoneBook(FILE_NAME);
        //counter = phbk2.readPhoneBook2(FILE_NAME);
        System.out.format("%d elements restored from file %s\n", counter, FILE_NAME);
        if (counter > 0) {
            //display read list
            System.out.println("Read data:");
            showPhoneBook(phbk2.getAllContacts());
        } else {
            System.out.println("There was an error reading data:");
        }
    }

    /**
     * Prints the list of contacts passed as a parameter to the console
     *
     * @param pb List of contacts
     */
    private static void showPhoneBook(List<Contact> pb) {
        for (Contact c : pb) {
            System.out.println(c.toString());
        }
    }

}
