package com.example.demo.repository;

import com.example.demo.orm.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends CrudRepository<Accounts, Long> {

//    @Query("FROM account ac WHERE id = :id")
//    Optional<Account> findById(@Param("id") Long id);
}
