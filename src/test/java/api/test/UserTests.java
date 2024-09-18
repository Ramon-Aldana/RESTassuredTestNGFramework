package api.test;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.github.javafaker.Faker;

import api.endpoints.*;
import api.payloads.User;
import api.payloads.UserData;

import static org.hamcrest.Matchers.lessThan;


public class UserTests {
	Faker faker;
	User user ;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
        user = UserData.newUser();
        //Logs
        logger = LogManager.getLogger(this.getClass());
        }

	@Test(priority=1)
	public void testCreateUser()
	{
		UserEndPoints.createUser(user).andReturn()
		.then().statusCode(200).and().log().body();

	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		UserEndPoints.readUser(this.user.getUsername())
		.then()
		.statusCode(200)
		.and().time(lessThan(50L))
		.log().body();
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		UserData.udpateUserDetails(user);
		UserEndPoints.updateUser(user.getUsername(), user)
		.then().statusCode(200).and().log().body();
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		UserData.udpateUserDetails(user);
		UserEndPoints.deleteUser(user.getUsername())
		.then().statusCode(200).and().log().body();
		
	}
	
}
