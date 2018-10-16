package com.cloudcomputing.fall2018.courseservice.resources;

import com.cloudcomputing.fall2018.courseservice.datamodel.Course;
import com.cloudcomputing.fall2018.courseservice.datamodel.Program;
import com.cloudcomputing.fall2018.courseservice.service.ProgramService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/programs")
public class ProgramResource {

    private ProgramService programService = new ProgramService();

    // .../programs
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Program> getAllPrograms(){
        return programService.getAllPrograms();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProgram(String name, List<Course> courses) {
        programService.addProgram(name, courses);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProgram(String name) {
        programService.addProgram(name);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCourseToProgram(String courseId, String programName){
        programService.addCourseToProgram(courseId, programName);
    }

    // .../programs/is
    @GET
    @Path("/{programName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Program getProgram(@PathParam("programName") String name) {
        return programService.getProgram(name);
    }

    // .../programs/is
    @DELETE
    @Path("/{programName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Program deleteProgram(@PathParam("programName") String name) {
        return programService.deleteProgram(name);
    }

    @DELETE
    @Path("/{programName}/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Program deleteCourseInProgram(@PathParam("courseId") String courseId, @PathParam("programName") String programName) {
        return programService.deleteCourseInProgram(courseId, programName);
    }

    // .../programs/is
    @PUT
    @Path("/{programName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Program updateProgram(@PathParam("programName") String name, Program program) {
        return programService.updateProgramInformation(name, program);
    }

}
