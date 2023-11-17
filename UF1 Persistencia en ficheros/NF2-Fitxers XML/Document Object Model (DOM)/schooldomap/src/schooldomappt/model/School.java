package schooldomappt.model;

import java.util.ArrayList;
import java.util.List;

public class School {
    private String name;
    private List< Group > groups;

    public School( String name ) {
        this.name = name;
        this.groups = new ArrayList<>();
    }

    public School( String name, List< Group > groups ) {
        this.name = name;
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List< Group > getGroups() {
        return groups;
    }

    public void setGroups( List< Group > groups ) {
        this.groups = groups;
    }

    public boolean addGroup( Group group ) {
        //TODO
        if ( group != null && !groups.contains( group ) ) {
            return groups.add( group );
        }
        return false;
    }

    public List< Group > getAllGroups() {
        //TODO
        return new ArrayList<>( groups );
    }

    public List< Student > getAllStudents() {
        //TODO
        List< Student > allStudents = new ArrayList<>();
        for ( Group group : groups ) {
            allStudents.addAll( group.getStudents() );
        }
        return allStudents;
    }

    public List< Student > getStudentsByGroup( Group group ) {
        //TODO
        List< Student > studentsByGroup = new ArrayList<>();
        for ( Group g : groups ) {
            if ( g.equals( group ) ) {
                studentsByGroup.addAll( g.getStudents() );
            }
        }
        //studentsByGroup.addAll( group.getStudents() );

        return studentsByGroup;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "School{" );
        sb.append( "name=" ).append( name );
        sb.append( ", groups=" ).append( groups );
        sb.append( '}' );
        return sb.toString();
    }
}