package requestbody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import requestbody.POJO.Course;
import requestbody.POJO.Student;


public class BodyGenerator {

	public static Map<String, Object> NewHashMapStudent() {

		// First student
		Map<String, Object> student = new HashMap<String, Object>();
		// student.put("id", "1");
		student.put("name", "John Doe");
		student.put("age", 20);
		student.put("email", "johndoe@example.com");

		// Create a list of courses for student
		ArrayList<HashMap<String, Object>> courses = new ArrayList<HashMap<String, Object>>();

		HashMap<String, Object> course1 = new HashMap<String, Object>();
		course1.put("course_id", 101);
		course1.put("course_name", "Introduction to Computer Science");
		course1.put("instructor", "Dr. Smith");
		course1.put("credits", 3);
		courses.add(course1);

		HashMap<String, Object> course2 = new HashMap<String, Object>();
		course2.put("course_id", 102);
		course2.put("course_name", "Calculus I");
		course2.put("instructor", "Prof. Johnson");
		course2.put("credits", 4);
		courses.add(course2);

		student.put("courses", courses);

		return student;
	}

	public static JSONObject NewJSONObjectStudent() {
		// First student
		JSONObject student = new JSONObject();
		// student.put("id", "1");
		student.put("name", "John Doe");
		student.put("age", 20);
		student.put("email", "johndoe@example.com");

		// Create a list of courses for student
		ArrayList<JSONObject> courses = new ArrayList<JSONObject>();

		JSONObject course1 = new JSONObject();
		course1.put("course_id", 101);
		course1.put("course_name", "Introduction to Computer Science");
		course1.put("instructor", "Dr. Smith");
		course1.put("credits", 3);
		courses.add(course1);

		JSONObject course2 = new JSONObject();
		course2.put("course_id", 102);
		course2.put("course_name", "Calculus I");
		course2.put("instructor", "Prof. Johnson");
		course2.put("credits", 4);
		courses.add(course2);

		student.put("courses", courses);

		return student;
	}
	
	 public static Student NewPOJOStudent() {
	        // Create the first student
	        Student student = new Student();
	        student.setName("John Doe");
	        student.setAge(20);
	        student.setEmail("johndoe@example.com");

	        // Create a list of courses for the student
	        ArrayList<Course> courses = new ArrayList<Course>();

	        // First course
	        Course course1 = new Course(101, "Introduction to Computer Science", "Dr. Smith", 3);
	        courses.add(course1);

	        // Second course
	        Course course2 = new Course(102, "Calculus I", "Prof. Johnson", 4);
	        courses.add(course2);

	        // Set courses for the student
	        student.setCourses(courses);

	        return student;
	    }
	 public static JSONObject NewJsonFileStudent() throws IOException {
         String content = new String(Files.readAllBytes(Paths.get(".\\newstudent.json")));
         
         // Convert string content to a JSONObject
         JSONObject student = new JSONObject(content);
         return student;
	 }
}


