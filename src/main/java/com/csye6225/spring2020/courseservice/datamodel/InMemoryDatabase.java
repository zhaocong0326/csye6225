package com.csye6225.spring2020.courseservice.datamodel;

import com.amazonaws.services.dynamodbv2.xspec.B;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InMemoryDatabase {

	private static HashMap<String, Professor> professorDB = new HashMap<> ();
	private static HashMap<String, Student> studentDB = new HashMap<> ();
	private static HashMap<String, Program> programDB = new HashMap<> ();
	private static HashMap<String, Course> courseDB = new HashMap<> ();
	private static HashMap<String, Lecture> lectureDB = new HashMap<> ();
	private static HashMap<String, Board> boardDB = new HashMap<> ();


	public static HashMap<String, Professor> getProfessorDB() {
		return professorDB;
	}
	
	public static HashMap<String, Student> getStudentDB() {
		return studentDB;
	}
	
	public static HashMap<String, Program> getProgramDB() {
		return programDB;
	}
	
	public static HashMap<String, Course> getCourseDB() {
		return courseDB;
	}
	
	public static HashMap<String, Lecture> getLectureDB() {
		return lectureDB;
	}
	
	public static HashMap<String, Board> getBoardDB() {
		return boardDB;
	}
}

/*
 * 
 * */
 