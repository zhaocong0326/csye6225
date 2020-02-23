package com.csye6225.spring2020.courseservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.csye6225.spring2020.courseservice.datamodel.Lecture;
import com.csye6225.spring2020.courseservice.service.LecturesService;

//.. /webapi/myresource
@Path("lectures")
public class LecturesResource {
	LecturesService lecturesService = new LecturesService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lecture> getLectures() {
		return lecturesService.getAllLectures();
	}	
	
	// ... webapi/lecture/1 
	@GET
	@Path("/{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture getLecture(@PathParam("lectureId") String lectureId) {
		System.out.println("lecture Resource: Looking for: " + lectureId);
		return lecturesService.getLecture(lectureId);
	}
	
	@DELETE
	@Path("/{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Lecture deleteLecture(@PathParam("lectureId") String lectureId) {
		return lecturesService.deleteLecture(lectureId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lecture addLecture(Lecture lecture) {
		return lecturesService.addLecture(lecture);
	}
	
	@PUT
	@Path("/{lectureId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Lecture updateLecture(@PathParam("lectureId") String lectureId,
			Lecture lecture) {
		return lecturesService.updateLectureInformation(lectureId, lecture);
	}
}
