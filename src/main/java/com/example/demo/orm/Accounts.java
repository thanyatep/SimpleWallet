package com.example.demo.orm;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Accounts implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(
    name = "ACCOUNT_ID_GENERATOR",
    sequenceName = "ACCOUNT_PK_SEQ",
    allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_ID_GENERATOR")
  private Long id;

  private BigDecimal amount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
