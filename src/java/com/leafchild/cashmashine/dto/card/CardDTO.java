package com.leafchild.cashmashine.dto.card;

import com.leafchild.cashmashine.entity.Card;

import javax.annotation.Resource;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

public class CardDTO implements CardService {

    @Resource
    private CardRepository cardRepository;

    @Override
    public Card findCardByID( long id ){
        return cardRepository.findOne( id );
    }

    @Override
    public Card saveCard( Card card ){
        return cardRepository.save(card);
    }

    @Override
    public Card updateCard( Card card ){
        Card updCard = cardRepository.findOne(card.getCard_id());

        if (updCard == null)
            throw new NotFoundException();

        updCard.setCurrentBalance(card.getCurrentBalance());
        updCard.setLocked(card.isLocked());
        updCard.setTrasactions(card.getTrasactions());
        return updCard;
    }

    @Override
    public Card deleteCard( Card card ){
        Card found = cardRepository.findOne( card.getCard_id() );
        if(found != null) {
            cardRepository.delete(found);
            return found;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List getAllCards(){
        return cardRepository.findAll();
    }

    @Override
    public long count(){
        return cardRepository.count();
    }
}
