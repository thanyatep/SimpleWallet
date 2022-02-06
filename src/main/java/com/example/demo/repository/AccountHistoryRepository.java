package com.example.demo.repository;

import com.example.demo.orm.AccountHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountHistoryRepository extends CrudRepository<AccountHistory, Long> {

}
