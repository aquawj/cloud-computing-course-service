package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Course {
    String name;
    String id;
    List<Lecture> lectures;
    String board;
    String roster;
    List<Student> students;
    Student ta;
    Professor professor;

    public Course(String name, String id, String board, String roster, Student ta, Professor professor) {
        this.name = name;
        this.id = id;
        this.board = board;
        this.roster = roster;
        this.ta = ta;
        this.professor = professor;
        this.lectures = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public Course(String name, String id, List<Lecture> lectures, String board, String roster, List<Student> students, Student ta, Professor professor) {
        this.name = name;
        this.id = id;
        this.lectures = lectures;
        this.board = board;
        this.roster = roster;
        this.students = students;
        this.ta = ta;
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getRoster() {
        return roster;
    }

    public void setRoster(String roster) {
        this.roster = roster;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getTa() {
        return ta;
    }

    public void setTa(Student ta) {
        this.ta = ta;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
