package com.example.patterns_banking.services.proxy;

import com.example.patterns_banking.dtos.CustomerDTO;
import com.example.patterns_banking.models.Customer;
import com.example.patterns_banking.repositories.CustomerRepository;
import com.example.patterns_banking.services.CustomerService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerServiceProxy implements ICustomerServiceOperations {


    CustomerRepository customerRepository;
    @Override
    public Customer create(CustomerDTO customerDTO) {
        validateDomanin(customerDTO.getEmail());
        Customer customer = Customer
                .builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .build();
        return customerRepository.save(customer);


    }


    public static boolean esCorreoYahoo(String mail) {
 String patron = "^[a-zA-Z0-9._%+-]+@yahoo\\.com$";
Pattern pattern = Pattern.compile(patron);
Matcher matcher = pattern.matcher(mail);
 return matcher.matches();
}






    private void validateDomanin(String mail) {

      if(esCorreoYahoo(mail))
          throw new IllegalArgumentException("los datos ingresados tiene dominio yahoo");

    }


}
