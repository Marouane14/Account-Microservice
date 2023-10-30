package com.example.banqueservice.web;

import com.example.banqueservice.dto.BankAccountRequestDTO;
import com.example.banqueservice.dto.BankAccountResponseDTO;
import com.example.banqueservice.exceptions.BankAccountNotFoundException;
import com.example.banqueservice.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountRestController {

    private BankAccountService bankAccountService;

    public AccountRestController(BankAccountService bankAccountService){
        this.bankAccountService=bankAccountService;
    }

    @GetMapping("/BankAccounts")
    public List<BankAccountResponseDTO> BankAccounts(){
        return bankAccountService.listAccounts();
    }


    @GetMapping("/BankAccounts/{id}")
    public BankAccountResponseDTO getAccount(@PathVariable String id) throws BankAccountNotFoundException {
        return bankAccountService.Accountbyid(id);
    }

    @PostMapping("/BankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO bankAccountRequestDTO){
        return bankAccountService.addAccount(bankAccountRequestDTO);
    }


    @PutMapping("/BankAccounts/{id}")
    public BankAccountResponseDTO update(@RequestBody BankAccountRequestDTO bankAccountRequestDTO){
       return bankAccountService.updateAccount(bankAccountRequestDTO);
    }

    @DeleteMapping ("/BankAccounts/{id}")
    public void delete(@PathVariable String id) throws BankAccountNotFoundException {
        bankAccountService.deleteAccount(id);
    }

}
