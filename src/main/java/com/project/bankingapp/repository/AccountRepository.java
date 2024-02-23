package com.project.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
