package com.cloudcomputing.fall2018.courseservice.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.cloudcomputing.fall2018.courseservice.datamodel.Professor;
import com.cloudcomputing.fall2018.courseservice.service.ProfessorsService;

//.. /webapi/myresource
@Path("professors")
public class ProfessorsResource {
	
	ProfessorsService profService = new ProfessorsService();
	
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Professor> getProfessors() {
//		return profService.getAllProfessors();
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorsByDeparment(@QueryParam("department") String department){
		if(department == null) {
			return profService.getAllProfessors();
		}else {
			return profService.getProfessorsByDepartment(department);
		}
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Professor addProfessor(Professor prof) {
		return profService.addProfessor(prof);
	}
	
	public void addProfessor(String name, String department, Date joiningDate) {
		profService.addProfessor(name, department, joiningDate);
	}
	
	// ...webapi/professors/1
	@GET
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("professorId") long profId) {
		return profService.getProfessor(profId);
	}
	
	@DELETE
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("professorId") long profId) {
		return profService.deleteProfessor(profId);
	}
	
	@PUT
	@Path("/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(@PathParam("professorId") long profId, Professor prof) {
		return profService.updateProfessorInformation(profId, prof);
	}
	
	
	
	
}
