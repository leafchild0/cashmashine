package com.leafchild.cashmashine.entity;

/**
 * Created by: leafchild
 * Project: CashMashineDemo
 * Date: 7/2/15
 * Time: 12:51 AM
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "CUSTOMER_CARDS")
public class Card implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private Long card_id;
  private BigDecimal currentBalance;
  private short pin;
  private boolean isLocked;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
  private List<CardTransaction> transactions;

  public Card() {}

  public Card(short pin, BigDecimal currentBalance) {

    this.pin = pin;
    this.currentBalance = currentBalance;
  }

  public BigDecimal getCurrentBalance() {

    return currentBalance;
  }

  public void setCurrentBalance(BigDecimal current_balance) {

    this.currentBalance = current_balance;
  }

  public short getPin() {

    return pin;
  }

  public void setPin(short pin) {

    this.pin = pin;
  }

  public boolean isLocked() {

    return isLocked;
  }

  public void setIsLocked(boolean isLocked) {

    this.isLocked = isLocked;
  }

  public Long getCard_id() {

    return card_id;
  }

  public void setCard_id(Long card_id) {

    this.card_id = card_id;
  }

  public void setLocked(boolean locked) {

    isLocked = locked;
  }

  @Override
  public String toString() {

    return "Card" + "{id=" + card_id + ", pin='" + pin + ", isLocked='" + isLocked + '}';
  }


  public Customer getCustomer() {

    return customer;
  }

  public void setCustomer(Customer customer) {

    this.customer = customer;
  }

  public List<CardTransaction> getTransactions() {

    return transactions;
  }

  public void setTransactions(List<CardTransaction> transactions) {

    this.transactions = transactions;
  }

  public boolean checkPin(short pinCode) {

    return pin == pinCode;
  }

}
