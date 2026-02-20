package com.vis.rental.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vis.rental.entity.Customers;
import com.vis.rental.repository.CustomerRepository;
import com.vis.rental.service.CustomerService;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class CustomerController {
	
	@Autowired
	CustomerService  customerService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping("/add-Customer")
	public ResponseEntity<Customers> addCustomerRequestBody(
	        @RequestBody Customers customer,
	        Principal principal) {

	    Customers savedCustomer =
	            customerService.addCustomer(customer, principal.getName());

	    return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
	}
	
//	
//	@PostMapping("/add-CustomerMul")
//	public ResponseEntity<List<Customers>> addCustomerMulRequestBody(@RequestBody List<Customers> Customer  ) {
//		
//		return new ResponseEntity<List<Customers>>(customerService.addCustomerMul(Customer),HttpStatus.CREATED);		
//		
//	}


	
	@GetMapping("/single-customer/{customerId}")
	public ResponseEntity<Customers> SingleItems(@PathVariable Integer customerId) {
	    return new ResponseEntity<Customers>(customerService.CustomerFind(customerId),HttpStatus.OK);
	}
	
	@GetMapping("/All-customer")
	public ResponseEntity<List<Customers>> AllCustomerFind(){
		return new ResponseEntity<List<Customers>>(customerService.AllCustoemerFind(),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/customerDelete/{customerId}")
	public ResponseEntity<String> customerDelete(@PathVariable Integer customerId){
//		logger.info("Request received in controller to delete product ID " +customerId);
		customerService.customerDelete(customerId);
		return new ResponseEntity<String>("Delete Product ID:"+customerId,HttpStatus.OK);
	}
	
	
	@PutMapping("/customerUpdate/{customerid}")
	public ResponseEntity<Customers> updateProduct(@PathVariable Integer customerid,@RequestBody Customers newValue){

		return new ResponseEntity<Customers>(customerService.customerUpdate(customerid,newValue),HttpStatus.OK);
	}
	
	@GetMapping("/customerSingle/{customerid}")
	public ResponseEntity<Customers> SingleCustomer(@PathVariable Integer customerid) {
		
		return new  ResponseEntity<Customers>(customerService.SingleCustomer( customerid),HttpStatus.CREATED);
//		return productService.addMulProduct(products);
	}

	@PutMapping("/customerStatus/{id}")
	public ResponseEntity<Customers> updateStatus(@PathVariable Integer id) {
	    Customers customer = customerRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Customer not found"));

	    customer.setStatus(
	        customer.getStatus().equals("ACTIVE") ? "INACTIVE" : "ACTIVE"
	    );

	    return ResponseEntity.ok(customerRepository.save(customer));
	}
	
	
}
