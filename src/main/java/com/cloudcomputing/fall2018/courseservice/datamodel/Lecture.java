package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Lecture {
    int id;
    String notes;
    List<String> materials;

    public Lecture(){
        this.notes = null;
        this.materials = new ArrayList<>();
    }

    public Lecture(int id, String notes, List<String> materials) {
        this.id = id;
        this.notes = notes;
        this.materials = materials;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }
}
