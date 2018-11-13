package com.cloudcomputing.fall2018.courseservice.resources;

import com.cloudcomputing.fall2018.courseservice.datamodel.*;
import com.cloudcomputing.fall2018.courseservice.service.CourseService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("courses")
public class CourseResource {
    private CourseService courseService = new CourseService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(){
        return courseService.getAllCourses();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course addCourse(Course course) {
        return courseService.addCourse(course);
    }


     //.../courses/addStudent
//    @POST
//    @Path("/addStudent")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Course addStudentToCourse(String courseId, String studentId){
//        return courseService.addStudentToCourse(courseId, studentId);
//    }

    // .../courses/cyse6150
    @GET
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourse(@PathParam("courseId") String courseId) {
        return courseService.getCourse(courseId);
    }

    // .../courses/cyse6150
    @DELETE
    @Path("/{courseId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Course deleteCourse(@PathParam("courseId") String courseId) {
        return courseService.deleteCourse(courseId);
    }

    // .../courses/cyse6150/student/?studentId=1
    @DELETE
    @Path("/{courseId}/student")
    @Produces(MediaType.APPLICATION_JSON)
    public Course deleteStudentInCourse(@PathParam("courseId") String courseId, @QueryParam("studentId") String studentId) {
        return courseService.deleteStudentInCourse(courseId,studentId);
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
