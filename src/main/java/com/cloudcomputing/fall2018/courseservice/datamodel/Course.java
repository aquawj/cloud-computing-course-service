package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Course {
    String name;
    String id;
    List<Integer> lectures;
    String board;
    List<Long> roster;
    List<Long> students;
    Long taId;
    Long professorId;

    public Course(){

    }

    public Course(String name, String id, List<Integer> lectures, String board, List<Long> roster, List<Long> students, Long taId, Long professorId) {
        this.name = name;
        this.id = id;
        this.lectures = lectures;
        this.board = board;
        this.roster = roster;
        this.students = students;
        this.taId = taId;
        this.professorId = professorId;
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

    public List<Integer> getLectures() {
        return lectures;
    }

    public void setLectures(List<Integer> lectures) {
        this.lectures = lectures;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public List<Long> getRoster() {
        return roster;
    }

    public void setRoster(List<Long> roster) {
        this.roster = roster;
    }

    public List<Long> getStudents() {
        return students;
    }

    public void setStudents(List<Long> students) {
        this.students = students;
    }

    public Long getTaId() {
        return taId;
    }

    public void setTaId(Long taId) {
        this.taId = taId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }
}
