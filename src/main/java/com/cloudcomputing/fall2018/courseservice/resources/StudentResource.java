package com.cloudcomputing.fall2018.courseservice.resources;

import com.cloudcomputing.fall2018.courseservice.datamodel.Student;
import com.cloudcomputing.fall2018.courseservice.service.StudentService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/students")
public class StudentResource {

    static StudentService studentService = new StudentService();

    // .../Students
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    
     //.../Students/byCourse/?courseId=cyse6150
//    @GET
//    @Path("/byCourse/{courseId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Student> getStudentsByCourse(@QueryParam("courseId") String courseId){
//        return studentService.getStudentsByCourse(courseId);
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student addStudent(Student student) {
        return studentService.addStudent(student);
    }

    // .../Students/1
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudent(@PathParam("studentId") String id) {
        return studentService.getStudent(id);
    }

    // .../Students/1
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
