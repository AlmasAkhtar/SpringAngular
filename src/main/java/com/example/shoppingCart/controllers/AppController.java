package com.example.shoppingCart.controllers;

import com.example.shoppingCart.models.Customer;
import com.example.shoppingCart.repositories.CustomerRepository;
import com.example.shoppingCart.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("api/v1/auth/")
public class AppController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @PostMapping()
    public Callable<ResponseEntity<Customer>> createItem(@RequestBody Customer _customer) {
        return () -> {
           Customer customer= customerService.createCustomer(_customer);
            URI location = new URI("api/register");
            return ResponseEntity.created(location).body(customer);
        };
    }
    @PostMapping("/register")
    public void registerCustomer(@Valid @RequestBody Customer newCustomer) {
        List<Customer> customers = customerRepository.findAll();
        System.out.println("New user: " + newCustomer.toString());
        for (Customer customer: customers) {
            System.out.println("Registered user: " + newCustomer.toString());
            if (customer.equals(newCustomer)) {
                System.out.println("User Already exists!");
           //     return Status.CUSTOMER_ALREADY_EXISTS;
            }
        }
        customerRepository.save(newCustomer);
      //  return Status.SUCCESS;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("customer", new Customer());
        return mav;
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("customer") Customer customer) {

       Optional<Customer> oauthUser = customerService.login(customer.getId(), customer.getName());
        System.out.print(oauthUser);
        if(Objects.nonNull(oauthUser))
        {
            return "redirect:/";
        }
        else {
            return "redirect:/login";
        }

    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request, HttpServletResponse response)
    {
        return "redirect:/login";
    }
   /* @PostMapping()
    public Callable<ResponseEntity<Customer>> getCustomer(@PathVariable int customerId) {
        return () -> {
            Customer customer= customerService.findById(customerId);
            return ResponseEntity.ok(customer);
        };
    }*/
}