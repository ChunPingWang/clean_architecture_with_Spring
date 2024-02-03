package com.example.cleanarch.adapter.in;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.cleanarch.adapter.Customer;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import net.minidev.json.JSONArray;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;



    @Test
    void customer_when_data_is_saved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/customers/752068", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(752068);

        String name = documentContext.read("$.name");
        assertThat(name).isNotNull();
        assertThat(name).isEqualTo("Rex Wang");

        Integer age = documentContext.read("$.age");
        assertThat(age).isEqualTo(55);

    }

    @Test
    void should_not_return_a_customer_with_an_unknown_id() {
        ResponseEntity<String> response = restTemplate.getForEntity("/customers/1000", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void should_create_new_customer() {
        Customer customer = new Customer(null, "Harry Wang", 23);
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/customers", customer, Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        URI locationOfNewCustomer= createResponse.getHeaders().getLocation();

        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCustomer, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void should_return_all_customers_when_list_is_requested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/customers", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void should_return_all_customers_when_list_is_requested_v2() {
        ResponseEntity<String> response = restTemplate.getForEntity("/customers", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int customerCount= documentContext.read("$.length()");
        assertThat(customerCount).isEqualTo(3);


        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(752068, 111111, 222222);

        JSONArray amounts = documentContext.read("$..age");
        assertThat(amounts).containsExactlyInAnyOrder(55, 24, 52);
    }
}
