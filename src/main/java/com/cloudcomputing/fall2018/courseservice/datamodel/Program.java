package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Program {
    String name;
    List<String> courses;

    public Program(){

    }

    public Program(String name) {
        this.name = name;
        courses = new ArrayList<>();
    }

    public Program(String name, List<String> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
