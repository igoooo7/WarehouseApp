package pl.nowator_zpu.warehouse_app.data_access;

import java.util.ArrayList;

import pl.nowator_zpu.warehouse_app.application_classes.Part;
import pl.nowator_zpu.warehouse_app.application_classes.User;
import pl.nowator_zpu.warehouse_app.entities.JobTitles;
import pl.nowator_zpu.warehouse_app.entities.UserRights;
import pl.nowator_zpu.warehouse_app.entities.Users;

public class Controller {

	DBManagerForUsers dbManagerForUsers = new DBManagerForUsers();
	DBManagerForParts dbManagerForParts = new DBManagerForParts();

	//----------- User Manager -----------
	// User
	public User getUserByUserName(String userName) {
		return dbManagerForUsers.getUserByUserName(userName);
	}

	public ArrayList<User> getAllUsers() {
		return dbManagerForUsers.getAllUsers();
	}

	public Boolean newUser(Users user) {
		return dbManagerForUsers.newUser(user);
	}

	public Boolean deleteUser(User user) {
		return dbManagerForUsers.deleteUser(user);
	}

	// Job title
	public ArrayList<String> getAllJobTitles() {
		return dbManagerForUsers.getAllJobTitles();
	}

	public JobTitles getJobTitleByTitle(String title) {
		return dbManagerForUsers.getJobTitleByTitle(title);
	}

	// User rights
	public ArrayList<String> getAllUserRights() {
		return dbManagerForUsers.getAllUserRights();
	}

	public UserRights getUserRightsByRights(String rights) {
		return dbManagerForUsers.getUserRightsByRights(rights);
	}
	
	//----------- Part Manager -----------
	
	// Part
	public ArrayList<Part> getAllParts() {
		return dbManagerForParts.getAllParts();
	}
}
