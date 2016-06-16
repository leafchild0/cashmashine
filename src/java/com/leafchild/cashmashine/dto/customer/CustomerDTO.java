package com.leafchild.cashmashine.dto.customer;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

import com.leafchild.cashmashine.entity.Customer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.NotFoundException;
import java.util.List;

/**
 * DAO class for {@link Customer}
 */
@Component
public class CustomerDTO implements CustomerService {

    @Resource
    private CustomerRepository customerRepository;


    @Override
    public Customer findCurtomerByID( int id ){
        return customerRepository.findOne( id );
    }

    @Override
    public Customer saveCurtomer( Customer user ){
        return customerRepository.save(user);
    }

    @Override
    public Customer updateCurtomer( Customer user ){
        Customer updatedCust = customerRepository.findOne(user.getCustomer_id());

        if (updatedCust == null)
            throw new NotFoundException();

        updatedCust.setCustomerName(user.getCustomerName());
        updatedCust.setCards(user.getCards());
        return updatedCust;
    }

    @Override
    public Customer deleteCurtomer( Customer user ){
        Customer cust = customerRepository.findOne( user.getCustomer_id() );
        if(cust != null) {
            customerRepository.delete(user);
            return cust;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List getAllCurtomers(){
        return customerRepository.findAll();
    }

    @Override
    public long count(){
        return customerRepository.count();
    }
}

