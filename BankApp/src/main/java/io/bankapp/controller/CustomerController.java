package io.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.bankapp.model.Customer;
import io.bankapp.service.CustomerService;
import io.bankapp.controller.AccountController;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountController accountController;

	@PostMapping("/customer/register")
	public void createCustomer(@RequestBody Customer customer) {
		customerService.createCustomer(customer);
		accountController.createAccount(customer.getAcctID(), 0, "Active");
	}

	@GetMapping("/customer/login")
	public Customer getCustomerInfo(@RequestParam int acctID,@RequestParam string password) {
		return customerService.getCustomerInfo(acctID);
	}

}
