package com.example.patterns_banking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String accountNumber;
  private Double balance;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  @JsonBackReference
  private Customer customer;

  public abstract Double calculateDepositFee(Double amount);
  public abstract double calculateWithdrawalFee(double amount);

  public void deposit(Double amount) {
    double fee = calculateDepositFee(amount);
    this.balance += amount - fee;
  }

  public void withdraw(double amount) {
    if (amount > this.balance) {
      throw new IllegalArgumentException("Insufficient funds");
    }
    double fee = calculateWithdrawalFee(amount);
    this.balance -= (amount + fee);
  }

  //  Crear un nuevo decorador para controlar qué tanto dinero se le puede prestar a una persona en un retiro
//  LIMITE sobre retiros: 20.000
//  Vamos a mostrar el valor excedido y vamos a dejar la cuenta en 0
//  I: 50k
//  W: 60k
//  T: 70k
//  Se hizo el retiro y el excedente fue de 10k
//  La cuenta me queda vacíá

  public void lend(Double amount) {

    double fee = 0.0;

    fee=amount+20000;
    withdraw(fee);


    if (amount > 20000) {
      fee = amount - 20000;
    }

    this.balance=amount-fee;

  }

}
