package com.leafchild.cashmashine.dto.card;

import com.leafchild.cashmashine.entity.Card;

import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 16/06/16
 */

interface CardService {
    
    Card findCardByID( long id);
    
    Card saveCard( Card card);
    
    Card updateCard( Card card);
    
    Card deleteCard( Card card);
    
    List getAllCards();
    
    long count();
}
