package com.bharath.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.entities.Flight;
import com.bharath.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {

	@Autowired
	FlightRepository flightRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

	@PostMapping("findFlights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate,
			ModelMap modelMap) {

		LOGGER.info("Inside findFlights() From:" + from + " TO:" + to + "Departure Date: " + departureDate);
		List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
		modelMap.addAttribute("flights", flights);
		LOGGER.info("Flight Found are:" + flights);
		System.out.println("Flights "+flights);
		return "displayFlights";

	}
	
	@GetMapping("admin/showAddFlight")
	public String showAddFlight() {
		return "addFlight";
	}

}
