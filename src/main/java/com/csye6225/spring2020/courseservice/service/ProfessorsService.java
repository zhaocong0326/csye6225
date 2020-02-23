package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
//import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
//import com.csye6225.spring2020.courseservice.datamodel.Board;
//import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Professor;

public class ProfessorsService extends Exception{
	
	static HashMap<String, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	
	public ProfessorsService() {
	}
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		//Getting the list
		ArrayList<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			list.add(prof);
		}
		if (list.size() != 0) {
			System.out.println("All professors updated ");
		} else {
			System.out.println("No professors data!");
		}
		return list ;
	}
	// adding a professor
	public Professor addProfessor(Professor prof) {
		prof_Map.put(prof.getProfessorId(), prof);
		System.out.println("Item added:");
		System.out.println(prof.toString());
        return prof;
    }

	// Getting One Professor
	public Professor getProfessor(String profId) {
		if (prof_Map.containsKey(profId)) {
		 //Simple HashKey Load
		 Professor prof2 = prof_Map.get(profId);
	     System.out.println("Item retrieved:");
	     System.out.println(prof2.toString());
		return prof2;
		} else {
			System.out.println("Professor" + profId + " does not exist!!!");
			return null;
		}
	}
	
	// Deleting a professor
	public Professor deleteProfessor(String profId) {
		if (prof_Map.containsKey(profId)) {
			Professor deletedProfDetails = prof_Map.get(profId);
			prof_Map.remove(profId);
			System.out.println("Item deleted:");
			System.out.println(deletedProfDetails.toString());
			return deletedProfDetails;
		} else {
			System.out.println("Professor" + profId + " does not exist!!!");
			return null;
		}

	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(String profId, Professor prof) {
		if (prof_Map.containsKey(profId)) {
			prof.setProfessorId(profId);
			prof_Map.put(profId , prof);
			System.out.println("Item updated:");
			System.out.println(prof.toString());
		} else  {
			System.out.println("Cannot find the professor data with " + profId);
		}
		return prof;
	}
	
	// Get professors in a department 
	public List<Professor> getProfessorsByDepartment(String department) {
		//Getting the list
		List<Professor> list = new ArrayList<>();
		for (Professor prof : prof_Map.values()) {
			if (prof.getDepartment().equals(department)) {
				list.add(prof);
			}
		}
		return list ;
	}
}