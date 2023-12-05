package org.proven.flights.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Data
//@Setter
//@Getter
//@EqualsAndHashCode
//@ToString

public class Flight {

    private long id;
    private String code;
    private int capacity;
    private LocalDate date;
    private LocalTime time;

//    public Flight( long id, String code, int capacity, LocalDate date, LocalTime time ) {
//        this.id = id;
//        this.code = code;
//        this.capacity = capacity;
//        this.date = date;
//        this.time = time;
//    }
//
//    public Flight( long id, String code, LocalDate date, LocalTime time ) {
//        this.id = id;
//        this.code = code;
//        this.capacity = 0;
//        this.date = date;
//        this.time = time;
//    }
}
