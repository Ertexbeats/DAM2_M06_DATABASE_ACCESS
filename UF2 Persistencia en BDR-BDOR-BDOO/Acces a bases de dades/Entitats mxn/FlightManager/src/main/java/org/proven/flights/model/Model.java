package org.proven.flights.model;

import org.proven.flights.model.persist.FlightDao;
import org.proven.flights.model.persist.FlightPassengerDao;
import org.proven.flights.model.persist.PassengerDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Model service for flights application.
 *
 * @author ProvenSoft
 */
public class Model {
    /**
     * DAO object for Flight entity.
     */
    private final FlightDao flightDao;
    /**
     * DAO object for Passenger entity.
     */
    private final PassengerDao passengerDao;
    private final FlightPassengerDao flightpassengerDao;

    public Model() throws ClassNotFoundException {
        this.flightDao = new FlightDao();
        this.passengerDao = new PassengerDao();
        this.flightpassengerDao = new FlightPassengerDao();
    }

    /**
     * searches all flights in database.
     *
     * @return list of all flights or null in case or error.
     */
    public List< Flight > searchAllFlights() {
        List< Flight > result = null;
        result = flightDao.selectAll();
        return result;
    }

    /**
     * adds a flight to database, prevents code duplicates.
     *
     * @param flight the flight to add.
     * @return 1 if succesfull, 0 if not, -1 in case of error.
     */
    public int addFlight( Flight flight ) {
        int result = 0;
        result = flightDao.insert( flight );
        return result;
    }

    /**
     * searches all passengers in database.
     *
     * @return list of all flights or null in case or error.
     */
    public List< Passenger > searchAllPassengers() {
        List< Passenger > result = null;
        result = passengerDao.selectAll();
        return result;
    }

    /**
     * search passengers in given flight.
     *
     * @param flight the flight to search passengers in.
     * @return list of passengers or null in case of error.
     */
    public List< Passenger > searchPassengersByFlight( Flight flight ) {
        List< Passenger > result = new ArrayList<>();
        List< FlightPassenger > flightpassengerList = flightpassengerDao.selectWhereFlightId( flight.getId() );
        if ( flightpassengerList != null ) {
            for ( FlightPassenger fp : flightpassengerList ) {
                Passenger p = passengerDao.selectWhereId( fp.getPassengerId() );
                if ( p != null ) {
                    result.add( p );
                }
            }
        }
        return result;
    }

}