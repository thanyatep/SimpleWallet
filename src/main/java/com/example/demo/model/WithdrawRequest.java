package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawRequest {

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("datetime")
    private String datetime;
}