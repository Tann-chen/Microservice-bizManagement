package com.user.domain.enums;

public enum JobStatus {
    UNDISTRIBUTED("undistributed"),
    ON_HOLIDAY("on_holiday"),
    PART_TIME_JOB("part_time"),
    FULL_TIME_JOB("full_time"),
    RETIRED("retired"),
    DISMISSED("dismissed"),
    ;

    private String name;

    JobStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
