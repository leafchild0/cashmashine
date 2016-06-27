package com.leafchild.cashmashine.dto.customer;

import com.leafchild.cashmashine.entity.Customer;

import java.util.List;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 16/06/16
 */

public interface CustomerService {
    
    Customer findCustomerByID(long id);
    
    Customer saveCustomer(Customer user);
    
    Customer updateCustomer(Customer user);

    Customer deleteCustomer(Customer user);
    
    List<Customer> getAllCurtomers();
    
    long count();
}
