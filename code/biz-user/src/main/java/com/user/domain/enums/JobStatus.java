package com.user.domain.enums;

import java.io.Serializable;

public enum JobStatus implements Serializable{
    UNDISTRIBUTED("undistributed"),
    ON_HOLIDAY("on_holiday"),
    PART_TIME_JOB("part_time"),
    FULL_TIME_JOB("full_time"),
    RETIRED("retired"),
    DISMISSED("dismissed"),
    ;
    private String name;
    private static final long serialVersionUID = 1L;

    JobStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
