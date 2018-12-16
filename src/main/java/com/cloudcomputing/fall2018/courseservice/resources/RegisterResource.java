package com.cloudcomputing.fall2018.courseservice.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.cloudcomputing.fall2018.courseservice.datamodel.Registrar;
import com.cloudcomputing.fall2018.courseservice.service.RegisterService;

@Path("registerOffering")
public class RegisterResource {
	private RegisterService registerService = new RegisterService();
	//.../registerOffering
 	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Registrar addRegistrar(Registrar registrar) {
		return registerService.addRegistrar(registrar);
	}
	
 }