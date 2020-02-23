package com.csye6225.spring2020.courseservice.datamodel;

public class Board {
	private String id;
	private String boardId;
	private String courseId;
	
	public Board() {
		
	}

	public Board(String boardId, String courseId) {
		this.boardId = boardId;
		this.courseId = courseId;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "boardId=" + boardId + ", courseId=" + courseId;
	}

}
