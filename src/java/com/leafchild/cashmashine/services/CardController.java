package com.leafchild.cashmashine.services;

import com.leafchild.cashmashine.dto.card.CardDTO;
import com.leafchild.cashmashine.entity.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    CardDTO cardDto;

    private static final Logger logger =
        LoggerFactory.getLogger(CardController.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> findCardByID(@PathVariable(value = "id") Long id) {
        //Check ID
        //Check user
        Card card = cardDto.findCardByID(id);

        if(card == null) {
            logger.error("card with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(card, HttpStatus.OK);

    }

}
