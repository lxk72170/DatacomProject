package com.ucm.myproject.config.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {

    @JsonProperty("UserName")
    private String userName;

    @JsonProperty("Age")
    private int age;

    @JsonProperty("AnnualSalary")
    private long annualSalary;

}
