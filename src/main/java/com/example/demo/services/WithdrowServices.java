package com.example.demo.services;

import com.example.demo.model.WithdrawRequest;
import com.example.demo.orm.AccountHistory;
import com.example.demo.orm.Accounts;
import com.example.demo.repository.AccountHistoryRepository;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class WithdrowServices {
    @Autowired
    AccountHistoryRepository accountHistoryRepository;

    @Autowired
    AccountRepository accountRepository;
    public void doProcess(WithdrawRequest request) throws Exception {

        //If you want to many TPS need to lock row account
        Optional<Accounts> account = accountRepository.findById(1L); //support 1 account

        //prepare amount
        BigDecimal newAmount = account.get().getAmount().add(new BigDecimal(request.getAmount()));
        account.get().setAmount(newAmount);

        //prepare time
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(request.getDatetime());
        ZonedDateTime zonedDateTime = offsetDateTime.atZoneSameInstant(ZoneOffset.UTC);

        //update DB
        AccountHistory accountHistry = new AccountHistory();
        accountHistry.setAmount(newAmount);
        accountHistry.setCreated(zonedDateTime);
        accountRepository.save(account.get());
        accountHistoryRepository.save(accountHistry);

    }

}
