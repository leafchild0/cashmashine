package com.leafchild.cashmashine.dto.card;

import com.leafchild.cashmashine.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 16/06/16
 */

interface CardRepository extends JpaRepository<Card, Long> {
}