package com.infosaude.infosaude.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student {
    private String id;
    private String name;
    private String birthDate;
    private String grade;
    private String gender;
    private double cpf;
}
