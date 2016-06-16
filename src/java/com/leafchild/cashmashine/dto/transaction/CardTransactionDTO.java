package com.leafchild.cashmashine.dto.transaction;

import com.leafchild.cashmashine.entity.CardTransaction;

import javax.annotation.Resource;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

public class CardTransactionDTO implements CardTransactionService {

    @Resource
    private CardTransactionRepository transactionRepository;

    @Override
    public CardTransaction findTransactionByID( int id ){
        return transactionRepository.findOne( id );
    }

    @Override
    public CardTransaction saveTransaction( CardTransaction transaction ){
        return transactionRepository.save( transaction );
    }

    @Override
    public CardTransaction deleteTransaction( CardTransaction transaction ){
        CardTransaction trans =
            transactionRepository.findOne( transaction.getCardTransaction_id());
        if(trans != null) {
            transactionRepository.delete(transaction);
            return trans;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List getAllTransactions(){
        return transactionRepository.findAll();
    }

    @Override
    public long count(){
        return transactionRepository.count();
    }
}
