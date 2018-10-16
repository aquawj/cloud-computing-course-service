package com.cloudcomputing.fall2018.courseservice.service;

import com.cloudcomputing.fall2018.courseservice.datamodel.Course;
import com.cloudcomputing.fall2018.courseservice.datamodel.InMemoryDatabase;
import com.cloudcomputing.fall2018.courseservice.datamodel.Program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProgramService {
    private HashMap<String, Program> pro_Map = InMemoryDatabase.getProgramDB();

    //get all programs
    public List<Program> getAllPrograms(){
        List<Program> list = new ArrayList<>();
        list.addAll(pro_Map.values());
        return list;
    }

    // add a program
    public void addProgram(String name, List<Course> courses) {
        Program program = new Program(name, courses);
        pro_Map.put(name, program);
    }

    public Program addProgram(Program program) {
        String name = program.getName();
        pro_Map.put(name, program);
        return program;
    }

    // add a course to program
    public void addCourseToProgram(String courseId, String programName){
        Course course = CourseService.course_Map.get(courseId);
        pro_Map.get(programName).getCourses().add(course);
    }

    // get a program by name
    public Program getProgram(String name) {
        return pro_Map.get(name);
    }

    //delete a program
    public Program deleteProgram(String name) {
        Program deletedProgram = pro_Map.get(name);
        pro_Map.remove(name);
        return deletedProgram;
    }

    //delete a course in a program
    public Program deleteCourseInProgram(String courseId, String programName) {
        Program program = pro_Map.get(programName);
        Course course = CourseService.course_Map.get(courseId);
        program.getCourses().remove(course);
        pro_Map.put(programName, program);
        return program;
    }

    //update a program
    public Program updateProgramInformation(String name, Program program) {
        program.setName(name);
        pro_Map.put(name, program);
        return program;
    }
}
