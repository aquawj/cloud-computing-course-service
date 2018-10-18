package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.HashMap;

public class InMemoryDatabase {
	
	private static HashMap<Long, Professor> professorDB = new HashMap<>();
	
	public static HashMap<Long, Professor> getProfessorDB(){
		return professorDB;
	}

	private static HashMap<Long, Student> studentDB = new HashMap<>();

	public static HashMap<Long, Student> getStudentDB(){
		return studentDB;
	}

	private static HashMap<String, Course> courseDB = new HashMap<>();

	public static HashMap<String, Course> getCourseDB(){
		return courseDB;
	}

	private static HashMap<Integer, Lecture> lectureDB = new HashMap<>();

	public static HashMap<Integer, Lecture> getLectureDB(){
		return lectureDB;
	}

	private static HashMap<String, Program> programDB = new HashMap<>();

	public static HashMap<String, Program> getProgramDB(){
		return programDB;
	}


}

