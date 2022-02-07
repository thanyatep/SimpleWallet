package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WithdrawRequest {

    @NotBlank
    @JsonProperty("amount")
    private String amount;

    @NotBlank
    @JsonProperty("datetime")
    private String datetime;
}