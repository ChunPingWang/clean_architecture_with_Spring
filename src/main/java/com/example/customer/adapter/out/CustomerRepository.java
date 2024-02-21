package com.example.customer.adapter.out;

import com.example.customer.adapter.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {

}
