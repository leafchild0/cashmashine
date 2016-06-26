package com.leafchild.cashmashine.dto.card;

import com.leafchild.cashmashine.entity.Card;
import com.leafchild.cashmashine.entity.CardTransaction;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

@Repository
@Transactional
public class CardDTO implements CardService {

  @Resource
  private CardRepository cardRepository;

  @Override
  public Card findCardByID( long id ){

    return cardRepository.findOne(id);
  }

  @Override
  public List<Card> getAllCards(){

    return cardRepository.findAll();
  }

  @Override
  public void save( Card card ){
    cardRepository.save(card);

  }

}
