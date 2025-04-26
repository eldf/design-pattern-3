package com.example.patterns_banking.services;

import com.example.patterns_banking.dtos.AccountDTO;
import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Account;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.CustomerRepository;
import com.example.patterns_banking.repositories.ICustomerRepository;
import com.example.patterns_banking.services.proxy.IAccountOperations;
import com.example.patterns_banking.services.proxy.ICustomerServiceOperations;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  private final ICustomerRepository customerRepository;
  private final ICustomerServiceOperations proxy;

  public CustomerService(ICustomerRepository customerRepository, ICustomerServiceOperations proxy) {
    this.customerRepository = customerRepository;
      this.proxy = proxy;
  }

 /*
  public Customer create(CustomerDTO customerDTO) {
    Customer customer = Customer
      .builder()
      .name(customerDTO.getName())
      .email(customerDTO.getEmail())
      .build();

    // Implementar proxy para verificar que el correo no sea del dominio yahoo
    return customerRepository.save(customer);
  }
  */
  

  public Customer create(CustomerDTO customerDTO) {
    return proxy.create(customerDTO);

  }

}
