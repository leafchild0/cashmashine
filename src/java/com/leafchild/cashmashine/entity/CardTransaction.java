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
@Table( name = "CUSTOMER_TRANSACTIONS" )
public class CardTransaction implements Serializable {

    @Id
    @GeneratedValue
    private Integer cardTransaction_id;
    @NotNull
    private String cardTransactionCode;
    @Temporal( TemporalType.TIMESTAMP )
    private Date cardTransactionDate;
    private BigDecimal amount;

    public CardTransaction(){}

    public CardTransaction( String cardTransactionCode, Date cardTransactionDate, BigDecimal amount ){
        this.cardTransactionCode = cardTransactionCode;
        this.cardTransactionDate = cardTransactionDate;
        this.amount = amount;
    }

    public Integer getCardTransaction_id(){

        return cardTransaction_id;
    }

    public void setCardTransaction_id( Integer cardTransaction_id ){
        this.cardTransaction_id = cardTransaction_id;
    }

    @Override
    public String toString(){
        return "CardTransaction" + "{cardTransaction_id = " + cardTransaction_id + ", cardTransactionCode = '"
                   + cardTransactionCode + ", cardTransactionDate = '" + cardTransactionDate + ", amount = '" + amount + '}';
    }
}