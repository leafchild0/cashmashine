package com.leafchild.cashmashine.dto.card;

import com.leafchild.cashmashine.entity.Card;

/**
 * Created by: vmalyshev
 * Date: 22/06/16
 * TIME: 13:41
 */
public abstract class CustomCRUDRepository implements CardRepository {

    @Override
    public Card findByCardName(String cardName) {

        return null;
    }
}
