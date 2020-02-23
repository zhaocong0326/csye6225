package com.csye6225.spring2020.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Program {
	private String id;
	private String programId;
	private String programName;
	private List<String> courses = new ArrayList<>();
	
	public Program() {
		
	}

	public Program(String programId, String programName) {
		this.programId = programId;
		this.programName = programName;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "programId=" + programId + ", programName=" + programName + ", courses=" + courses;
	}

}
