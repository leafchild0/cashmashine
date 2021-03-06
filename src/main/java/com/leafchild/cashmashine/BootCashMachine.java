package com.leafchild.cashmashine;

/**
 * Created by: leafchild
 * Project: cashmashine
 * Date: 14/06/16
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EnableJpaRepositories
@EnableAutoConfiguration
public class BootCashMachine {


    public static void main(String[] args) {

        SpringApplication.run( BootCashMachine.class, args);
    }
}
