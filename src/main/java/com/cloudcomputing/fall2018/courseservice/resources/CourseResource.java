package com.cloudcomputing.fall2018.courseservice.resources;

import com.cloudcomputing.fall2018.courseservice.datamodel.*;
import com.cloudcomputing.fall2018.courseservice.service.CourseService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/courses")
public class CourseResource {
    private CourseService courseService = new CourseService();

    // .../courses
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCoursesByProgram(@QueryParam("program") Program program){
        return courseService.getCoursesByProgram(program);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCourse(String name, String id, String board, String roster, Student ta, Professor professor) {
        courseService.addCourse(name, id, board, roster, ta, professor);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCourse(Course course) {
        courseService.addCourse(course);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addLectureToCourse(String courseId, Lecture lecture){
        courseService.addLectureToCourse(courseId, lecture);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addStudentToCourse(String courseId, Student student){
        courseService.addStudentToCourse(courseId, student);
    }

    // .../courses/cyse6150
    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course getCourse(@PathParam("courseId") String courseId) {
        return courseService.getCourse(courseId);
    }

    // .../courses/cyse6150
    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course deleteCourse(@PathParam("courseId") String courseId) {
        return courseService.deleteCourse(courseId);
    }

    // .../courses/cyse6150/?studentId=1
    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student deleteStudentInCourse(@PathParam("courseId") String courseId, @QueryParam("studentId") long studentId) {
        return courseService.deleteStudentInCourse(courseId,studentId);
    }

    // .../courses/cyse6150/?lecId=2
    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture deleteLectureInCourse(@PathParam("courseId") String courseId, @QueryParam("lecId") int lecId) {
        return courseService.deleteLectureInCourse(courseId, lecId);
    }

    // .../courses/cyse6150
    @PUT
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Course updateCourse(@PathParam("courseId") String courseId, Course course) {
        return courseService.updateCourseInformation(courseId, course);
    }
}
