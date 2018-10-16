package com.cloudcomputing.fall2018.courseservice.datamodel;

import java.util.HashMap;
import java.util.Map;

public class Lecture {
    int id;
    String notes;
    Map<Integer, String> materials;

    public Lecture(){
        this.notes = null;
        this.materials = new HashMap<>();
    }

    public Lecture(int id, String notes, Map<Integer, String> materials) {
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

    public Map<Integer, String> getMaterials() {
        return materials;
    }

    public void setMaterials(Map<Integer, String> materials) {
        this.materials = materials;
    }
}
