package com.cloudcomputing.fall2018.courseservice.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.cloudcomputing.fall2018.courseservice.datamodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseService {
    //static HashMap<String, Course> course_Map = InMemoryDatabase.getCourseDB();
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
        return course;
    }

    //delete a lecture in a course
    
    //update a course
    
    public Course updateCourseInformation(String courseId, Course course) {
    	List<Course> delete = getCourse(courseId);
    	for(Course d : delete) {
    		mapper.delete(d);
    	}
    	course.setCourseId(courseId);
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
}
