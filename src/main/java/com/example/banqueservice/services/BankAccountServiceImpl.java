package com.example.banqueservice.services;

import com.example.banqueservice.dto.BankAccountRequestDTO;
import com.example.banqueservice.dto.BankAccountResponseDTO;
import com.example.banqueservice.entities.BankAccount;
import com.example.banqueservice.exceptions.BankAccountNotFoundException;
import com.example.banqueservice.mappers.BankAccountMapper;
import com.example.banqueservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class BankAccountServiceImpl implements BankAccountService{

    private BankAccountRepository bankAccountRepository;
    private BankAccountMapper bankAccountMapper;



    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO request) {
        BankAccount bank= bankAccountMapper.from(request);
        System.out.println("id: "+bank.getId());
        if(bank.getId()==null) bank.setId(UUID.randomUUID().toString());
        BankAccount savedBankAccount= bankAccountRepository.save(bank);
        return bankAccountMapper.from(savedBankAccount);
    }

    @Override
    public List<BankAccountResponseDTO> listAccounts() {
        return bankAccountRepository.findAll()
                .stream()
                .map(bankAccountMapper::from)
                .collect(Collectors.toList());
    }

    @Override
    public BankAccountResponseDTO Accountbyid(String id) throws BankAccountNotFoundException {
        BankAccount account = bankAccountRepository.findById(id).orElse(null);
        if(account == null) throw new BankAccountNotFoundException(String.format("Bank Account Not Found with this id : %s",id));
        return bankAccountMapper.from(account);
    }

    @Override
    public BankAccountResponseDTO updateAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount account = bankAccountRepository.findById(bankAccountRequestDTO.getId()).orElseThrow(() -> new RuntimeException(String.format("Account with id = %s is Not Found",bankAccountRequestDTO.getId())));
        if(bankAccountRequestDTO.getBalance() != null) account.setBalance(bankAccountRequestDTO.getBalance());
        if(bankAccountRequestDTO.getType() != null) account.setType(bankAccountRequestDTO.getType());
        if(bankAccountRequestDTO.getCreatedAt() != null) account.setCreatedAt(bankAccountRequestDTO.getCreatedAt());
        if(bankAccountRequestDTO.getCurrency() != null) account.setCurrency(bankAccountRequestDTO.getCurrency());
        bankAccountRepository.save(account);
        return bankAccountMapper.from(account);
    }

    @Override
    public void deleteAccount(String id) throws BankAccountNotFoundException{
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
        if(bankAccount == null) throw new BankAccountNotFoundException(String.format("Bank Account Not Found with this id : %s", id));
    }
}
