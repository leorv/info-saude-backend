package com.infosaude.infosaude.entities;

// import lombok.Getter;
// import lombok.Setter;

// @Getter
// @Setter
public class VaccineTaken {
    private String id;
    private String vaccineId;
    private String studentId;
    private String name;
    private String description;
    private String date;    

    public VaccineTaken() {

    }

    public VaccineTaken (String id, String vaccineId, String studentId, String name, String description, String date) {
        this.id = id;
        this.vaccineId = vaccineId;
        this.studentId = studentId;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public String getVaccineId() {
        return this.vaccineId;
    }
    
    public String getStudentId() {
        return this.studentId;
    }
    
    public String getName() {
        return this.name;
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

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
