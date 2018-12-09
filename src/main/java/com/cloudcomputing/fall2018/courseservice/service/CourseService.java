package com.cloudcomputing.fall2018.courseservice.service;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.cloudcomputing.fall2018.courseservice.datamodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseService {
	private static AmazonSNSClientBuilder snsClientBuilder;
	private static AmazonSNSClient  snsClient;
	
    static HashMap<String, Course> course_Map = InMemoryDatabase.getCourseDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
    DynamoDBQueryExpression<Course> queryExpression;
    DynamoDBScanExpression scanExpression;
    
    public CourseService(){
		dynamoDb = new DynamoDbConnector();
		dynamoDb.init();
		mapper = new DynamoDBMapper(dynamoDb.getClient());
		queryExpression = new DynamoDBQueryExpression<Course>();
	    scanExpression = new DynamoDBScanExpression();
	    snsClientBuilder = AmazonSNSClientBuilder
				.standard()
				.withCredentials(new InstanceProfileCredentialsProvider(false))
				.withRegion(Regions.US_WEST_2);
		
		snsClient = (AmazonSNSClient) snsClientBuilder.build();
	}

    //get all courses
    public List<Course> getAllCourses(){
    	return mapper.scan(Course.class, scanExpression);
    }

    // add a course
    public Course addCourse(Course course) {
    	 mapper.save(course);
         return course;
    }

    // add a student to course
    public Course addStudentToCourse(String courseId, String studentId){
        Course course = getCourse(courseId).get(0);
        course.getRoster().add(studentId);
        return course;
    }

    //get a course
    public List<Course> getCourse(String courseId){
    	return queryCourse(courseId);
    }


    //delete a course
    public Course deleteCourse(String courseId) {
    	for(Course c : getCourse(courseId)) {
			mapper.delete(c);
		}
		return getCourse(courseId).get(0);
    }

    //delete a student in a course
    public Course deleteStudentInCourse(String courseId, String studentId) {
    	Course course = getCourse(courseId).get(0);
        course.getRoster().remove(studentId);
        removeTopic(course.getTopic());
        return course;
    }
    
    //update a course  
    public Course updateCourseInformation(String courseId, Course course) {
 	    List<Course> delete = getCourse(courseId);
    	for(Course d : delete) {
    		mapper.delete(d);
    	}
    	course.setCourseId(courseId);
	
		removeTopic(delete.get(0).getTopic());
		String topic = createTopic(course.getCourseId().toString());
		course.setTopic(topic);
		
		mapper.save(course);
		return course;
    }
    
    private List<Course> queryCourse(String courseId) {
    	Course course = new Course();
    	course.setCourseId(courseId);
        queryExpression.setHashKeyValues(course);
        queryExpression.withIndexName("courseId-index");
        queryExpression.setConsistentRead(false);
        List<Course> courses = mapper.query(Course.class, queryExpression);
        return courses;
    }
    
    public static AmazonSNSClient getSNSClient() {
		return snsClient;
	}
    
    public String createTopic(String courseId) {
		CreateTopicRequest createTopicRequest = new CreateTopicRequest(courseId);
        CreateTopicResult createTopicResult = snsClient.createTopic(createTopicRequest);
        
        String topic = createTopicResult.getTopicArn();
        return topic;
	}
	
	public void removeTopic(String topic) {
		DeleteTopicRequest deleteTopicRequest = new DeleteTopicRequest(topic);
		snsClient.deleteTopic(deleteTopicRequest);
	}
}
