package com.cloudcomputing.fall2018.courseservice.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cloudcomputing.fall2018.courseservice.datamodel.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnouncementService {

	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
    DynamoDBQueryExpression<Announcement> queryExpression;
    DynamoDBScanExpression scanExpression;
	
	public AnnouncementService(){
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
		queryExpression = new DynamoDBQueryExpression<Announcement>();
	    scanExpression = new DynamoDBScanExpression();
	}

    //get all Announcements
    public List<Announcement> getAllAnnouncements(){
    	return mapper.scan(Announcement.class, scanExpression);
    }

    public Announcement addAnnouncement(Announcement an) {
        mapper.save(an);
        return an;
    }

    public List<Announcement> getAnnouncement(String bId_aId) {
    	String[] ids = bId_aId.split("_");
    	String bId = ids[0];
    	String aId = ids[1];
    	Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1",  new AttributeValue().withS(bId));
		eav.put(":v2",  new AttributeValue().withS(aId));

		DynamoDBQueryExpression<Announcement> queryExpression = new DynamoDBQueryExpression<Announcement>()
		    .withIndexName("BoardId-AnnouncementId-Index")
		    .withConsistentRead(false)
		    .withKeyConditionExpression("BoardId = :v1 and begins_with(AnnouncementId, :v2)")
		    .withExpressionAttributeValues(eav);

		List<Announcement> announcements =  mapper.query(Announcement.class, queryExpression);
		return announcements;
    }
    

    //delete a Announcement
    public Announcement deleteAnnouncement(String bId_aId) {
    	for(Announcement an : getAnnouncement(bId_aId)) {
			mapper.delete(an);
		}
		return getAnnouncement(bId_aId).get(0);
    }

    //update a Announcement
    public Announcement updateAnnouncementInformation(String bId_aId, Announcement announcement) {
    	List<Announcement> delete = getAnnouncement(bId_aId);
    	for(Announcement d : delete) {
    		mapper.delete(d);
    	}
    	announcement.setAnnouncementId(bId_aId);
		mapper.save(announcement);
		return announcement;
    }
    
    private List<Announcement> queryAnnouncements(String bId_aId) {
    	String[] ids = bId_aId.split("_");
    	String boardId = ids[0];
    	String announcementId = ids[1];
    	
    	Announcement announcement = new Announcement();
        announcement.setAnnouncementId(announcementId);
        announcement.setBoardId(boardId);
        queryExpression.setHashKeyValues(announcement);
        queryExpression.withIndexName("boardId-announcementId-index");
        queryExpression.setConsistentRead(false);
        List<Announcement> announcements = mapper.query(Announcement.class, queryExpression);
        return announcements;
    }
    
}


