package com.leafchild.cashmashine.models;

/**
 * Created by: leafchild
 * Project: CashMashineDemo
 * Date: 7/2/15
 * Time: 12:51 AM
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Transactions")
public class CardTransaction {

        @Id
        @GeneratedValue
        private Long cardTransaction_id;
        @NotNull
        private String cardTransactionCode;
        @Temporal(TemporalType.TIMESTAMP)
        private Date cardTransactionDate;
        private BigDecimal amount;

        public CardTransaction() {
        }

        public CardTransaction(String cardTransactionCode, Date cardTransactionDate, BigDecimal amount) {
                this.cardTransactionCode = cardTransactionCode;
                this.cardTransactionDate = cardTransactionDate;
                this.amount = amount;
        }

        @Override
        public String toString() {
                return "CardTransaction" + "{cardTransaction_id = " + cardTransaction_id + ", cardTransactionCode = '"
                               + cardTransactionCode + ", cardTransactionDate = '" + cardTransactionDate + ", amount = '" + amount + '}';
        }
}