package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Student {
    String name;
    long id;
    String image;
    List<Course> enrolledCourses;
    Program program;

    public Student(){

    }

    public Student(String name, Program program) {
        this.name = name;
        this.program = program;
        this.image = null;
        this.enrolledCourses = new ArrayList<>();
    }

    public Student(String name, long id, String image, List<Course> enrolledCourses, Program program) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.enrolledCourses = enrolledCourses;
        this.program = program;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public Program getProgram() {
        return program;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
