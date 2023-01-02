package main.java.io.bankapp.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.bankapp.model.Customer;
import io.bankapp.dao.CustomerRepository;

@Service
public class CustomerDetailsService {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	@Transactional
	public CustomerDetails loadUserByUsername(String username) {
		Customer user = customerRepository.findByUsername(username)
				.orElseThrow(() -> new Exception("User Not Found with username: " + username));

		return CustomerDetailsImpl.build(user);
	}

}