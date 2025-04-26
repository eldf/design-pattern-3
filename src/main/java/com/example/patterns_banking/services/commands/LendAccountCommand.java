package com.example.patterns_banking.services.commands;

import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.models.decorator.AccountLendDecorator;
import com.example.patterns_banking.models.decorator.AccountNotificationDecorator;
import com.example.patterns_banking.repositories.IAccountRepository;

import java.util.Optional;

public class LendAccountCommand implements ICommand<Account>{

    private final IAccountRepository accountRepository;
    private final Long accountId;
    private final Double amount;

    public LendAccountCommand(IAccountRepository accountRepository, Long accountId, Double amount) {
        this.accountRepository = accountRepository;
        this.accountId = accountId;
        this.amount = amount;
    }

    @Override
    public Account execute() {

        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        Account account = accountOptional.get();
        Account accountLend = new AccountLendDecorator(account);
        accountLend.lend(this.amount);
        return accountRepository.save(accountLend);

    }
}
