package com.leafchild.cashmashine.entity;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 7/2/15
 * Time: 12:50 AM
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

        @Id
        @GeneratedValue
        private Integer customer_id;
        @NotNull
        private String customerName;
        @OneToMany
        @JoinTable(name = "customer_cards", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "card_id"))
        private List<Card> cards;

        public Customer() {
        }

        public Customer(String customerName) {
                this.customerName = customerName;
        }

        public Integer getCustomer_id() {
                return customer_id;
        }

        public String getCustomerName() {
                return customerName;
        }

        public void setCustomerName(String customer_name) {
                this.customerName = customer_name;
        }

        public List<Card> getCards() {
                return cards;
        }

        public void setCards(List<Card> cards) {
                this.cards = cards;
        }

        @Override
        public String toString() {
                return "Customer" + "{id = " + customer_id + ", customer_name = '" + customerName + '}';
        }
}