package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Professor;

public class ProfessorsService extends Exception{
	
	static HashMap<String, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public ProfessorsService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	
	// Getting a list of all professor 
	// GET "..webapi/professors"
	public List<Professor> getAllProfessors() {	
		//Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withIndexName("professorId-index")
				.withConsistentRead(false);

		List<Professor> list =  mapper.scan(Professor.class, scanExpression);
		return list ;
	}
	// adding a professor
	public Professor addProfessor(Professor prof) {
		Professor prof2 = new Professor(prof.getProfessorId(), prof.getFirstName(),
				prof.getLastName(), prof.getDepartment(), prof.getJoiningDate());
		mapper.save(prof2);
		System.out.println("Item added:");
		System.out.println(prof2.toString());
        return prof2;
    }

	// Getting One Professor
	public Professor getProfessor(String profId) {
		List<Professor> profList = getProfessorFromDynamoDB(profId);
		return profList.size() != 0 ? profList.get(0) : null;

	}
	
	// Deleting a professor
	public Professor deleteProfessor(String profId) {
		List<Professor>profList = getProfessorFromDynamoDB(profId);
		Professor prof = null;
		if(profList.size() != 0){
			prof = profList.get(0);
			mapper.delete(prof );

			Professor prof2 = mapper.load(Professor.class, prof.getId());
			if (prof2 == null) {
				System.out.println("The professor is deleted.");
				System.out.println(prof.toString());
			}
		}

		System.out.println("Item deleted:");
		System.out.println(prof.toString());
		return prof;
	}
	
	// Updating Professor Info
	public Professor updateProfessorInformation(String profId, Professor prof) {
		List<Professor> list = getProfessorFromDynamoDB(profId);
		Professor Prof2 = null;
		if(list.size() != 0) {
			Prof2 = list.get(0);
			Prof2.setProfessorId(prof.getProfessorId());
			Prof2.setFirstName(prof.getFirstName());
			Prof2.setLastName(prof.getLastName());
			Prof2.setDepartment(prof.getDepartment());
			Prof2.setJoiningDate(prof.getJoiningDate());
			mapper.save(Prof2);
			System.out.println("Item updated:");
			System.out.println(Prof2.toString());
		}
		return Prof2;
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

	public List<Professor> getProfessorFromDynamoDB(String profId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(profId));

		DynamoDBQueryExpression<Professor> queryExpression = new DynamoDBQueryExpression<Professor>()
				.withIndexName("professorId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("professorId = :v1")
				.withExpressionAttributeValues(eav);

		List<Professor> professorList =  mapper.query(Professor.class, queryExpression);
		return professorList;
	}

}