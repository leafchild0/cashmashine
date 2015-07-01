package com.leafchild.cashmashine.models;

/**
 * Created by: leafchild
 * Project: CashMashineDemo
 * Date: 7/2/15
 * Time: 12:50 AM
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long customer_id;
    @NotNull
    private String customerName;
    @OneToMany
    @JoinTable(name = "jnd_customer_cards", joinColumns = @JoinColumn(name = "customer_fk"), inverseJoinColumns = @JoinColumn(name = "card_fk"))
    private List<Card> cards;

    public Customer() {
    }

    public Customer(String customerName) {
        this.customerName = customerName;
    }

    public Long getCustomer_id() {
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
        final StringBuilder sb = new StringBuilder();
        sb.append("Customer");
        sb.append("{id = ").append(customer_id);
        sb.append(", customer_name = '").append(customerName);
        sb.append('}');
        return sb.toString();
    }
}