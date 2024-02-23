package com.project.bankingapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.bankingapp.dto.AccountDto;
import com.project.bankingapp.entity.Account;
import com.project.bankingapp.mapper.AccountMapper;
import com.project.bankingapp.repository.AccountRepository;
import com.project.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient balance");
		}
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);

	}

	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account)->AccountMapper
												.mapToAccountDto(account))
												.collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account=accountRepository
						.findById(id)
						.orElseThrow(()->new RuntimeException("Account does not exist"));
		accountRepository.deleteById(id);
	}

}
