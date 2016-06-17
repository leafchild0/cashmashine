package com.leafchild.cashmashine.services;

import com.leafchild.cashmashine.dto.card.CardService;
import com.leafchild.cashmashine.entity.Card;
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
public class CardController {

    @Autowired
    CardService cardService;

    private static final Logger logger =
        LoggerFactory.getLogger( CardController.class );

    @RequestMapping( value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Card>> showUsers(){
        List<Card> cards = cardService.getAllCards();
        if( cards.isEmpty() ) return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        return new ResponseEntity<>( cards, HttpStatus.OK );
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Card> findCardByID( @PathVariable( value = "id" ) Long id ){
        //Check ID
        //Check card
        Card card = cardService.findCardByID( id );

        if( card == null ){
            logger.error( "card with id " + id + " not found" );
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }

        return new ResponseEntity<>( card, HttpStatus.OK );
    }

    @RequestMapping( value = "/", method = RequestMethod.POST )
    public ResponseEntity<Card> createUser( @RequestBody Card card ){
        //Check required params
        //Create card
        cardService.saveCard( card );
        //Check on errors if need
        //Return results
        return new ResponseEntity<>( card, HttpStatus.CREATED );
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    public ResponseEntity<Card> updateUser( @PathVariable( "id" ) long id, @RequestBody Card card ){

        Card foundCard = cardService.findCardByID( id );

        if( foundCard == null ){
            logger.error( "Requested card " + id + " not found" );
            return new ResponseEntity<>( card, HttpStatus.NOT_FOUND );
        }
        foundCard.setCurrentBalance( card.getCurrentBalance() );
        foundCard.setLocked( card.isLocked() );

        //Update card
        card = cardService.updateCard( card );
        //Check on errors
        //Return results
        return new ResponseEntity<>( card, HttpStatus.OK );
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    public ResponseEntity<Void> deleteUser( @PathVariable( "id" ) long id ){

        Card foundCard = cardService.findCardByID( id );

        if( foundCard == null ){
            logger.error( "Requested card " + id + " not found" );
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }

        //Delete card
        cardService.deleteCard( foundCard );
        //Check on errors
        //Return results
        return new ResponseEntity<>( HttpStatus.OK );
    }

}
