package com.infosaude.infosaude.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaccineTaken {
    private String id;
    private String vaccineId;
    private String studentId;
    private String name;
    private String description;
    private String date;    
}
