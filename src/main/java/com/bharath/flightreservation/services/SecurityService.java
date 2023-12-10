package com.bharath.flightreservation.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SecurityService {
	
	boolean login(String username,String password,HttpServletRequest request,HttpServletResponse response);

}
