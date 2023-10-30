package com.example.banqueservice.mappers;

import com.example.banqueservice.dto.BankAccountRequestDTO;
import com.example.banqueservice.dto.BankAccountResponseDTO;
import com.example.banqueservice.entities.BankAccount;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapper {
    private ModelMapper modelMapper = new ModelMapper();
    //Mapper la classe Customer vers la classe CustomerDTO
    public BankAccountResponseDTO from(BankAccount bankAccount) {
        return modelMapper.map(bankAccount, BankAccountResponseDTO.class);
    }
    public BankAccount from(BankAccountRequestDTO bankAccountRequestDTO) {
        return modelMapper.map(bankAccountRequestDTO, BankAccount.class);
    }
}
