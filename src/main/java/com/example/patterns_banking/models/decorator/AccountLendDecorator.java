package com.example.patterns_banking.models.decorator;


import com.example.patterns_banking.models.Account;

public class AccountLendDecorator extends AccountDecorator {

    public AccountLendDecorator(Account account){
        super(account);
    }

    @Override
    public void lend(Double amount) {
        super.lend(amount);
        System.out.println("Se ha realizado un prestamo de: " + amount + " en la cuenta de: " + getAccountNumber());
    }

}
