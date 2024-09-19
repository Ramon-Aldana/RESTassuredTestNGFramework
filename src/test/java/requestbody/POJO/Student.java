package requestbody.POJO;

import java.util.List;

//Student POJO class
public class Student {
 private String name;
 private int age;
 private String email;
 private List<Course> courses;

 // Default constructor
 public Student() {
 }

 // Parameterized constructor
 public Student(String name, int age, String email, List<Course> courses) {
     this.name = name;
     this.age = age;
     this.email = email;
     this.courses = courses;
 }

 // Getters and Setters
 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public int getAge() {
     return age;
 }

 public void setAge(int age) {
     this.age = age;
 }

 public String getEmail() {
     return email;
 }

 public void setEmail(String email) {
     this.email = email;
 }

 public List<Course> getCourses() {
     return courses;
 }

 public void setCourses(List<Course> courses) {
     this.courses = courses;
 }


}
