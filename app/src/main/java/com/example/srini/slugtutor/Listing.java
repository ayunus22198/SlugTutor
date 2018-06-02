package com.example.srini.slugtutor;

public class Listing {

    private String id;
    private String name;
    private Course course;

    public Listing() {}

    public Listing(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Listing(String id, String name, Course course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public String toString() {
        return "id: " + id + ", name: " + name + ", course: " + course;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
