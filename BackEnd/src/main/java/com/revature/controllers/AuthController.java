package com.revature.controllers;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.AuthService;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class AuthController {
	
	static final Logger log = LogManager.getLogger(AuthController.class.getName());
	
	Gson gson = new Gson();
	
	AuthService authService = new AuthService();
	
	public static HttpSession session;
	
	//Method to check if user is authenticated. This method is used in other controller handlers
	public static boolean isLoggedIn(Context ctx) {
		
		if (session != null) {
			return true;
		} else {

			System.out.println("Access denied: User not logged in.");
			log.warn("Access denied to user: Login required.");
			
			ctx.result("Access denied: Login required.");
			ctx.status(410);
			return false;
		}
		
	}
	
	public Handler loginHandler = (ctx) -> {
		
		//Getting Json from HTTP body
		String body = ctx.body();
		
		//Parsing Json to LoginDTO object
		LoginDTO loginDTO = gson.fromJson(body, LoginDTO.class);
		
		User user = null;
		
		try {
			//Instantiate a User object if authService.login is successful
			user = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
		} catch (Exception e) {
			System.out.println("Exception occured:");
			e.printStackTrace();
		}
		
		
		//If user obj successfully instantiated set session
		if (user != null) {
			
			System.out.println("User login successful.");
			log.info("User login successful.");
			
			ctx.result("Login successful");
			ctx.status(200);
			
			session = ctx.req.getSession();
		} else {
			
			System.out.println("Login attempt failed.");
			log.warn("User login attempt failed.");
			
			ctx.result("Login failed.");
			ctx.status(401);
		}
	
	};
		
	public Handler logoutHandler = (ctx) -> {
			
			session = null;
			System.out.println("Logout successful.");
			log.warn("User logout successful.");
			
			ctx.result("Logged out.");
			ctx.status(200);
	};

}
