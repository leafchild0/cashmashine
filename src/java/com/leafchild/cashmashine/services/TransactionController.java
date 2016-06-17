package com.leafchild.cashmashine.services;

import com.leafchild.cashmashine.dto.transaction.CardTransactionService;
import com.leafchild.cashmashine.entity.CardTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private CardTransactionService transactionService;

    private static final Logger logger =
        LoggerFactory.getLogger(TransactionController.class);

    @RequestMapping( value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<CardTransaction>> findTransactions(){
        List<CardTransaction> customers = transactionService.getAllTransactions();
        if( customers.isEmpty() ) return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        return new ResponseEntity<>( customers, HttpStatus.OK );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardTransaction> findTransactionByID( @PathVariable(value = "id") Long id) {
        CardTransaction transaction = transactionService.findTransactionByID(id);

        if(transaction == null) {
            logger.error("transaction with id " + id + " not found");
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(transaction, HttpStatus.OK);

    }

    @RequestMapping( value = "/", method = RequestMethod.POST )
    public ResponseEntity<CardTransaction> addTransaction( @RequestBody CardTransaction transaction ){
        //Check required params
        //Create transaction
        transactionService.saveTransaction( transaction );
        //Check on errors if need
        //Return results
        return new ResponseEntity<>( transaction, HttpStatus.CREATED );
    }
    
    /*@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    public ResponseEntity<CardTransaction> updateTransaction( @PathVariable( "id" ) long id, @RequestBody CardTransaction transaction ){

        CardTransaction foundTransaction = transactionService.findTransactionByID( id );
        
        if( foundTransaction == null ){
            logger.error( "Requested transaction " + id + " not found" );
            return new ResponseEntity<>( transaction, HttpStatus.NOT_FOUND );
        }
        foundTransaction.setTransactionName( transaction.getTransactionName() );
        
        //Update transaction
        transaction = transactionService.updateTransaction( transaction );
        //Check on errors
        //Return results
        return new ResponseEntity<>( transaction, HttpStatus.OK );
    }
    
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    public ResponseEntity<Void> deleteTransaction( @PathVariable( "id" ) long id ){

        CardTransaction foundTransaction = transactionService.findTransactionByID( id );
        
        if( foundTransaction == null ){
            logger.error( "Requested transaction " + id + " not found" );
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
        //Delete transaction
        transactionService.deleteTransaction( foundTransaction );
        //Check on errors
        //Return results
        return new ResponseEntity<>( HttpStatus.OK );
    }*/
}
