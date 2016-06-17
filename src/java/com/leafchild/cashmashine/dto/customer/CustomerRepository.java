package com.leafchild.cashmashine.dto.customer;

import com.leafchild.cashmashine.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 16/06/16
 */

interface CustomerRepository extends JpaRepository<Customer, Long> {
}
