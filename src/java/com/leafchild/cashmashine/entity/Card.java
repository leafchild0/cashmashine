package com.leafchild.cashmashine.entity;

/**
 * Created by: leafchild
 * Project: CashMashineDemo
 * Date: 7/2/15
 * Time: 12:51 AM
 */

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table( name = "CUSTOMER_CARDS" )
public class Card {

    @Id
    @GeneratedValue
    private Integer card_id;
    private BigDecimal currentBalance;
    private short pin;
    private boolean isLocked;

    @OneToMany
    @JoinTable( name = "cards_transactions", joinColumns = @JoinColumn( name = "card_id" ),
        inverseJoinColumns = @JoinColumn( name = "transaction_id" ) )
    private List<CardTransaction> trasactions;

    public Card(){}

    public Card( short pin, BigDecimal currentBalance ){
        this.pin = pin;
        this.currentBalance = currentBalance;
    }

    public BigDecimal getCurrentBalance(){
        return currentBalance;
    }

    public void setCurrentBalance( BigDecimal current_balance ){
        this.currentBalance = current_balance;
    }

    public short getPin(){
        return pin;
    }

    public void setPin( short pin ){
        this.pin = pin;
    }

    public boolean isLocked(){
        return isLocked;
    }

    public void setIsLocked( boolean isLocked ){
        this.isLocked = isLocked;
    }

    public List<CardTransaction> getTrasactions(){
        return trasactions;
    }

    public void setTrasactions( List<CardTransaction> trasactions ){
        this.trasactions = trasactions;
    }

    public Integer getCard_id(){
        return card_id;
    }

    public void setCard_id( Integer card_id ){
        this.card_id = card_id;
    }

    public void setLocked( boolean locked ){
        isLocked = locked;
    }

    @Override
    public String toString(){
        return "Card" + "{id=" + card_id + ", pin='" + pin + ", isLocked='" + isLocked + '}';
    }
}
