package com.example.demo.services;

import com.example.demo.model.HistoryRequest;
import com.example.demo.model.HistoryResponse;
import com.example.demo.repository.AccountHistoryRepository;
import com.example.demo.repository.AccountHistoryRepositoryJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HistoryServices {
    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    @Autowired
    AccountHistoryRepositoryJpaImpl accountHistryRepositoryJpa;

    public List<HistoryResponse> doProcess(OffsetDateTime offsetStartDateTime,OffsetDateTime offsetEndDateTime) throws Exception {


        ZonedDateTime startDate = offsetStartDateTime.atZoneSameInstant(ZoneOffset.UTC);
        ZonedDateTime endDate = offsetEndDateTime.atZoneSameInstant(ZoneOffset.UTC);
        HashMap<ZonedDateTime, BigDecimal> accountHistryList = accountHistryRepositoryJpa.findHistory(startDate,endDate);
        List<HistoryResponse> historyResponseList = new ArrayList<>();

        accountHistryList.forEach((time,amount) ->{
            ZonedDateTime zonedDateTime = time.plusHours(1).truncatedTo(ChronoUnit.HOURS);
            historyResponseList.add(new HistoryResponse(zonedDateTime,amount));
        });

        return historyResponseList;
    }

}
