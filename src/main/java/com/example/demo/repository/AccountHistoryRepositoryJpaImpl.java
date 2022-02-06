package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@Repository
public class AccountHistoryRepositoryJpaImpl {

  @Autowired private EntityManager enitiyManager;

  private String SQL_FIND_HISTRY = "SELECT MAX(created) as timestamp, MAX(id) , max(amount) as amount FROM AccountHistory WHERE created >=  :startDate AND created <= :endDate GROUP BY  HOUR(created)";

  public HashMap<ZonedDateTime, BigDecimal> findHistory(ZonedDateTime startDate, ZonedDateTime endDate) throws Exception {

    try {
      Query q = enitiyManager.createQuery(SQL_FIND_HISTRY);
      q.setParameter("startDate", startDate);
      q.setParameter("endDate", endDate);

      List<Object[]> accountHistryList = (List<Object[]>)q.getResultList();

      HashMap<ZonedDateTime, BigDecimal> result = new HashMap<>();
      for (Object[] results : accountHistryList) {
        ZonedDateTime timestamp = (ZonedDateTime) results[0];
        BigDecimal amount = (BigDecimal)results[2];
        result.put(timestamp,amount);
      }

      return result;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

}
