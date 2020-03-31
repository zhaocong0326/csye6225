package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.spring2020.courseservice.EmailAnnouncement;
import com.csye6225.spring2020.courseservice.datamodel.Board;
import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;

public class CoursesService {
	static HashMap<String, Course> course_Map = InMemoryDatabase.getCourseDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public CoursesService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	// Getting a list of all course 
	// GET "..webapi/courses"
	public List<Course> getAllCourses() {
		//Getting the list
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withIndexName("courseId-index")
				.withConsistentRead(false);

		List<Course> courseList =  mapper.scan(Course.class, scanExpression);
		return courseList ;
	}
	
	// Adding a course
	public Course addCourse(Course course) {
		String topicArn = EmailAnnouncement.createTopic("course"+course.getCourseId());
		Course course2 = new Course(course.getCourseId(), course.getCourseName(),course.getProfessorId(),
				course.getTaId(), course.getDepartment(), course.getBoardId());
		mapper.save(course2);

		if(!course.getBoardId().equals("")) {
			Board board = new Board(course2.getBoardId(),course2.getCourseId());
			BoardsService boardSer = new BoardsService();
			boardSer.addBoard(board);
		}

		System.out.println("Item added:");
		System.out.println(course2.toString());
		return course2;
    }

	// Getting One course
	public Course getCourse(String courseId) {
		List<Course> list = getCourseFromDynamoDB(courseId);
		return list.size() != 0 ? list.get(0) : null;
	}
	
	// Deleting a course
	public Course deleteCourse(String courseId) {
		List<Course> courseList = getCourseFromDynamoDB(courseId);
		Course course = null;
		if(courseList.size() != 0){
			course = courseList.get(0);
			mapper.delete(course);
			Course deletedCourse = mapper.load(Course.class, course.getId());

			if (deletedCourse == null) {
				System.out.println("The course is deleted.");
				System.out.println(course.toString());
			}
		}
		return course;
	}
	
	// Updating course Info
	public Course updateCourseInformation(String courseId, Course course) {
		List<Course> list = getCourseFromDynamoDB(courseId);
		Course course2 = null;
		if(list.size() != 0) {
			course2= list.get(0);
			course2.setCourseId(course.getCourseId());
			course2.setProfessorId(course.getProfessorId());
			course2.setTaId(course.getTaId());
			course2.setDepartment(course.getDepartment());
			course2.setBoardId(course.getBoardId());
			course2.setRosterId(course.getRosterId());
			course2.setSnsTopicArn(course.getSnsTopicArn());
			mapper.save(course2);
			System.out.println("Item updated:");
			System.out.println(course2.toString());
		}
		return course2;
	}

	public List<Course> getCourseFromDynamoDB(String courseId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(courseId));

		DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
				.withIndexName("courseId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("courseId = :v1")
				.withExpressionAttributeValues(eav);

		List<Course> courseList =  mapper.query(Course.class, queryExpression);
		return courseList;
	}
}
