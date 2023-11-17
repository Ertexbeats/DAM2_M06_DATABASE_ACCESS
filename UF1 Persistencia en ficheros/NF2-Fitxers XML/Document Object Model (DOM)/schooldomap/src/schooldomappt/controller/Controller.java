package schooldomappt.controller;

import schooldomappt.model.Group;
import schooldomappt.model.Model;
import schooldomappt.model.School;
import schooldomappt.model.Student;
import schooldomappt.model.persistence.SchoolXmlDomPersist;
import schooldomappt.view.SchoolMainView;

import java.util.List;
import java.util.Scanner;

public class Controller {
    SchoolXmlDomPersist persister = new SchoolXmlDomPersist();
    private Model model;
    private SchoolMainView view;
    private String filename;

    public Controller() {
    }

    public Controller( Model model ) {
        this.model = model;
        this.view = new SchoolMainView( this, model );
        this.view.display();

    }

    /**
     * Processes requests from the view.
     *
     * @param action to execute.
     */
    public void executeAction( String action ) {
        switch ( action ) {
            case "exit":
                exitApplication();
                break;
            case "groups/all":
                listAllGroups();
                break;
            case "students/bygroup":
                listStudentsByGroup();
                break;
            case "students/all":
                listAllStudents();
                break;
            case "schollfromxml":
                loadSchoolFromXml();
                break;
            case "savexml":
                saveSchool();
                break;
            case "wrong_option":
            default:
                view.showMessage( "Wrong option" );
                break;
        }
    }

    private void saveSchool() {
        //view.showMessage( "listAllStudents: Not implemented yet!" );
        inputFilename( "Input file name: " );
        School school = persister.load();

        outputFilename( "Output file name: " );
        if ( school != null ) {
            persister.save( school ); // Guardar la escuela en el nuevo archivo XML
            showMessage( "School successfully saved to " + filename );
        } else {
            showMessage( "No school data to save." );
        }
    }

    public void exitApplication() {
        System.exit( 0 );
    }

    private void listAllStudents() {
        //view.showMessage( "listAllStudents: Not implemented yet!" );
        inputFilename( "Input file name: " );
        School school = persister.load();
        if ( school != null ) {
            showMessage( "Successfully read" );
            List< Student > students = school.getAllStudents();
            for ( Student student : students ) {
                // Mostramos la informacion de cada estudiante
                showMessage( "Student: " + student.getId() );
                showMessage( "Name: " + student.getName() + " " + student.getSurname() );
                showMessage( "Emails: " + student.getEmails().toString() );
                showMessage( "Age: " + student.getAge() );
            }
        } else {
            showMessage( "Read fail" );
        }
    }

    private void listAllGroups() {
        //view.showMessage( "listAllGroups: Not implemented yet!" );
        inputFilename( "Input file name: " );
        School school = persister.load();
        if ( school != null ) {
            showMessage( "Successfully read" );
            List< Group > groups = school.getAllGroups();
            for ( Group group : groups ) {
                // Mostramos la informacion de cada grupo
                showMessage( "Group: " + group.getName() );
                showMessage( "Tutor: " + group.getTutor() );
                showMessage( "Curriculum: " + group.getCurriculum() );
            }
        } else {
            showMessage( "Read fail" );
        }
    }

    private void listStudentsByGroup() {
        Scanner sc = new Scanner( System.in );
        //view.showMessage( "listStudentsByGroup: Not implemented yet!" );
        inputFilename( "Input file name: " );
        School school = persister.load();
        if ( school != null ) {
            System.out.print( "enter the group name: " );
            String gName = sc.nextLine();
            Group gFound = null;
            for ( Group group : school.getGroups() ) {
                if ( group.getName().equals( gName ) ) {
                    gFound = new Group( group.getName(), group.getTutor(), group.getCurriculum(), group.getStudents() );
                }
            }
            showMessage( "Successfully read" );
            // Mostramos la informacion de el grupo introducido
            showMessage( "Group: " + gFound.getName() );
            showMessage( "Tutor: " + gFound.getTutor() );
            showMessage( "Curriculum: " + gFound.getCurriculum() );
            showMessage( "Students" );
            for ( Student student : gFound.getStudents() ) {
                // Mostramos la informacion de cada estudiante por el grupo seleccionado
                showMessage( "Student: " + student.getId() );
                showMessage( "Name: " + student.getName() + " " + student.getSurname() );
                showMessage( "Emails: " + student.getEmails().toString() );
                showMessage( "Age: " + student.getAge() );
            }

        }
    }

    private void loadSchoolFromXml() {
        //view.showMessage( "loadSchoolFromXml: Not implemented yet!" );
        inputFilename( "Input file name: " );
        School school = persister.load();
        if ( school != null ) {
            showMessage( "Successfully read" );
            showMessage( "Groups" );
            for ( Group group : school.getGroups() ) {
                // Mostramos la informacion de cada grupo
                showMessage( "Group: " + group.getName() );
                showMessage( "Tutor: " + group.getTutor() );
                showMessage( "Curriculum: " + group.getCurriculum() );
                showMessage( "Students" );
                for ( Student student : group.getStudents() ) {
                    // Mostramos la informacion de cada estudiante
                    showMessage( "Student: " + student.getId() );
                    showMessage( "Name: " + student.getName() + " " + student.getSurname() );
                    showMessage( "Emails: " + student.getEmails().toString() );
                    showMessage( "Age: " + student.getAge() );
                }
            }
        } else {
            showMessage( "Read fail" );
        }
    }

    private void inputFilename( String message ) {
        Scanner sc = new Scanner( System.in );
        System.out.print( message );
        //filename = sc.nextLine();
        filename = "resources/school.xml";
        persister.setFileName( filename );
    }

    private void outputFilename( String message ) {
        Scanner sc = new Scanner( System.in );
        System.out.print( message );
        filename = sc.nextLine();
        filename = "resources/" + filename;
        persister.setFileName( filename );
    }

    public void showMessage( String message ) {
        System.out.println( message );
    }
}