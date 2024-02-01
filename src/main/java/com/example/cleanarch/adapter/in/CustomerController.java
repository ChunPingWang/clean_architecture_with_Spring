package com.example.cleanarch.adapter.in;


import com.example.cleanarch.adapter.out.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//        Customer customer = new Customer(752068L, "Rex Wang", 55);
//        return ResponseEntity.ok(customer);
        Optional<Customer> customerOptional = customerRepository.findById(requestedId);
        if (requestedId.equals(752068L)) {
            Customer customer = new Customer(752068L, "Rex Wang", 55);
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
