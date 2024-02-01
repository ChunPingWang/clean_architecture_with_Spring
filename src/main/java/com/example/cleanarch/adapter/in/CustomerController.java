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
    private ResponseEntity<String> findById() {
          return ResponseEntity.ok("{}");
    } 
}
