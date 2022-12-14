package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

public class UserDAO implements UserDAOInterface {
	
	static final Logger log = LogManager.getLogger();

	@Override
	public ArrayList<User> getAllUsers() {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			//Creating an empty ArrayList that will hold Users
			ArrayList <User> userList = new ArrayList<>();
			
			String sql = "SELECT * FROM users;";
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				User user = new User(
						resultSet.getInt("user_id"), 
						resultSet.getString("username"), 
						resultSet.getString("password"), 
						resultSet.getString("user_first_name"), 
						resultSet.getString("user_last_name"), 
						resultSet.getString("user_email"), 
						resultSet.getInt("user_role_id_fk"), 
						null //UserRole parameter is filled below
						);
				
				//Getting role for user by passing object to method (defined at the bottom of this class)and adding it to user object
				user.setUserRole(this.getUserRoleForUser(user));
				
				//Adding User to userList
				userList.add(user);
				
			}
			
		return userList;
			
		} catch (SQLException e) {
			
			log.warn("Failed getting all Users. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User getUserById(int user_id) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM users WHERE user_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				User user = new User(
						resultSet.getInt("user_id"), 
						resultSet.getString("username"), 
						resultSet.getString("password"), 
						resultSet.getString("user_first_name"), 
						resultSet.getString("user_last_name"), 
						resultSet.getString("user_email"), 
						resultSet.getInt("user_role_id_fk"), 
						null //UserRole parameter is filled below
						);
				
				user.setUserRole(this.getUserRoleForUser(user));
				
				return user;
			}
			
			
			
		} catch (SQLException e) {
			
			log.warn("Failed getting User by ID. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO users (username, password, user_first_name, user_last_name, user_email, user_role_id_fk) "
					+ "VALUES ?, ?, ?, ?, ?, ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getUser_first_name());
			preparedStatement.setString(4, user.getUser_last_name());
			preparedStatement.setString(5, user.getUser_email());
			preparedStatement.setInt(6, user.getUser_role_id_fk());
			
			preparedStatement.executeUpdate();
			
			System.out.println("User " + user.getUser_first_name() + "successfully added.");
			
			return true;
			
		} catch (SQLException e) {
			
			log.warn("Failed inserting User. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteUserWithId(int user_id) {
		
		try (Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "DELETE FROM user_roles WHERE user_role_id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, user_id);
			
			preparedStatement.executeUpdate();
			
			System.out.println("UserRole " + user_id + " successfully deleted.");
			return true;
			
		} catch (SQLException e) {
			
			log.warn("Unable to delete UserRole. SQL Exception occured: " + e);
			e.printStackTrace();
		}
		
		return false;
	}
	
	/* MARK: - Helper Methods --------------------------------------------------------------------- */

	@Override
	public UserRole getUserRoleForUser(User user) {
		
		UserRoleDAO userRoleDAO = new UserRoleDAO();
		UserRole userRole = userRoleDAO.getUserRoleById(user.getUser_role_id_fk());
		
		return userRole;
	}

}
