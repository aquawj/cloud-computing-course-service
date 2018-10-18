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
    public List<Course> getCoursesByProgram(@QueryParam("program") Program program){
        if(program == null){
            return courseService.getAllCourses();
        }else{
            return courseService.getCoursesByProgram(program);
        }
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void addCourse(String name, String id, String board, String roster, Student ta, Professor professor) {
//        courseService.addCourse(name, id, board, roster, ta, professor);
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Course addCourse(Course course) {
        return courseService.addCourse(course);
    }

//     //.../courses/addLecture
//    @POST
//    @Path("/addLecture")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Lecture addLectureToCourse(String courseId, Lecture lecture){
//        return courseService.addLectureToCourse(courseId, lecture);
//    }
//
//     //.../courses/addStudent
//    @POST
//    @Path("/addStudent")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Student addStudentToCourse(String courseId, Student student){
//        return courseService.addStudentToCourse(courseId, student);
//    }

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

    // .../courses/cyse6150/student/?studentId=1
    @DELETE
    @Path("/{courseId}/student")
    @Produces(MediaType.APPLICATION_JSON)
    public Student deleteStudentInCourse(@PathParam("courseId") String courseId, @QueryParam("studentId") long studentId) {
        return courseService.deleteStudentInCourse(courseId,studentId);
    }

    // .../courses/cyse6150/lecture/?lecId=2
    @DELETE
    @Path("/{courseId}/lecture")
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
