package api.test;

import org.testng.annotations.Test;


import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.ExcelDataProvider;

public class UserDataDrivenTests {

	@Test(priority=1, dataProvider="Data", dataProviderClass=ExcelDataProvider.class)
	public void testCreateUsers(String UserID,String  UserName,String  FirstName,String  LastName,String  Email,String  Password,String  Phone) 
	{
		User user = new User((int)Double.parseDouble(UserID),UserName,FirstName,LastName,Email,Password,Phone, 0);
		
		UserEndPoints.createUser(user).andReturn()
		.then().statusCode(200).and().log().body();
	}

	@Test(priority = 2, dataProvider = "UserName", dataProviderClass = ExcelDataProvider.class)
	public void testDeleteUsers(String UserName) {

		UserEndPoints.deleteUser(UserName).andReturn()
		.then()
			.statusCode(200).and().log().body();

	}
}
