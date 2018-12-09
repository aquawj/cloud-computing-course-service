package com.cloudcomputing.fall2018.courseservice.service;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.cloudcomputing.fall2018.courseservice.datamodel.Course;
import com.cloudcomputing.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.cloudcomputing.fall2018.courseservice.datamodel.Student;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.Subscription;
import com.amazonaws.services.sns.model.UnsubscribeRequest;


public class RegisterService{
	
	static DynamoDB dynamoDB;
	public boolean isRegistered = false;
    
    public RegisterService(){
		DynamoDbConnector.init();
	}
    
    StudentService studentService = new StudentService();
    CourseService courseService = new CourseService();

	public void register(String studentId, String courseId) {
		Student student = studentService.getStudent(studentId).get(0);
		Course course = courseService.getCourse(courseId).get(0);
		
		if (student == null || course == null || student.getEnrolledCourses().size() > 2) return;
		List<String> newCourseList = student.getEnrolledCourses();
		newCourseList.add(courseId);
		
		Table StudentTable = dynamoDB.getTable("student");
		UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("ID", student.getID())
	            .withUpdateExpression("set EnrolledClasses= :a")
	            .withValueMap(new ValueMap().withList(":a", newCourseList))
	            .withReturnValues(ReturnValue.UPDATED_NEW);
		StudentTable.updateItem(updateItemSpec);
		
		List<String> roster = course.getRoster();
		roster.add(studentId);
		
		Table CourseTable = dynamoDB.getTable("course");
		updateItemSpec = new UpdateItemSpec().withPrimaryKey("ID", course.getID())
	            .withUpdateExpression("set Roster= :a")
	            .withValueMap(new ValueMap().withList(":a", roster))
	            .withReturnValues(ReturnValue.UPDATED_NEW);
		CourseTable.updateItem(updateItemSpec);
		
		subscribeTopic(course.getTopic(), student.getEmail());
		isRegistered = true;
	}
	
	
	public void subscribeTopic(String topicArn, String email) {
		AmazonSNSClient  snsClient = CourseService.getSNSClient();
		SubscribeRequest subscribeRequest = new SubscribeRequest(topicArn, "Email", email);
		snsClient.subscribe(subscribeRequest);
	}
	
	public void unSubscribeTopic(String topicArn, String email) {
		AmazonSNSClient  snsClient = CourseService.getSNSClient();
		ListSubscriptionsByTopicRequest requestList = new ListSubscriptionsByTopicRequest(topicArn);
		ListSubscriptionsByTopicResult resultList = snsClient.listSubscriptionsByTopic(requestList);
		
		List<Subscription> subscriptions = resultList.getSubscriptions();
		for(Subscription s : subscriptions) {
			if(s.getEndpoint().equals(email)) {
				UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest(s.getSubscriptionArn());
				snsClient.unsubscribe(unsubscribeRequest);
				break;
			}
		}
	}
}