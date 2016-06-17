package com.leafchild.cashmashine.services;

import com.leafchild.cashmashine.dto.customer.CustomerService;
import com.leafchild.cashmashine.entity.Customer;
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
@RequestMapping("/api/customers")
public class CustomerController {
    
    @Autowired
    private CustomerService cService;
    
    private static final Logger logger =
        LoggerFactory.getLogger(CustomerController.class);
    
    
    @RequestMapping( value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Customer>> findCustomers(){
        List<Customer> customers = cService.getAllCurtomers();
        if( customers.isEmpty() ) return new ResponseEntity<>( HttpStatus.NO_CONTENT );
        
        return new ResponseEntity<>( customers, HttpStatus.OK );
    }
    
    @RequestMapping( value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<Customer> findCustomerByID( @PathVariable( value = "id" ) Long id ){
        //Check ID
        //Check customer
        Customer customer = cService.findCustomerByID( id );
        
        if( customer == null ){
            logger.error( "customer with id " + id + " not found" );
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
        
        return new ResponseEntity<>( customer, HttpStatus.OK );
    }
    
    @RequestMapping( value = "/", method = RequestMethod.POST )
    public ResponseEntity<Customer> addCustomer( @RequestBody Customer customer ){
        //Check required params
        //Create customer
        cService.saveCustomer( customer );
        //Check on errors if need
        //Return results
        return new ResponseEntity<>( customer, HttpStatus.CREATED );
    }
    
    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
    public ResponseEntity<Customer> updateCustomer( @PathVariable( "id" ) long id, @RequestBody Customer customer ){
        
        Customer foundCustomer = cService.findCustomerByID( id );
        
        if( foundCustomer == null ){
            logger.error( "Requested customer " + id + " not found" );
            return new ResponseEntity<>( customer, HttpStatus.NOT_FOUND );
        }
        foundCustomer.setCustomerName( customer.getCustomerName() );
        
        //Update customer
        customer = cService.updateCustomer( customer );
        //Check on errors
        //Return results
        return new ResponseEntity<>( customer, HttpStatus.OK );
    }
    
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    public ResponseEntity<Void> deleteCustomer( @PathVariable( "id" ) long id ){
        
        Customer foundCustomer = cService.findCustomerByID( id );
        
        if( foundCustomer == null ){
            logger.error( "Requested customer " + id + " not found" );
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
        //Delete customer
        cService.deleteCustomer( foundCustomer );
        //Check on errors
        //Return results
        return new ResponseEntity<>( HttpStatus.OK );
    }
}

