package pl.nowator_zpu.warehouse_app.data_access;

import pl.nowator_zpu.warehouse_app.application_classes.User;

public class Controller {
	
	DBManagerForUsers dbManagerForUsers = new DBManagerForUsers();
	 
	public User getUserByUserName(String userName) {
		
		return dbManagerForUsers.getUserByUserName(userName);

	}

}
