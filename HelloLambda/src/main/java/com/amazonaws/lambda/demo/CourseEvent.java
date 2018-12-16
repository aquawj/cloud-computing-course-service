package com.amazonaws.lambda.demo;

public class CourseEvent{
	private String courseId;
	private String boardId;
	private String department;
	private String topic;
	
   public CourseEvent() {
   
   }
   
   public CourseEvent(String courseId, String boardId, String department) {
       this.courseId = courseId;
       this.boardId = boardId;
       this.department = department;
       this.topic = "";
   }
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@Override
   public String toString() {
       return "{\"courseId\": \"" + courseId + "\", \"boardId\": \"" + boardId + "\", \"department\": \"" + department +
               "\", \"topic\": \"" + topic + "\"}";
   }
	
}
