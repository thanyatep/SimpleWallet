package com.example.demo;

import com.example.demo.model.HistoryRequest;
import com.example.demo.model.HistoryResponse;
import com.example.demo.model.WithdrawRequest;
import com.example.demo.services.HistoryServices;
import com.example.demo.services.WithdrowServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UpdateAccountController {

  @Autowired
  WithdrowServices withdrowServices;

  @Autowired
  HistoryServices historyServices;

  @PostMapping(value = "/account/withdraw", name = "accountWithdraw")
  public ResponseEntity withdraw(
      @Validated @RequestBody WithdrawRequest withdrawRequest) throws Exception {

    withdrowServices.doProcess(withdrawRequest);

    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping(value = "/account/history", name = "history")
  public List<HistoryResponse> history(
          @RequestBody HistoryRequest historyRequest) throws Exception {

    return historyServices.doProcess(historyRequest);
  }

}
