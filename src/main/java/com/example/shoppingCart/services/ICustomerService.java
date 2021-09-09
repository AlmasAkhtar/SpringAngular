package com.example.shoppingCart.services;

import com.example.shoppingCart.models.Customer;

public interface ICustomerService {
    Customer save(CustomerRegisterationDTO customerRegisterationDTO);
}
