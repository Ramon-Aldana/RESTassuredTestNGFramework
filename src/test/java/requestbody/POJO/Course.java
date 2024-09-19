package requestbody.POJO;





//Course POJO class
public class Course {
	private int course_id;
	private String course_name;
	private String instructor;
	private int credits;

	// Default constructor
	public Course() {
	}

	// Parameterized constructor
	public Course(int courseId, String courseName, String instructor, int credits) {
		this.course_id = courseId;
		this.course_name = courseName;
		this.instructor = instructor;
		this.credits = credits;
	}

	// Getters and Setters
	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int courseId) {
		this.course_id = courseId;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourseName(String course_name) {
		this.course_name = course_name;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}


}
