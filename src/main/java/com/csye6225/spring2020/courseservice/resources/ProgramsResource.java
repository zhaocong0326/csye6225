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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.csye6225.spring2020.courseservice.datamodel.Program;
import com.csye6225.spring2020.courseservice.service.ProgramsService;

//.. /webapi/myresource
@Path("programs")
public class ProgramsResource {
	ProgramsService programsService = new ProgramsService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getPrograms() {
		return programsService.getAllPrograms();
	}	
	
	// ... webapi/program/1 
	@GET
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("programId") String programId) {
		System.out.println("program Resource: Looking for: " + programId);
		return programsService.getProgram(programId);
	}
	
	@DELETE
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("programId") String programId) {
		return programsService.deleteProgram(programId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program program) {
		return programsService.addProgram(program);
	}
	
	@PUT
	@Path("/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("programId") String programId,
			Program program) {
		return programsService.updateProgramInformation(programId, program);
	}
}
