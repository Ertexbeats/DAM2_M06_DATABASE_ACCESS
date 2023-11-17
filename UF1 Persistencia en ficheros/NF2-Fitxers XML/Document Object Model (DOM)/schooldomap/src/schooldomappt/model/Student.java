package schooldomappt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private String id;
    private String name;
    private String surname;
    private List< String > emails;
    private int age;

    public Student() {
    }

    public Student( String id, String name, String surname, List< String > emails, int age ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.emails = emails;
        this.age = age;
    }

    public Student( String id, String name, String surname, int age ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.emails = new ArrayList<>();
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname( String surname ) {
        this.surname = surname;
    }

    public List< String > getEmails() {
        return emails;
    }

    public void setEmail( List< String > emails ) {
        this.emails = emails;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public void addEmail( String email ) {
        this.emails.add( email );
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode( this.id );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Student other = ( Student ) obj;
        if ( !Objects.equals( this.id, other.id ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "Student{" );
        sb.append( "id=" ).append( id );
        sb.append( ", name=" ).append( name );
        sb.append( ", surname=" ).append( surname );
        sb.append( ", emails=" ).append( emails );
        sb.append( ", age=" ).append( age );
        sb.append( '}' );
        return sb.toString();
    }
}