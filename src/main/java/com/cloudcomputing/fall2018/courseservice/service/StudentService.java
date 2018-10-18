package com.cloudcomputing.fall2018.courseservice.service;

import com.cloudcomputing.fall2018.courseservice.datamodel.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentService {

    static HashMap<Long, Student> student_Map = InMemoryDatabase.getStudentDB();

    //get all students
    public List<Student> getAllStudents(){
        List<Student> list = new ArrayList<>();
        list.addAll(student_Map.values());
        return list;
    }

    //get students in a program
//    public List<Student> getStudentsByProgram(String programName) {
//        ArrayList<Student> list = new ArrayList<>();
//        for (Student stu : student_Map.values()) {
//            if (stu.getProgramName().equals(programName)) {
//                list.add(stu);
//            }
//        }
//        return list ;
//    }

    //get students enrolled in a course
    public List<Student> getStudentsByCourse(String courseId){
        List<Student> studentList = new ArrayList<>();
        if(CourseService.course_Map.containsKey(courseId)){
            Course course = CourseService.course_Map.get(courseId);
            for(long id : course.getStudents()){
                studentList.add(student_Map.get(id));
            }
        }
        return studentList;
    }

    public Student addStudent(Student student) {
        long nextAvailableId = student_Map.size() + 1;
        student.setId(nextAvailableId);
        student_Map.put(nextAvailableId, student);
        return student_Map.get(nextAvailableId);
    }

    // get a student by ID
    public Student getStudent(long id) {

        return student_Map.get(id);
    }

    // get a student by name
//    public Student getStudent(String name) {
//        for(long id : student_Map.keySet()){
//            if(student_Map.get(id).getName().equals(name)){
//                return student_Map.get(id);
//            }
//        }
//        return null;
//    }

    //delete a student
    public Student deleteStudent(Long id) {
        Student deletedStudent = student_Map.get(id);
        student_Map.remove(id);
        return deletedStudent;
    }

    //update a student
    public Student updateStudentInformation(long id, Student student) {
        student.setId(id);
        student_Map.put(id, student) ;
        return student;
    }

}
