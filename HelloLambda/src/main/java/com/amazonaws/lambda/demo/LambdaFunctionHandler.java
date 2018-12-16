package com.amazonaws.lambda.demo;

import java.util.Map;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent.DynamodbStreamRecord;

public class LambdaFunctionHandler implements RequestHandler<DynamodbEvent, Integer> {

	private AmazonSNSClientBuilder snsClientBuilder = AmazonSNSClientBuilder
			.standard()
			.withRegion(Regions.US_WEST_2);
	
	private AmazonSNSClient snsClient = (AmazonSNSClient) snsClientBuilder.build();
	
    @Override
    public Integer handleRequest(DynamodbEvent event, Context context) {
        int success = 0;
		for(DynamodbStreamRecord record : event.getRecords()) {
			if(record == null) break; 
			String curEvent = record.getEventName();
			if(!curEvent.equals("INSERT"))
				continue;
			success = 1;
			Map<String, AttributeValue> map = record.getDynamodb().getNewImage();
			String arn = "arn:aws:sns:us-west-2:821043062437:NotificationTopic";
			String subject = "new announcement";
			StringBuilder message = new StringBuilder().append("There is a new announcement:");
			if(map != null) {
				if(map.get("AnnouncementText")!= null) {
					message.append(map.get("AnnouncementText").getS());
				}
				if(map.get("CourseId")!= null) {
					sendNotification(arn + map.get("CourseId").getS(), subject, message.toString());
				}
			}
		}
		return success;
    }
    
    private void sendNotification(String topicArn, String subject, String message) {
		PublishRequest request = new PublishRequest(topicArn, message, subject);
		snsClient.publish(request);
    }
}



