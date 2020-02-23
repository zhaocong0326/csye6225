package com.csye6225.spring2020.courseservice.datamodel;

public class Lecture {
	private String id;
	private String lectureId;
	private String lectureName;
	private String notesUrl;
	private String materialUrl;
	private String publishDate;
	
	public Lecture() {
		
	}

	public Lecture(String lectureId, String lectureName, String notesUrl, String materialUrl, String publishDate) {
		this.lectureId = lectureId;
		this.lectureName = lectureName;
		this.notesUrl = notesUrl;
		this.materialUrl = materialUrl;
		this.publishDate = publishDate;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLectureId() {
		return lectureId;
	}

	public void setLectureId(String lectureId) {
		this.lectureId = lectureId;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getNotesUrl() {
		return notesUrl;
	}

	public void setNotesUrl(String notesUrl) {
		this.notesUrl = notesUrl;
	}

	public String getMaterialUrl() {
		return materialUrl;
	}

	public void setMaterialUrl(String materialUrl) {
		this.materialUrl = materialUrl;
	}
	
	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "id=" + id + ", notesUrl=" + notesUrl + ", materialUrl=" + materialUrl + ", publishDate="
				+ publishDate;
	}
}
