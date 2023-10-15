package com.management.hotel.customer.service;

import com.management.hotel.customer.exception.CustomerAlreadyPresentException;
import com.management.hotel.customer.exception.NoSuchCustomerExistsException;
import com.management.hotel.customer.model.Customer;
import com.management.hotel.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer save(Customer customer) {
        if(customerRepository.findByEmail(customer.getEmail()) != null){
            throw new CustomerAlreadyPresentException();
        }else{
            return customerRepository.save(customer);
        }

    }

    public Optional<Customer> getCustomerById(Long id) {
        return Optional.ofNullable(customerRepository.findById(id).orElseThrow(() -> new NoSuchCustomerExistsException()));
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer update(Customer customer, Long id) {
        Optional<Customer> savedCustomer = customerRepository.findById(id);
        if(savedCustomer.isPresent()){
            Customer updatedCustomer = new Customer();
            updatedCustomer.setId(id);
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setPassword(customer.getPassword());
            return customerRepository.save(updatedCustomer);
        }else {
            throw new NoSuchCustomerExistsException();
        }
    }
}
