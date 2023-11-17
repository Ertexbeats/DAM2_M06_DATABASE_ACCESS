package schooldomappt.view;

public class SchoolMenu extends Menu {

    public SchoolMenu() {
        super( "School manager" );
        addOption( new Option( "Exit", "exit" ) );
        addOption( new Option( "List all students", "students/all" ) );
        addOption( new Option( "List all groups", "groups/all" ) );
        addOption( new Option( "List students by group", "students/bygroup" ) );
        addOption( new Option( "Load data from xml file", "schollfromxml" ) );
        addOption( new Option( "Save the information in another xml file", "savexml" ) );
    }


}
