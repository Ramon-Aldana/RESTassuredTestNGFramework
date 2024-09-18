package api.endpoints;

import java.util.ResourceBundle;

public class Routes {

	public static String baseUrl = "https://petstore.swagger.io/v2";
	
	//user module
//	public static String post_url = baseUrl + "/user";
//	public static String get_url = baseUrl + "/user/{username}";
//	public static String update_url = baseUrl + "/user/{username}";
//	public static String delete_url = baseUrl + "/user/{username}";
	
	public static String post_url = ResourceBundle.getBundle("routes").getString("post_url");
	public static String get_url = ResourceBundle.getBundle("routes").getString("get_url");
	public static String update_url = ResourceBundle.getBundle("routes").getString("update_url");
	public static String delete_url = ResourceBundle.getBundle("routes").getString("delete_url");
	//store module
	
	//pet module
	

}
