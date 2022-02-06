package com.example.demo.orm;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "accountHistory")
public class AccountHistory implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(
    name = "ACCOUNT_HISTORY_ID_GENERATOR",
    sequenceName = "ACCOUNT_HISTORY_PK_SEQ",
    allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_HISTORY_ID_GENERATOR")
  private Long id;

  private ZonedDateTime created;
  private BigDecimal amount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ZonedDateTime getCreated() {
    return created;
  }

  public void setCreated(ZonedDateTime created) {
    this.created = created;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
