package com.leafchild.cashmashine.entity;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 7/2/15
 * Time: 12:51 AM
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CARD_TRANSACTIONS")
public class CardTransaction implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long cardTransaction_id;
  @NotNull
  @Enumerated(EnumType.ORDINAL)
  private CardTransactionCode cardTransactionCode;
  @Temporal(TemporalType.TIMESTAMP)
  private Date cardTransactionDate;
  private BigDecimal amount;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id")
  private Card card;

  public CardTransaction() {}

  public CardTransaction(CardTransactionCode cardTransactionCode, Date cardTransactionDate) {

    this.cardTransactionCode = cardTransactionCode;
    this.cardTransactionDate = cardTransactionDate;
  }

  public Long getCardTransaction_id() {

    return cardTransaction_id;
  }

  public void setCardTransaction_id(Long cardTransaction_id) {

    this.cardTransaction_id = cardTransaction_id;
  }

  @Override
  public String toString() {

    return "CardTransaction" + "{cardTransaction_id = " + cardTransaction_id + ", cardTransactionCode = '"
               + cardTransactionCode + ", cardTransactionDate = '" + cardTransactionDate + ", amount = '" + amount + '}';
  }

  public CardTransactionCode getCardTransactionCode() {

    return cardTransactionCode;
  }

  public void setCardTransactionCode(CardTransactionCode cardTransactionCode) {

    this.cardTransactionCode = cardTransactionCode;
  }

  public Date getCardTransactionDate() {

    return cardTransactionDate;
  }

  public void setCardTransactionDate(Date cardTransactionDate) {

    this.cardTransactionDate = cardTransactionDate;
  }

  public BigDecimal getAmount() {

    return amount;
  }

  public void setAmount(BigDecimal amount) {

    this.amount = amount;
  }

  public Card getCard() {

    return card;
  }

  public void setCard(Card card) {

    this.card = card;
  }
}