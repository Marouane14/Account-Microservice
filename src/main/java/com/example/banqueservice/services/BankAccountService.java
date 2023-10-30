package com.example.banqueservice.services;


import com.example.banqueservice.dto.BankAccountRequestDTO;
import com.example.banqueservice.dto.BankAccountResponseDTO;
import com.example.banqueservice.exceptions.BankAccountNotFoundException;

import java.util.List;

public interface BankAccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO request);
    public List<BankAccountResponseDTO> listAccounts();
    public BankAccountResponseDTO Accountbyid (String id) throws BankAccountNotFoundException;
    public BankAccountResponseDTO updateAccount(BankAccountRequestDTO bankAccountRequestDTO);
    public void deleteAccount(String id) throws BankAccountNotFoundException;

}
