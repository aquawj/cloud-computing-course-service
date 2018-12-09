package com.cloudcomputing.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.cloudcomputing.fall2018.courseservice.datamodel.DynamoDbConnector;
import com.cloudcomputing.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.cloudcomputing.fall2018.courseservice.datamodel.Professor;
import com.cloudcomputing.fall2018.courseservice.datamodel.Student;

public class ProfessorsService {
	//static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	static DynamoDbConnector dynamoDb;
	DynamoDBMapper mapper; 
    DynamoDBQueryExpression<Professor> queryExpression;
    DynamoDBScanExpression scanExpression;
	
	public ProfessorsService(){
		//dynamoDb = new DynamoDbConnector();
		DynamoDbConnector.init();
		mapper = new DynamoDBMapper(DynamoDbConnector.getClient());
		queryExpression = new DynamoDBQueryExpression<Professor>();
		scanExpression = new DynamoDBScanExpression();
	}

	//get a list of all professors
	//GET "..webapi/professors"
	public List<Professor> getAllProfessors(){
		return mapper.scan(Professor.class, scanExpression);
	}
	
	// Get professors in a department 
//	public List<Professor> getProfessorsByDepartment(String department) {	
//		List<Professor> professors = queryProfessors(department);
//		return professors;
//	}	
	
	// Adding a professor
	public void addProfessor(long professorId, String name, String department, String joiningDate) {
		//next id?
		//long nextAvailableId = prof_Map.size() + 1;
		Professor prof = new Professor(professorId, name, department, joiningDate);
		mapper.save(prof);
	}
	
	public Professor addProfessor(Professor prof) {
		mapper.save(prof);
		return prof;
	}
	
	// Getting One Professor
	public List<Professor> getProfessor(long profId) {
		List<Professor> professors = queryProfessors(profId);
		return professors;
	}
		
	//Deleting a professor
	public void deleteProfessor(long profId) {
		for(Professor p : getProfessor(profId)) {
			mapper.delete(p);
		}
	}
	
	//Updating Professor Info
	public Professor updateProfessorInformation(long profId, Professor prof) {
		List<Professor> delete = getProfessor(profId);
    	for(Professor d : delete) {
    		mapper.delete(d);
    	}
		prof.setProfessorId(profId);
		mapper.save(prof);
		return prof;
	}
	
	private List<Professor> queryProfessors(long profId) {
        Professor p= new Professor();
        p.setProfessorId(profId);
        queryExpression.setHashKeyValues(p);
        queryExpression.withIndexName("professorId-index");
        queryExpression.setConsistentRead(false);
        List<Professor> professors = mapper.query(Professor.class, queryExpression);
        return professors;
    }
	
//	private List<Professor> queryProfessors(String dept) {
////		List<Professor> all = getAllProfessors();
////		for(Professor p : all) {
////			if(p.getDepartment().equals(dept)) {
////				scanExpression
//////				queryExpression.setHashKeyValues(p);
//////				queryExpression.withIndexName("professorId-index");
//////		        queryExpression.setConsistentRead(false);
////			}
////		}
//		Map<String, AttributeValue> map = new HashMap<String, AttributeValue>();
//        map.put(":d", new AttributeValue().withS(dept));
//
//        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
//            .withFilterExpression("department = :d").withExpressionAttributeValues(map);
//        
//        List<Professor> professors = mapper.query(Professor.class, queryExpression);
//        return professors;
//    }
}
