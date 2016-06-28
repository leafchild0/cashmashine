package com.leafchild.cashmashine.services;

import com.leafchild.cashmashine.dto.card.CardService;
import com.leafchild.cashmashine.dto.transaction.CardTransactionService;
import com.leafchild.cashmashine.entity.Card;
import com.leafchild.cashmashine.entity.CardTransaction;
import com.leafchild.cashmashine.entity.CardTransactionCode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

@RestController
@RequestMapping("/api/cards")
class MainController {

  @Autowired
  private CardService cardService;
  @Autowired
  private CardTransactionService transactionService;

  private static final Logger logger =
      LoggerFactory.getLogger(MainController.class);


  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> findCardByID(@PathVariable(value = "id") Long id) {

    logger.debug("Looking for a card " + id);
    //Check card
    boolean isExists = cardService.exists(id);

    if (!isExists) {
      logger.error("card with id " + id + " not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Card> getCardForBalance(@PathVariable(value = "id") Long id) {

    logger.debug("Checking balance for card " + id);
    //Check card
    Card card = cardService.findCardByID(id);

    if (card == null) {
      logger.error("card with id " + id + " not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //Add balance transaction
    CardTransaction transaction = new CardTransaction(CardTransactionCode.BALANCE, new Date());
    transaction.setCard(card);
    card.getTransactions().add(transaction);
    transactionService.saveTransaction(transaction);
    cardService.save(card);

    logger.debug("Balance transactions was saved " + transaction);

    return new ResponseEntity<>(card, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}/withdraw", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Card> makeWithdraw(@PathVariable(value = "id") Long id, @RequestBody String amount) {

    logger.debug("Trying to withdraw from a card " + id);
    //Check card
    Card card = cardService.findCardByID(id);

    if (card == null) {
      logger.error("card with id " + id + " not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //Try to make an withdraw
    JSONObject obj = new JSONObject(amount);
    long amountN = obj.getLong("amount");
    long currentA = card.getCurrentBalance().longValue();
    if (currentA - amountN > 0) {
      //Add balance transaction
      CardTransaction transaction = new CardTransaction(CardTransactionCode.WITHDRAW, new Date());
      transaction.setCard(card);
      transaction.setAmount(BigDecimal.valueOf(amountN));
      transactionService.saveTransaction(transaction);
      card.setCurrentBalance(BigDecimal.valueOf(currentA - amountN));
      cardService.save(card);
    } else {
      logger.error("Requested amount " + amount + " is more than card amount " + currentA);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    logger.debug("Withdraw was performed successfully from card " + id);

    return new ResponseEntity<>(card, HttpStatus.OK);
  }

  @RequestMapping(value = "/pincode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> checkPinCode(@RequestBody String creds) {

    JSONObject obj = new JSONObject(creds);
    long cardId = obj.getLong("id");
    logger.debug("Checking pin code for a card " + cardId);
    Card found = cardService.findCardByID(cardId);

    if (found != null) {
      //check whether its locked
      if (obj.getBoolean("isLocked")) {
        //Save it, send message to block in UI
        found.setLocked(true);
        cardService.save(found);
        logger.error("Requested card has been locked " + cardId);
        return new ResponseEntity<>(JSONObject.quote("Your card has been locked"), HttpStatus.LOCKED);
      }

      //check pin code
      if (found.getPin() == obj.getInt("pinCode")) {
        //Success case
        logger.error("Pincode is correct " + cardId);
        return new ResponseEntity<>(JSONObject.quote("Pin is correct"), HttpStatus.OK);
      } else {
        logger.error("Pincode doesn't match " + cardId);
        return new ResponseEntity<>(JSONObject.quote("Pin doesn't match"), HttpStatus.BAD_REQUEST);
      }
    }
    //Not found
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
