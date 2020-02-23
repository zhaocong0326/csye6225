package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Program;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class ProgramsService {

	static HashMap<String, Program> program_Map = InMemoryDatabase.getProgramDB();
	
	public ProgramsService() {
		
	}
	
	// Getting a list of all Program 
	// GET "..webapi/programs"
	public List<Program> getAllPrograms() {	
		//Getting the list
		ArrayList<Program> list = new ArrayList<>();
		for (Program program : program_Map.values()) {
			list.add(program);
		}
		if (list.size()!= 0) {
			System.out.println("All programs retrieved!");
		} else {
			System.out.println("No programs data!");
		}
		return list ;
	}

	// Adding a Program
	public Program addProgram(Program program) {
        // Next Id
		Program newProgram = new Program(program.getProgramId(), program.getProgramName());
		String programId = program.getProgramId();
        program_Map.put(programId, newProgram);
		System.out.println("Item added:");
        return newProgram;
    }
	
	
	// Getting One Program
	public Program getProgram(String programId) {
		 //Simple HashKey Load
		if(program_Map.containsKey(programId)) {
		 Program program = program_Map.get(programId);
	     System.out.println("Item retrieved:");
	     System.out.println(program.toString());
		return program;
		} else {
			System.out.println("Program " + programId + " does not exist!!!");
			return null;
		}
	}
	
	// Deleting a Program
	public Program deleteProgram(String programId) {
		if(program_Map.containsKey(programId)) {
			Program deletedProgramDetails = program_Map.get(programId);
			program_Map.remove(programId);
			System.out.println("Item deleted:");
			System.out.println(deletedProgramDetails.toString());
			return deletedProgramDetails;
		} else {
			System.out.println("Program " + programId + " does not exist!!!");
			return null;
		}

	}
	
	// Updating Program Info
	public Program updateProgramInformation(String programId, Program program) {
		if(program_Map.containsKey(programId)) {
			program.setProgramId(programId);
			program_Map.put(programId,program);
			System.out.println("Item updated:");
			System.out.println(program.toString());
		} else {
			System.out.println("Cannot find the program data with" + programId);
		}
		return program;
	}
	
}
