package com.cloudcomputing.fall2018.courseservice.service;

import com.cloudcomputing.fall2018.courseservice.datamodel.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LectureService {
    static HashMap<Integer, Lecture> lec_Map = InMemoryDatabase.getLectureDB();

    //get all lectures
    public List<Lecture> getAllLectures(){
        List<Lecture> list = new ArrayList<>();
        list.addAll(lec_Map.values());
        return list;
    }

    //get all lectures of a course
//    public List<Lecture> getLecturesByCourse(String courseId){
//        Course course = CourseService.course_Map.get(courseId);
//        List<Lecture> list = new ArrayList<>();
//        List<Integer> lecListByCourse = course.getLectures();
//        for(int lecId : lecListByCourse){
//            list.add(lec_Map.get(lecId));
//        }
//        return list;
//    }

    // add a lecture
    public void addLecture(String notes, List<String> materials) {
        int id = lec_Map.size() + 1;
        Lecture lecture = new Lecture(id, notes, materials);
        lec_Map.put(id, lecture);
    }

    public Lecture addLecture(Lecture lecture) {
        int nextId = lec_Map.size() + 1;
        lecture.setId(nextId);
        lec_Map.put(nextId, lecture);
        return lecture;
    }

    // add a piece of material to lecture
    public List<String> addMaterailToLecture(int lecId, String content){
        Lecture lecture = lec_Map.get(lecId);
        lecture.getMaterials().add(content);
        lec_Map.put(lecId,lecture);
        return lecture.getMaterials();
    }

    //get a lecture
    public Lecture getLecture(int id){
        return lec_Map.get(id);
    }


    //delete a lecture
    public Lecture deleteLecture(int id) {
        Lecture lecture = lec_Map.get(id);
        lec_Map.remove(id);
        return lecture;
    }

    //update a lecture
    public Lecture updateLectureInformation(int id, Lecture lecture) {
        lecture.setId(id);
        lec_Map.put(id, lecture);
        return lecture;
    }
}
