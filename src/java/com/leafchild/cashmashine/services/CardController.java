package com.leafchild.cashmashine.services;

import com.leafchild.cashmashine.dto.card.CardService;
import com.leafchild.cashmashine.entity.Card;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

@RestController
@RequestMapping( "/api/cards" )
class CardController {

  @Autowired
  private CardService cardService;

  private static final Logger logger =
      LoggerFactory.getLogger(CardController.class);

  @RequestMapping( value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
  public ResponseEntity<List<Card>> findCards(){

    List<Card> cards = cardService.getAllCards();
    if( cards.isEmpty() ) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    return new ResponseEntity<>(cards, HttpStatus.OK);
  }

  @RequestMapping( value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
  public ResponseEntity<Boolean> findCardByID( @PathVariable( value = "id" ) Long id ){
    //Check ID
    //Check card
    Card card = cardService.findCardByID(id);

    if( card == null ){
      logger.error("card with id " + id + " not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(!card.isLocked(), HttpStatus.OK);
  }

  @RequestMapping( value = "/{id}/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
  public ResponseEntity<Card> getCardByID( @PathVariable( value = "id" ) Long id ){
    //Check ID
    //Check card
    Card card = cardService.findCardByID(id);

    if( card == null ){
      logger.error("card with id " + id + " not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(card, HttpStatus.OK);
  }

  @RequestMapping( value = "/pincode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
  public ResponseEntity<String> checkPinCode( @RequestBody String creds ){

    JSONObject obj   = new JSONObject(creds);
    Card       found = cardService.findCardByID(obj.getLong("id"));

    if( found != null ){
      //check whether its locked
      if(obj.getBoolean("isLocked")) {
        //Save it, send message to block in UI
        found.setLocked(true);
        cardService.save(found);
        return new ResponseEntity<>(JSONObject.quote("Your card has been locked"), HttpStatus.LOCKED);
      }

      //check pin code
      if( found.getPin() == obj.getInt("pinCode") ){
        //Sussess case
        return new ResponseEntity<>(JSONObject.quote("Pin is correct"), HttpStatus.OK);
      } else { return new ResponseEntity<>(JSONObject.quote("Pin do not match"), HttpStatus.BAD_REQUEST); }
    }
    //Return results
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
