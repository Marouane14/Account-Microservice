package com.example.banqueservice.web;

import com.example.banqueservice.dto.BankAccountRequestDTO;
import com.example.banqueservice.dto.BankAccountResponseDTO;
import com.example.banqueservice.entities.BankAccount;
import com.example.banqueservice.entities.Customer;
import com.example.banqueservice.exceptions.BankAccountNotFoundException;
import com.example.banqueservice.repositories.BankAccountRepository;
import com.example.banqueservice.services.BankAccountService;
import com.example.banqueservice.services.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AccountGraphQLController {
    private BankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;
    private CustomerService customerService;

    public AccountGraphQLController(BankAccountRepository bankAccountRepository, BankAccountService bankAccountService, CustomerService customerService){
        this.bankAccountRepository=bankAccountRepository;
        this.bankAccountService=bankAccountService;
        this.customerService=customerService;
    }

    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){
        return bankAccountRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Bank Account %s Not Found!",id))
        );
    }
    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO request){
        return bankAccountService.addAccount(request);
    }
    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument BankAccountRequestDTO  request){
        return bankAccountService.updateAccount(request);
    }

    @MutationMapping
    Boolean deleteAccount(@Argument String id) throws BankAccountNotFoundException {
        bankAccountService.deleteAccount(id);
        return true;
    }

    @QueryMapping
    public List<Customer> customers(){
        return this.customerService.listCustomers();
    }

}
