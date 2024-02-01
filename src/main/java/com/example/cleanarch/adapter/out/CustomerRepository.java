package com.example.cleanarch.adapter.out;

import com.example.cleanarch.adapter.in.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
