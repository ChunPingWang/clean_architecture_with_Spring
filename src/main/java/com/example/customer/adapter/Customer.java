package com.example.customer.adapter;

import org.springframework.data.annotation.Id;

public record Customer(@Id Long id, String name, int age) {
}
