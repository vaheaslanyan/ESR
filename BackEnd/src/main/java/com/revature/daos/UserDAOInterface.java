package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.User;
import com.revature.models.UserRole;

public interface UserDAOInterface {

	//Get all Users
	ArrayList <User> getAllUsers();
	
	//Get User by ID
	User getUserById(int user_id);
	
	//Insert a new User
	User insertUser(User user);
	
	//Delete User with ID
	User deleteUser(User user);
	
	//Get UserRole for User
	UserRole getUserRoleForUser(User user);
	
}
