package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Student {
    String name;
    long id;
    String image;
    List<String> enrolledCourses;
    String programName;

    public Student(){

    }

    public Student(String name, long id, String image, List<String> enrolledCourses, String programName) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.enrolledCourses = enrolledCourses;
        this.programName = programName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<String> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
