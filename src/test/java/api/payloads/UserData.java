package api.payloads;

import com.github.javafaker.Faker;

public class UserData {

	public static User newUser() {
		Faker faker = new Faker();
		User user = new User();
        user.setId(faker.number().randomDigitNotZero());
        user.setUsername(faker.name().username());
        //Additional data
        udpateUserDetails(user);
        return user;
	}
	public static void  udpateUserDetails(User thisUser) {
		Faker faker = new Faker();
		thisUser.setFirstName(faker.name().firstName());
		thisUser.setLastName(faker.name().lastName());
		thisUser.setEmail(faker.internet().emailAddress());
		thisUser.setPassword(faker.internet().password(8, 16));
		thisUser.setPhone(faker.phoneNumber().cellPhone());
		thisUser.setUserStatus(0); // You can modify the logic for userStatus if needed
		
	}
	
}
