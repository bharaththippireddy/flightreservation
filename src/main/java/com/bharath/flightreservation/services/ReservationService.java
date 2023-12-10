package com.bharath.flightreservation.services;

import com.bharath.flightreservation.dto.ReservationRequest;
import com.bharath.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
