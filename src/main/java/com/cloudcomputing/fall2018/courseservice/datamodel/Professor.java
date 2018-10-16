package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Professor {
	private String firstName;
	private String department;
	private long professorId;
	private Date joingDate;
	
	public Professor() {
		
	}
	
	public Professor(long professorId, String firstName, String department, Date joiningDate) {
		this.professorId = professorId;
		this.firstName = firstName;
		this.department = department;
		this.joingDate = joiningDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(long professorId) {
		this.professorId = professorId;
	}

	public Date getJoingDate() {
		return joingDate;
	}

	public void setJoingDate(Date joingDate) {
		this.joingDate = joingDate;
	}
	
	
	
}
