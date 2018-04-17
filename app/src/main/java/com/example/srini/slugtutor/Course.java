package com.example.srini.slugtutor;

public class Course {
    private String courseNum;
    private String professor;
    private String description;

    public Course(String courseNum, String professor, String description) {
        this.courseNum = courseNum;
        this.professor = professor;
        this.description = description;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public String getDescription() {
        return description;
    }

    public String getProfessor() {
        return professor;
    }

    public String toString() {
        return "Course: " + courseNum + ", Professor: " + professor + ", Description: " + description;
    }


}
