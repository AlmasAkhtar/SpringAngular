package com.example.shoppingCart.services;

import com.example.shoppingCart.exceptions.ItemNotFoundException;
import com.example.shoppingCart.models.Customer;
import com.example.shoppingCart.repositories.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    private Logger logger = LoggerFactory.getLogger("DebugLogger");
    public CustomerService (CustomerRepository customerRepository)
    {
        super();
        this.customerRepository=customerRepository;

    }
    @Override
    public Customer save(CustomerRegisterationDTO customerRegisterationDTO) {
        Customer customer=new Customer(customerRegisterationDTO.getId(),customerRegisterationDTO.getName());
        return customerRepository.save(customer);
    }
    public Customer findById(int customerId) {

        Customer customer=customerRepository.findById((long) customerId)
                .orElseThrow(() -> new ItemNotFoundException(customerId));
        return customer;
    }

    public  Customer createCustomer(Customer customer) {
        Customer createdCustomer = customerRepository.save(customer);
        return createdCustomer;
    }

    public Optional<Customer> login(int id, String name) {
        Optional<Customer> user = customerRepository.findById((long) id);
        return user;
    }
   }
