package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.csye6225.spring2020.courseservice.EmailAnnouncement;
import com.csye6225.spring2020.courseservice.datamodel.Course;
import com.csye6225.spring2020.courseservice.datamodel.DynamoDbConnector;
import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Student;

public class StudentsService {
	static HashMap<String, Student> student_Map = InMemoryDatabase.getStudentDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper;

	public StudentsService() {
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
	}
	// Getting a list of all student
	public List<Student> getAllStudents() {
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withIndexName("studentId-index")
				.withConsistentRead(false);

		List<Student> studentList =  mapper.scan(Student.class, scanExpression);
		return studentList ;
	}
	
	// Adding a student
	public Student addStudent(Student student) {
		Student student2 = new Student(student.getStudentId(), student.getFirstName(), student.getLastName(),
				student.getProgramName(), student.getImageUrl(), student.getEmail(), student.getJoiningDate());
		mapper.save(student2);

		System.out.println("Item added.");
		System.out.println(student2.toString());
		return student2;
	}
	
	// Getting student
	public Student getStudent(String studentId) {
		List<Student> studentList = getStudentFromDynamoDB(studentId);
		return studentList.size() != 0 ? studentList.get(0) : null;
	}
	
	// Deleting a student
	public Student deleteStudent(String studentId) {
		List<Student> list = getStudentFromDynamoDB(studentId);
		Student student = null;
		if(list.size() != 0){
			student = list.get(0);
			mapper.delete(student);
			Student deletedStudent = mapper.load(Student.class, student.getId());

			if (deletedStudent == null) {
				System.out.println("The student is deleted.");
				System.out.println(student.toString());
			}
		}
		return student;

	}
	
	// Updating student Info
		public Student updateStudentInformation(String studentId, Student student) {
			List<Student> list = getStudentFromDynamoDB(studentId);
			Student student2 = null;
			if(list.size() != 0) {
				student2 = list.get(0);
				student2.setStudentId(student.getStudentId());
				student2.setFirstName(student.getFirstName());
				student2.setLastName(student.getLastName());
				student2.setJoiningDate(student.getJoiningDate());
				student2.setProgramName(student.getProgramName());
				student2.setImageUrl(student.getImageUrl());
				student2.setEmail(student.getEmail());
				mapper.save(student2);
				System.out.println("Updated.");
				System.out.println(student2.toString());
			}
			return student2;
		}
		
	// update the email of student
	public Student updateStudentEmail(String studentId, String email) {
		Student student = student_Map.get(studentId);
		student.setEmail(email);
		return student;
	}


	// register for a course
	public Student registerCourse(String studentId, Course course){
		List<Student> list = getStudentFromDynamoDB(studentId);
		CoursesService courseSer = new CoursesService();
		Student student = null;
		if(list.size() != 0) {
			student = list.get(0);
			System.out.println(course.getCourseId());
			if(student.getCourseEnrolled().size() < 3) {
				student.getCourseEnrolled().add(course.getCourseId());
				course.getRosterId().add(studentId);

				// update information in database
				updateStudentInformation(studentId,student);
				courseSer.updateCourseInformation(course.getCourseId(), course);

				EmailAnnouncement.subscribe(course.getSnsTopicArn(), student.getEmail());
			}
		}

		return student;
	}


	// get student in a program
	public List<Student> getStudentsByProgram(String program) {
		List<Student> students = new ArrayList<>();
		for (Student student : student_Map.values()) {
			if (student.getProgramName().equals(program)) {
				System.out.print(student.getStudentId());
				students.add(student);
			}
		}
		return students;
	}

	public List<Student> getStudentFromDynamoDB(String stuId){
		HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(stuId));

		DynamoDBQueryExpression<Student> queryExpression = new DynamoDBQueryExpression<Student>()
				.withIndexName("studentId-index")
				.withConsistentRead(false)
				.withKeyConditionExpression("studentId = :v1")
				.withExpressionAttributeValues(eav);

		List<Student> studentList =  mapper.query(Student.class, queryExpression);
		return studentList;
	}
	
}
