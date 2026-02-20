package com.vis.rental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.vis.rental.entity.Customers;
import com.vis.rental.entity.User;
import com.vis.rental.exceptionHandling.CustomersNotFoundException;
import com.vis.rental.repository.CustomerRepository;
import com.vis.rental.repository.UserRespository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	UserRespository  userRepository;

	public Customers addCustomer(Customers customer) {

		return customerRepository.save(customer);
	}

	public Customers addCustomer(Customers customer, String username) {

	    User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    customer.setUser(user);

	    return customerRepository.save(customer);
	}


	public Customers CustomerFind(Integer customerId) {
		Optional<Customers> optionalcustomer = customerRepository.findById(customerId);
		Customers customer = null;
		if (!customerRepository.existsById(customerId)) {
			throw new CustomersNotFoundException("Customer is Not Present ");
		}

		customer = optionalcustomer.get();
		return customer;

	}

	public List<Customers> AllCustoemerFind() {

		return customerRepository.findAll();
	}

	public void customerDelete(int customerId) {

	    Customers customer = customerRepository.findById(customerId)
	        .orElseThrow(() ->
	            new CustomersNotFoundException(
	                "Customer with ID " + customerId + " does not exist"
	            )
	        );

	    // 🔥 Soft delete instead of hard delete
	    customer.setStatus("INACTIVE");
	    customerRepository.save(customer);
	}

	public  Customers customerUpdate(Integer customerid, Customers newValue) {
		
		 Customers existingCustomer = customerRepository.findById(customerid)
			        .orElseThrow(() ->
			            new CustomersNotFoundException(
			                "Customer with ID " + customerid + " does not exist in Database"
			            )
			        );

			    // 🔹 Update fields (ONLY what is allowed)
			    existingCustomer.setName(newValue.getName());
			    existingCustomer.setEmail(newValue.getEmail());
			    existingCustomer.setPhone(newValue.getPhone());
			    existingCustomer.setStatus(newValue.getStatus());
			    
			    
			    existingCustomer.setLicenseNumber(newValue.getLicenseNumber());
			    // 🔹 Update address (if present)
			    if (newValue.getAddress() != null) {
			        existingCustomer.setAddress(newValue.getAddress());
			    }

		
		return customerRepository.save(existingCustomer);
	}
	
	public Customers SingleCustomer(Integer customerid) {
		Optional<Customers> optionalCustomer = customerRepository.findById(customerid);
		Customers Customer = null;
		if(optionalCustomer.isPresent()) {
			Customer = optionalCustomer.get();
			return Customer;
		}
		throw new RuntimeException("Customers is not Present in stock"); // it not show throws because it unchecked Exception but RuntimeException to behalf of 
																		//note a Exception than it throw Exception																					
	}
}

