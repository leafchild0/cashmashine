package com.leafchild.cashmashine.dto.transaction;

import com.leafchild.cashmashine.entity.CardTransaction;
import com.leafchild.cashmashine.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 16/06/16
 */

interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {
}
