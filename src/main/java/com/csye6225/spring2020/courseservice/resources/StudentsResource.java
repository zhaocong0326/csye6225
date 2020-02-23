package com.csye6225.spring2020.courseservice.resources;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.csye6225.spring2020.courseservice.datamodel.Student;
import com.csye6225.spring2020.courseservice.service.StudentsService;

//.. /webapi/myresource
@Path("students")
public class StudentsResource {
	StudentsService studentsService = new StudentsService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudents() {
		return studentsService.getAllStudents();
	}	

	// ... webapi/student/1 
	@GET
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("studentId") String studentId) {
		System.out.println("Student Resource: Looking for: " + studentId);
		return studentsService.getStudent(studentId);
	}

	@GET
	@Path("/{program}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentByProgram(
			@PathParam("program") String programName, @PathParam("id") String id) {

		if (programName == null) {
			return studentsService.getAllStudents();
		}
		System.out.println("Student Resource: Looking for: " + programName);
		return studentsService.getStudentsByProgram(programName, id);

	}
	
	@DELETE
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteStudent(@PathParam("studentId") String studentId) {
		return studentsService.deleteStudent(studentId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student student) {
		if (student.getStudentId() == null) {
			student.setStudentId(student.getFirstName()+student.getLastName());
		}
		return studentsService.addStudent(student);
	}
	
	@PUT
	@Path("/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(@PathParam("studentId") String studentId, 
			Student student) {
		return studentsService.updateStudentInformation(studentId, student);
	}

	@PUT
	@Path("/{studentId}/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudentEmail(@PathParam("studentId") String studentId,
									  @PathParam("email") String email) {
		return studentsService.updateStudentEmail(studentId, email);
	}

//	@PUT
//	@Path("{studentId}/{course}/{newCourse}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Student updateStudentCourse(@PathParam("studentId") String studentId,
//									   @PathParam("course")String course, @PathParam("newCourse")String newCourse) {
//		return studentsService.updateStudentCourse(studentId, course,newCourse);
//	}

}
