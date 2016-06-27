package com.leafchild.cashmashine.dto.card;

import com.leafchild.cashmashine.entity.Card;
import com.leafchild.cashmashine.entity.CardTransaction;

import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 16/06/16
 */

public interface CardService {

  Card findCardByID( long id );
  List<Card> getAllCards();
  void save( Card card );
  boolean exists( long id);

}
