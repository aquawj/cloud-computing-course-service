package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@XmlRootElement
@DynamoDBTable(tableName="course")
public class Course {
    String ID;
    String courseId;
    Long professorId;
    String taId;
    String department;
    String boardId;
    List<String> roster;
    
    public Course(){

    }

	public Course(String iD, String courseId, Long professorId, String taId, String department, String boardId,
			List<String> roster) {
		this.ID = iD;
		this.courseId = courseId;
		this.professorId = professorId;
		this.taId = taId;
		this.department = department;
		this.boardId = boardId;
		this.roster = roster;
	}

	@DynamoDBHashKey(attributeName="ID")
	@DynamoDBAutoGeneratedKey
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	@DynamoDBIndexHashKey(attributeName="courseId", globalSecondaryIndexName = "courseId-index")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@DynamoDBAttribute(attributeName="professorId")
	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}

	@DynamoDBAttribute(attributeName="taId")
	public String getTaId() {
		return taId;
	}

	public void setTaId(String taId) {
		this.taId = taId;
	}

	@DynamoDBAttribute(attributeName="department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@DynamoDBAttribute(attributeName="boardId")
	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	@DynamoDBAttribute(attributeName="roster")
	public List<String> getRoster() {
		return roster;
	}

	public void setRoster(List<String> roster) {
		this.roster = roster;
	}

    
}
