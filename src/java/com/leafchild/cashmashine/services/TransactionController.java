package com.leafchild.cashmashine.services;

import com.leafchild.cashmashine.dto.transaction.CardTransactionService;
import com.leafchild.cashmashine.entity.Card;
import com.leafchild.cashmashine.entity.CardTransaction;
import org.omg.IOP.TransactionService;
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
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    CardTransactionService transactionService;

    private static final Logger logger =
        LoggerFactory.getLogger(TransactionController.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardTransaction> findCardByID( @PathVariable(value = "id") Long id) {
        CardTransaction transaction = transactionService.findTransactionByID(id);

        if(transaction == null) {
            logger.error("transaction with id " + id + " not found");
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transaction, HttpStatus.OK);

    }
}
