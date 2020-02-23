package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Student;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class StudentsService {
	static HashMap<String, Student> student_Map = InMemoryDatabase.getStudentDB();
	
	// Getting a list of all student
	public List<Student> getAllStudents() {
		List<Student> list = new ArrayList<>();
		for (Student student : student_Map.values()) {
			list.add(student);
		}
		if (list.size() != 0) {
			System.out.println("All students retrieved:");
		} else {
			System.out.println("No students data!");
		}
		return list;
	}
	
	// Adding a student
	public Student addStudent(Student student) {
        student_Map.put(student.getStudentId(), student);

		System.out.println("Item added:");
		System.out.println(student.toString());
        return student;
    }
	
	// Getting student
	public Student getStudent(String studentId) {
		if (student_Map.containsKey(studentId)) {
			Student student = student_Map.get(studentId);
			System.out.println("Item retrieved");
			System.out.println(student.toString());
			return student;
		} else {
			System.out.println("Student " + studentId + " does not exist !!!");
			return null;
		}
	}
	
	// Deleting a student
	public Student deleteStudent(String studentId) {
		if (student_Map.containsKey(studentId)) {
			Student student = student_Map.get(studentId);
			student_Map.remove(studentId);
			System.out.println("Item deleted:");
			System.out.println(student.toString());
			return student;
		} else {
			System.out.println("Student " + studentId + " does not exist !!!");
			return null;
		}

	}
	
	// Updating student Info
		public Student updateStudentInformation(String studentId, Student student) {
			if (student_Map.containsKey(studentId)) {
				student.setStudentId(studentId);
				student_Map.put(studentId, student);
				System.out.println("Item updated:");
				System.out.println(student.toString());
			} else  {
				System.out.println("Cannot find the student data with " + studentId);
			}
			return student;
		}
		
	// update the email of student
	public Student updateStudentEmail(String studentId, String email) {
		Student student = student_Map.get(studentId);
		student.setEmail(email);
		return student;
	}
	
//	//update enrolled courses of student
//	public Student updateStudentCourse(String studentId, String course, String newCourse) {
//		Student student = student_Map.get(studentId);
//		List<String> courses = student.getCourseEnrolled();
//		for (int i = 0; i <= courses.size() - 1; i++) {
//			String oldCourse = courses.get(i);
//			if (oldCourse.equals(course)) {
//				courses.set(i,newCourse);
//			}
//		}
//		System.out.println(student.toString() + student.getAllCourses());
//		return student;
//	}
	
	// get student in a program
	public List<Student> getStudentsByProgram(String program) {
		List<Student> students = new ArrayList<>();
		for (Student student : student_Map.values()) {
			if (student.getProgramName().equals(program)) {
				System.out.print(student.getStudentId());
				students.add(student);
			}
		}
		return students;
	}

	
}
