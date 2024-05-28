package com.baseball.dto;

import lombok.Data;

@Data
public class Player {
    
    protected String fName,lName;
    protected int age;
    protected int playerId;
    protected int team;
}
