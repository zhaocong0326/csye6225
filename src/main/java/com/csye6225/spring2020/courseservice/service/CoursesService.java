package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;

public class CoursesService {
	static HashMap<String, Course> course_Map = InMemoryDatabase.getCourseDB();
	
	public CoursesService() {
		
	}
	// Getting a list of all course 
	// GET "..webapi/courses"
	public List<Course> getAllCourses() {
		//Getting the list
		List<Course> list = new ArrayList<>();
		for (Course course : course_Map.values()) {
			list.add(course);
		}
		if (list.size() != 0) {
			System.out.println("All courses updated.");
		} else {
			System.out.println("No courses data.");
		}
		return list;
	}
	
	// Adding a course
	public Course addCourse(Course course) {
	    course_Map.put(course.getCourseId(), course);
		System.out.println("Item added:");
		System.out.println(course.toString());
	    return course;
    }

	// Getting One course
	public Course getCourse(String courseId) {
		if (course_Map.containsKey(courseId)) {
		 //Simple HashKey Load
		 Course course = course_Map.get(courseId);
	     System.out.println("Item retrieved:");
	     System.out.println(course.toString());
		return course;
		} else {
			System.out.println("Course " + courseId + " does not exist!!");
			return null;
		}
	}
	
	// Deleting a course
	public Course deleteCourse(String courseId) {
		if (course_Map.containsKey(courseId)) {
			Course deletedCourseDetails = course_Map.get(courseId);
			course_Map.remove(courseId);
			System.out.println("Item deleted:");
			System.out.println(deletedCourseDetails.toString());
			return deletedCourseDetails;
		} else {
			System.out.println("Course " + courseId + "does not exist!!");
			return null;
		}
	}
	
	// Updating course Info
	public Course updateCourseInformation(String courseId, Course course) {
		if (course_Map.containsKey(courseId)) {
			course.setCourseId(courseId);
			course_Map.put(courseId, course);
			System.out.println("Item updated:");
			System.out.println(course.toString());
		} else {
			System.out.println("Cannot find the course data with " + courseId);
		}
		return course;
	}
}
