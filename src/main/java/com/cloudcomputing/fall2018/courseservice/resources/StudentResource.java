package com.cloudcomputing.fall2018.courseservice.resources;

import com.cloudcomputing.fall2018.courseservice.datamodel.Course;
import com.cloudcomputing.fall2018.courseservice.datamodel.Program;
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

    // .../Students/?program=is
    @GET
    @Path("/{programName}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudentsByProgram(@QueryParam("programName") String programName){
        return studentService.getStudentsByProgram(programName);
    }

    // .../Students/?courseId=cyse6150
    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getStudentsByCourse(@QueryParam("courseId") String courseId){
        return studentService.getStudentsByCourse(courseId);
    }

    // .../Students/?name=Jane
    @GET
    @Path("/{studentName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student getStudentsByName(@QueryParam("studentName") String studentName){
        return studentService.getStudent(studentName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addStudent(String name, Program program, String image, List<Course> courses) {
        studentService.addStudent(name, program, image, courses);
    }

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
    public Student getStudent(@PathParam("studentId") long id) {
        return studentService.getStudent(id);
    }

    // .../Students/1
    @DELETE
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student deleteStudent(@PathParam("studentId") long id) {
        return studentService.deleteStudent(id);
    }

    // .../Students/1
    @PUT
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student updateStudent(@PathParam("studentId") long id, Student student) {
        return studentService.updateStudentInformation(id, student);
    }

}
