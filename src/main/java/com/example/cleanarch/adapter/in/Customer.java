package com.example.cleanarch.adapter.in;

import org.springframework.data.annotation.Id;

public record Customer(@Id Long id, String name, int age) {
}
