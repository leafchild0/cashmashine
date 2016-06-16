package com.leafchild.cashmashine.dto.customer;

import com.leafchild.cashmashine.entity.Customer;

import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 16/06/16
 */

interface CustomerService {
    
    Customer findCurtomerByID( int id);
    
    Customer saveCurtomer( Customer user);
    
    Customer updateCurtomer( Customer user);

    Customer deleteCurtomer( Customer user);
    
    List getAllCurtomers();
    
    long count();
}
