package com.infosaude.infosaude.entities;

// import lombok.Getter;
// import lombok.Setter;

// @Getter
// @Setter
public class Vaccine {
    private String id;
    private String name;

    public Vaccine() {

    }

    public Vaccine (String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
