package com.cloudcomputing.fall2018.courseservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.cloudcomputing.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.cloudcomputing.fall2018.courseservice.datamodel.Professor;

public class ProfessorsService {
	static HashMap<Long, Professor> prof_Map = InMemoryDatabase.getProfessorDB();
	
	//get a list of all professors
	//GET "..webapi/professors"
	public List<Professor> getAllProfessors(){
		List<Professor> list = new ArrayList<>();
		list.addAll(prof_Map.values());
		return list;
	}
	
	// Get professors in a department 
		public List<Professor> getProfessorsByDepartment(String department) {	
			//Getting the list
			ArrayList<Professor> list = new ArrayList<>();
			for (Professor prof : prof_Map.values()) {
				if (prof.getDepartment().equals(department)) {
					list.add(prof);
				}
			}
			return list ;
		}
	
	// Adding a professor
	//for post
	public void addProfessor(String name, String department, Date joiningDate) {
		//next id?
		long nextAvailableId = prof_Map.size() + 1;
		
		Professor prof = new Professor(nextAvailableId, name, department, joiningDate);
		prof_Map.put(nextAvailableId, prof);
	}
	public Professor addProfessor(Professor prof) {
		long nextAvailableId = prof_Map.size() + 1;
		prof.setProfessorId(nextAvailableId);
		prof_Map.put(nextAvailableId, prof);
		return prof_Map.get(nextAvailableId);
	}
	
	// Getting One Professor
	public Professor getProfessor(long profId) {
		return prof_Map.get(profId);
	}
	
	//Deleting a professor
	public Professor deleteProfessor(Long profId) {
		Professor deletedProfDetails = prof_Map.get(profId);
		prof_Map.remove(profId);
		return deletedProfDetails;
	}
	
	//Updating Professor Info
	public Professor updateProfessorInformation(long profId, Professor prof) {
		prof.setProfessorId(profId);
		prof_Map.put(profId, prof) ;
		return prof;
	}
	
}
