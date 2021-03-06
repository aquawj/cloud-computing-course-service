package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@XmlRootElement
@DynamoDBTable(tableName="board")
public class Board {
    String ID;
    String boardId;
    String courseId;
    List<String> announcements;
    
    public Board(){

    }
    
    public Board(String boardId) {
		super();
		this.boardId = boardId;
	}

	public Board(String boardId, String courseId) {
		super();
		this.boardId = boardId;
		this.courseId = courseId;
	}

	@DynamoDBHashKey(attributeName="ID")
	@DynamoDBAutoGeneratedKey
	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}

	@DynamoDBIndexHashKey(attributeName="boardId", globalSecondaryIndexName = "boardId-index")
	public String getBoardId() {
		return boardId;
	}


	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	@DynamoDBAttribute(attributeName="courseId")
	public String getCourseId() {
		return courseId;
	}


	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	@DynamoDBAttribute(attributeName = "announcements")
    public List<String> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<String> announcements) {
        this.announcements = announcements;
    }

}
