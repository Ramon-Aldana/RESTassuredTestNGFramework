package chaining;


import org.testng.ITestContext;
import org.testng.annotations.Test;

import endpoints.UserEndPoints;
import payloads.User;

public class GetUser {
	@Test
	public void testGetUserByName(ITestContext context) {
		User user = (User)context.getAttribute("user");
		
		UserEndPoints.readUser(user.getUsername())
		.then().statusCode(200).and().log().body();
	}
}
