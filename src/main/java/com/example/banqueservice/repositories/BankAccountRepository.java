package com.example.banqueservice.repositories;

import com.example.banqueservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository extends JpaRepository<BankAccount,String>{

}
