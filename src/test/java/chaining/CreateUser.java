package chaining;


import org.testng.annotations.Test;

import endpoints.UserEndPoints;
import payloads.User;
import payloads.UserData;

import org.testng.ITestContext;


public class CreateUser {
	@Test
	public void testCreateUser(ITestContext context)
	{
		//logger.info("*********** Creating User ******************");
		
		User user = UserData.newUser();
		UserEndPoints.createUser(user).andReturn()
		.then().statusCode(200).and().log().body();
		
		context.setAttribute("user", user);
		
		//logger.info("*********** Created User ******************");
	}
	
}
