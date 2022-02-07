package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class HistoryRequest {

    @NotBlank
    @JsonProperty("startDate")
    //validate with pattern
    private String startDate;

    @NotBlank
    @JsonProperty("endDate")
    //validate with pattern
    private String endDate;
}