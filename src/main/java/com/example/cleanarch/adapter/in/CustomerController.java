package com.example.cleanarch.adapter.in;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @GetMapping("/{requestedId}")
    private ResponseEntity<Customer> findById(@PathVariable Long requestedId) {
        //return ResponseEntity.ok("{}");
//        Customer customer = new Customer(752068L, "Rex Wang", 55);
//        return ResponseEntity.ok(customer);
        if (requestedId.equals(752068L)){
        Customer customer = new Customer(752068L, "Rex Wang", 55);
        return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    } 
}
