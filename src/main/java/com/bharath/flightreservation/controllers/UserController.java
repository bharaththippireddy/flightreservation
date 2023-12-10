package com.bharath.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.entities.User;
import com.bharath.flightreservation.repos.UserRepository;
import com.bharath.flightreservation.services.SecurityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityService securityService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
	}

	@PostMapping("/registerUser")
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register()" + user);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";

	}
	
	@GetMapping("/showLogin")
	public String showLoginPage() {
		LOGGER.info("Inside showLoginPage()");
		return "login/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
		boolean loginResponse = securityService.login(email, password,request,response);
		LOGGER.info("Inside login() and the email is: " + email);
		if (loginResponse) {
			return "findFlights";
		} else {
			modelMap.addAttribute("msg", "Invalid user name or password .Please try again.");
		}

		return "login/login";

	}
}
