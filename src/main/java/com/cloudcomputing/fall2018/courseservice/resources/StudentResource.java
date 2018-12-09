package com.cloudcomputing.fall2018.courseservice.resources;

import com.cloudcomputing.fall2018.courseservice.datamodel.Student;
import com.cloudcomputing.fall2018.courseservice.service.RegisterService;
import com.cloudcomputing.fall2018.courseservice.service.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/students")
public class StudentResource {

    static StudentService studentService = new StudentService();

    // .../students
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student addStudent(Student student) {
        return studentService.addStudent(student);
    }
    
  //.../student/1/register
  	@POST
  	@Path("/{studentId}/register")
  	@Produces(MediaType.APPLICATION_JSON)
  	@Consumes(MediaType.APPLICATION_JSON)
  	public String updatestudent(@PathParam("studentId") String studentId, 
  			String courseId) {
  		RegisterService service = new RegisterService();
  		service.register(studentId, courseId);
  		
  		return service.isRegistered ? "Registration success" : "Registration failed";
  	}

    // .../students/1
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudent(@PathParam("studentId") String id) {
        return studentService.getStudent(id);
    }

    // .../students/1
    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student deleteStudent(@PathParam("studentId") String id) {
        return studentService.deleteStudent(id);
    }

    // .../Students/1
    @PUT
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student updateStudent(@PathParam("studentId") String id, Student student) {
        return studentService.updateStudentInformation(id, student);
    }

}
