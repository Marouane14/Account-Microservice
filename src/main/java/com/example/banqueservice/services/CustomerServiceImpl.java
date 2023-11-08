package com.example.banqueservice.services;

import com.example.banqueservice.entities.Customer;
import com.example.banqueservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> listCustomers() {
        return this.customerRepository.findAll();
    }
}
