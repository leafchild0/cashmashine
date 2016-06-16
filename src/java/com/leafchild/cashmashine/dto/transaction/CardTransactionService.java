package com.leafchild.cashmashine.dto.transaction;

import com.leafchild.cashmashine.entity.CardTransaction;

import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 16/06/16
 */

interface CardTransactionService {
    
    CardTransaction findTransactionByID( int id );
    
    CardTransaction saveTransaction( CardTransaction transaction );

    CardTransaction deleteTransaction( CardTransaction transaction );
    
    List getAllTransactions();
    
    long count();
}
