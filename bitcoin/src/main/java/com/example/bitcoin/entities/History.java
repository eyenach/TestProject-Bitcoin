package com.example.bitcoin.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class History {

    @NotNull @NotEmpty
    private String startDatetime;
    @NotNull @NotEmpty
    private String endDatetime;

    public String getStartDatetime() {
        return startDatetime;
    }
    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }
    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

}
