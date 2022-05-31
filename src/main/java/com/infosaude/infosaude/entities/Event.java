package com.infosaude.infosaude.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private String id;
    private String studentId;
    private String type;
    private String description;
    private String date;
}
