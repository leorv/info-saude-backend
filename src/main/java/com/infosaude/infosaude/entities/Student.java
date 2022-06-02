package com.infosaude.infosaude.entities;

// import lombok.Getter;
// import lombok.Setter;

// @Setter
// @Getter
public class Student {
    private String id;
    private String name;
    private String birthDate;
    private String grade;
    private String gender;
    private double cpf;

    public Student() {
    }

    public Student(String id, String name, String birthDate, String grade, String gender, double cpf) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.grade = grade;
        this.gender = gender;
        this.cpf = cpf;
    }

    public String getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String getGrade() {
        return this.grade;
    }

    public String getGender() {
        return this.gender;
    }

    public double getCpf() {
        return this.cpf;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCpf(double cpf) {
        this.cpf = cpf;
    }


}
