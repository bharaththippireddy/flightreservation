package com.bharath.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bharath.flightreservation.dto.ReservationUpdateRequest;
import com.bharath.flightreservation.entities.Reservation;
import com.bharath.flightreservation.repos.ReservationRepository;

@RestController
public class ReservationRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	@Autowired
	ReservationRepository reservationRepository;

	@GetMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation() for id: " + id);
		return reservationRepository.findById(id).get();

	}

	@PostMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation() for " + request);
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		LOGGER.info("Saving Reservation");
		return reservationRepository.save(reservation);

	}

}
