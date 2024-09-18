package api.test;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.github.javafaker.Faker;

import api.endpoints.*;
import api.payloads.User;


public class UserTests {
	Faker faker;
	User user ;
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
        faker = new Faker();
        user = new User();
        
        user.setId(faker.number().randomDigitNotZero());
        user.setUsername(faker.name().username());
        udpateUserDetails();

        //Logs
        logger = LogManager.getLogger(this.getClass());
        }

	public void udpateUserDetails() {
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(8, 16));
        user.setPhone(faker.phoneNumber().cellPhone());
        user.setUserStatus(0); // You can modify the logic for userStatus if needed
	}
	
	
	@Test(priority=1)
	public void testCreateUser()
	{
		logger.info("*********** Creating User ******************");
		UserEndPoints.createUser(user).andReturn()
		.then().statusCode(200).and().log().body();
		logger.info("*********** Created User ******************");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		UserEndPoints.readUser(this.user.getUsername())
		.then().statusCode(200).and().log().body();
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		udpateUserDetails();
		UserEndPoints.updateUser(user.getUsername(), user)
		.then().statusCode(200).and().log().body();
	}
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		udpateUserDetails();
		UserEndPoints.deleteUser(user.getUsername())
		.then().statusCode(200).and().log().body();
		
	}
	
}
