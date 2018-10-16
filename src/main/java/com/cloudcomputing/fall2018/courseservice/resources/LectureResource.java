package com.cloudcomputing.fall2018.courseservice.resources;

import com.cloudcomputing.fall2018.courseservice.datamodel.*;
import com.cloudcomputing.fall2018.courseservice.service.LectureService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/lectures")
public class LectureResource {
    private LectureService lectureService = new LectureService();

    // .../lectures
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lecture> getAllLectures(){
        return lectureService.getAllLectures();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lecture> getLecturesByCourse(@QueryParam("courseId") String courseId){
        return lectureService.getLecturesByCourse(courseId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addLecture(String notes, Map<Integer, String> materials){
        lectureService.addLecture(notes, materials);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addLecture(Lecture lecture) {
        lectureService.addLecture(lecture);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addMaterialToLecture(int lecId, String content){
        lectureService.addMaterailToLecture(lecId, content);
    }

    // .../lectures/1
    @GET
    @Path("/{lectureId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture getLecture(@PathParam("lectureId") int lectureId) {
        return lectureService.getLecture(lectureId);
    }

    // .../lectures/1
    @DELETE
    @Path("/{lectureId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Lecture deleteLecture(@PathParam("lectureId") int lectureId) {
        return lectureService.deleteLecture(lectureId);
    }

    // .../lectures/1
    @PUT
    @Path("/{lectureId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Lecture updateCourse(@PathParam("lectureId") int id, Lecture lecture) {
        return lectureService.updateLectureInformation(id, lecture);
    }
}
