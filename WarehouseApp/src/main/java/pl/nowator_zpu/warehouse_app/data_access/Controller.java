package pl.nowator_zpu.warehouse_app.data_access;

import pl.nowator_zpu.warehouse_app.entities.Users;

public class Controller {
	
	DBManagerForUsers dbManagerForUsers = new DBManagerForUsers();
	 
	public Users getUserByUserName(String userName) {
		
		return dbManagerForUsers.getUserByUserName(userName);

	}

}
