package com.example.banqueservice.web;

import com.example.banqueservice.dto.BankAccountRequestDTO;
import com.example.banqueservice.dto.BankAccountResponseDTO;
import com.example.banqueservice.entities.BankAccount;
import com.example.banqueservice.repositories.BankAccountRepository;
import com.example.banqueservice.services.BankAccountService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AccountGraphQLController {
    private BankAccountRepository bankAccountRepository;
    private BankAccountService bankAccountService;

    public AccountGraphQLController(BankAccountRepository bankAccountRepository, BankAccountService bankAccountService){
        this.bankAccountRepository=bankAccountRepository;
        this.bankAccountService=bankAccountService;
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
}
