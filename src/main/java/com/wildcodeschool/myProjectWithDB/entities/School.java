package com.wildcodeschool.myProjectWithDB.entities;

public class School {
    private int id;
    private String name;
    private int capacity;
    private String coutry;

    public School(int id, String name, int capacity, String coutry) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.coutry = coutry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCoutry() {
        return coutry;
    }

    public void setCoutry(String coutry) {
        this.coutry = coutry;
    }
}
