package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
public class HistoryResponse {

    @JsonProperty("datetime")
    private ZonedDateTime datetime;

    @JsonProperty("amount")
    private BigDecimal amount;

    public HistoryResponse(ZonedDateTime datetime, BigDecimal amount) {
        this.datetime = datetime;
        this.amount = amount;
    }
}