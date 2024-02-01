package com.example.cleanarch.adapter.in;


import com.example.cleanarch.adapter.Customer;
import com.example.cleanarch.adapter.out.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    private CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @GetMapping("/{requestedId}")
    private ResponseEntity<Customer> findById(@PathVariable Long requestedId) {
//return ResponseEntity.ok("{}");
// 未使用資料庫
//        Customer customer = new Customer(752068L, "Rex Wang", 55);
//        return ResponseEntity.ok(customer);
//        if (requestedId.equals(752068L)) {
//            Customer customer = new Customer(752068L, "Rex Wang", 55);
//            return ResponseEntity.ok(customer);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
// 使用資料庫
        Optional<Customer> customerOptional = customerRepository.findById(requestedId);
        if(customerOptional.isPresent()){
            return ResponseEntity.ok(customerOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucb) {
        Customer savedCustomer = customerRepository.save(customer);
        URI locationOfNewCustomer = ucb.path("/customers/{id}").buildAndExpand(savedCustomer.id()).toUri();

        return ResponseEntity.created(locationOfNewCustomer).build();
    }


}
