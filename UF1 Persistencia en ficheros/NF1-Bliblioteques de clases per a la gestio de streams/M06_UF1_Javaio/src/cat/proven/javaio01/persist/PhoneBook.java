package cat.proven.javaio01.persist;

import cat.proven.javaio01.model.Contact;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose
 */
public class PhoneBook {

    private static List<Contact> contacts;

    public PhoneBook() {
        contacts = new ArrayList<>();
    }

    /**
     * adds a contact to the phonebook
     *
     * @param contact the contact to add
     * @return true if added, false otherwise
     */
    public boolean addContact(Contact contact) {
        return contacts.add(contact);
    }

    /**
     * gets all contacts in phonebook
     *
     * @return list of contacts
     */
    public List<Contact> getAllContacts() {
        return contacts;
    }

    /**
     * Stores the phonebook to a file writing contact by contact
     *
     * @param filename the name of the file
     * @return the number of elements saved
     */
    public int savePhoneBook(String filename) {
        int counter = 0;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Contact c : contacts) {
                oos.writeObject(c);
                counter++;
            }
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return counter;
    }

    /**
     * Reads a list of contacts from a file reading contact by contact
     *
     * @return number of elements read
     */
    public int readPhoneBook(String filename) {
        int counter = 0;
        List<Contact> data = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                if (obj instanceof Contact) {
                    Contact p = (Contact) obj;
                    data.add(p);
                    counter++;
                }
            }
        } catch (EOFException ex) {
        } catch (FileNotFoundException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            counter = 0;
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            counter = 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            counter = 0;
        }
        contacts = data;
        return counter;
    }

    /**
     * Stores the phonebook to a file writing the list at once
     *
     * @param filename the name of the file
     * @return the number of elements saved
     */
    public int savePhoneBook2(String filename) {
        int counter = 0;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(contacts);
            counter = contacts.size();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            counter = 0;
        }
        return counter;
    }

    /**
     * Reads a list of contacts from a file reading the list at once
     *
     * @return number of elements read
     */
    public int readPhoneBook2(String filename) {
        List<Contact> data = new ArrayList<>();
        int counter = 0;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            data = (List<Contact>) ois.readObject();
            counter = data.size();
        } catch (EOFException ex) {
        } catch (FileNotFoundException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        contacts = data;
        return counter;
    }

}
