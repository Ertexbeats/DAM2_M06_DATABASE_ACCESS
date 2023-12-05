/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subscribermanager.model;

import java.util.Objects;

/**
 * @author ProvenSoft
 */
public class Subscriber {

    private long id;
    private String name;
    private String address;
    private String phone;
    private int age;

    public Subscriber() {
    }

    public Subscriber( String name, String address, String phone, int age ) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.age = age;
    }

    public Subscriber( long id, String name, String address, String phone, int age ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.age = age;
    }

    public Subscriber( long id ) {
        this.id = id;
    }

    public Subscriber( Subscriber other ) {
        this.id = other.id;
        this.name = other.name;
        this.address = other.address;
        this.phone = other.phone;
        this.age = other.age;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode( this.name );
        hash = 97 * hash + Objects.hashCode( this.phone );
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
        final Subscriber other = ( Subscriber ) obj;
        if ( !Objects.equals( this.name, other.name ) ) {
            return false;
        }
        if ( !Objects.equals( this.phone, other.phone ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "Subscriber{" );
        sb.append( "id=" ).append( id );
        sb.append( ", name=" ).append( name );
        sb.append( ", address=" ).append( address );
        sb.append( ", phone=" ).append( phone );
        sb.append( ", age=" ).append( age );
        sb.append( '}' );
        return sb.toString();
    }

}