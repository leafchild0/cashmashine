package com.leafchild.cashmashine.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

@RestController
@RequestMapping("/")
public class CustomerController {
    
    //@Autowired
    //UserService uService;
    
    private static final Logger logger =
        LoggerFactory.getLogger(CustomerController.class);
    
    
    /*//Get All the users
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> showUsers() {
        
        List<Customer> allUsers = uService.getAllUsers();
        if(allUsers.isEmpty()) return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> findUserByID(@PathVariable(value = "id") long id) {
        //Check ID
        //Check user
        Customer user = uService.getUserByID(id);
        
        if(user == null) {
            logger.error("User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(user, HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Customer> createUser(@RequestBody Customer user) {
        
        //Check required params
        //Create user
        uService.saveUser(user);
        //Check on errors if need
        //Return results
        return new ResponseEntity<>(user, HttpStatus.CREATED);
        
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateUser(@PathVariable("id") long id, @RequestBody Customer user) {
        
        Customer foundUser = uService.getUserByID(id);
        
        if(foundUser == null) {
            logger.error("Requested user " + id + " not found");
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
        
        foundUser.setName(user.getName());
        foundUser.setPhone(user.getPhone());
        
        //Update user
        uService.updateUser(user);
        //Check on errors
        //Return results
        return new ResponseEntity<>(user, HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        
        Customer foundUser = uService.getUserByID(id);
        
        if(foundUser == null) {
            logger.error("Requested user " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        //Delete user
        uService.deleteUser(foundUser);
        //Check on errors
        //Return results
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAllUsers() {
        
        //Delete all user
        uService.deleteAllUsers();
        //Return results
        return new ResponseEntity<>(HttpStatus.OK);
        
    }*/
}

