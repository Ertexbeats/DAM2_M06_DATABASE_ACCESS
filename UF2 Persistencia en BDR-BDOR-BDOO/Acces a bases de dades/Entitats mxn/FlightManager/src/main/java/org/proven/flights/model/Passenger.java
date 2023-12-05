package org.proven.flights.model;

import lombok.*;

@AllArgsConstructor
@Data
//@Setter
//@Getter
//@EqualsAndHashCode
//@ToString

public class Passenger {

    private long id;
    private String phone;
    private boolean minor;

//    public Passenger( long id, String phone, boolean minor ) {
//        this.id = id;
//        this.phone = phone;
//        this.minor = minor;
//    }
//
//    public Passenger( long id, String phone ) {
//        this.id = id;
//        this.phone = phone;
//        this.minor = true;
//    }
}
