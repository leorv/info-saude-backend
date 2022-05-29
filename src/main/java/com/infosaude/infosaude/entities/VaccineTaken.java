package com.infosaude.infosaude.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineTaken {
    private int id;
    private int vaccineId;
    private int studentId;
    private String name;
    private String description;
    private String date;    
}
