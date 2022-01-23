package com.example.bitcoin.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class Bitcoin {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotNull @NotEmpty
    private String datetime;
    @NotNull @Min(1)
    private float amount;

    public Bitcoin() {}

    public Bitcoin(String datetime, float amount) {
        this.datetime = datetime;
        this.amount = amount;
    }

    public Bitcoin(Long id, String datetime, float amount) {
        this.id = id;
        this.datetime = datetime;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDatetime() {
        return datetime;
    }
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    
}
