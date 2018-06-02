package com.example.srini.slugtutor;

public class Listing {

    private String id;
    private String name;
    private String description;

    public Listing() {}

    public Listing(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "id: " + id + ", name: " + name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
