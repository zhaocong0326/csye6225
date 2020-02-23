package com.csye6225.spring2020.courseservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csye6225.spring2020.courseservice.datamodel.InMemoryDatabase;
import com.csye6225.spring2020.courseservice.datamodel.Lecture;
import com.csye6225.spring2020.courseservice.datamodel.Program;

public class LecturesService {
	
static HashMap<String, Lecture> lecture_Map = InMemoryDatabase.getLectureDB();
	
	public LecturesService() {
		
	}
	
	// Getting a list of all lecture 
	// GET "..webapi/lectures"
	public List<Lecture> getAllLectures() {	
		//Getting the list
		ArrayList<Lecture> list = new ArrayList<>();
		for (Lecture lecture : lecture_Map.values()) {
			list.add(lecture);
		}
		if (list.size() != 0) {
			System.out.println("All lectures retrieved.");
		} else {
			System.out.println("No lectures data.");
		}
		return list ;
	}

	// Adding a lecture
	public Lecture addLecture(Lecture lecture) {
        // Next Id
        lecture_Map.put(lecture.getLectureId(), lecture);
		System.out.println("Item added:");
		System.out.println(lecture.toString());
        return lecture;
    }
	
	
	// Getting One lecture
	public Lecture getLecture(String lectureId) {
		if (lecture_Map.containsKey(lectureId)) {
		 //Simple HashKey Load
		 Lecture lecture = lecture_Map.get(lectureId);
	     System.out.println("Item retrieved:");
	     System.out.println(lecture.toString());
		return lecture;
		} else {
			System.out.println("Lecture " + lectureId + " does not exist!!");
			return  null;
		}
	}
	
	// Deleting a lecture
	public Lecture deleteLecture(String lectureId) {
		if (lecture_Map.containsKey(lectureId)) {
			Lecture deletedLectureDetails = lecture_Map.get(lectureId);
			lecture_Map.remove(lectureId);
			System.out.println("Item deleted:");
			System.out.println(deletedLectureDetails.toString());
			return deletedLectureDetails;
		} else {
			System.out.println("Lecture " + lectureId + "does not exist!!");
			return  null;
		}
	}
	
	// Updating lecture Info
	public Lecture updateLectureInformation(String lectureId, Lecture lecture) {
		if (lecture_Map.containsKey(lectureId)) {
			lecture.setLectureId(lectureId);
			lecture.setPublishDate(new Date().toString());
			lecture_Map.put(lectureId, lecture);
			System.out.println("Item updated:");
			System.out.println(lecture.toString());
		} else {
			System.out.println("Cannot find the lecture data with " + lectureId);
		}

		return lecture;


	}

}
