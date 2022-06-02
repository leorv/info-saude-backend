package com.infosaude.infosaude.entities;

// import lombok.Getter;
// import lombok.Setter;

// @Getter
// @Setter
public class Event {
    private String id;
    private String studentId;
    private String type;
    private String description;
    private String date;

    public Event () {

    }

    public Event (String id, String studentId, String type, String description, String date){
        this.id = id;
        this.studentId = studentId;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    public String getId(){
        return this.id;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
