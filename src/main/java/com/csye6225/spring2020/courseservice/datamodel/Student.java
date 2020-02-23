package com.csye6225.spring2020.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String id;
	private String studentId;
	private String firstName;
	private String lastName;
	private String programName;
	private String imageUrl;
	private String email;
	private List<String> courseEnrolled = new ArrayList<>() ;
	
	public Student() {
		
	}
	
	public Student(String studentId, String firstName, String lastName, String programName, String imageUrl,
			String email) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.programName = programName;
		this.imageUrl = imageUrl;
		this.email = email;
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<String> getCourseEnrolled() {
		return courseEnrolled;
	}

	public void setCourseEnrolled(List<String> courseEnrolled) {
		this.courseEnrolled = courseEnrolled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", programName=" + programName + ", imageUrl=" + imageUrl + ", email=" + email;
	}
	
	public String getAllCourses() {
		String allCourses = "";
		for (String course : courseEnrolled) {
			allCourses += course;
		}
		return allCourses;
	}

}
