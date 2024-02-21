package com.example.customer.adapter.in;

import com.example.customer.adapter.Customer;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CustomerJsonTests {

    @Autowired
    private JacksonTester<Customer> json;

    @Autowired
    private JacksonTester<Customer[]> jsonList;

    private Customer[] customers;
    @BeforeEach
    void setUp(){
        customers = Arrays.array(
                new Customer(752068L, "Rex Wang", 55),
                new Customer(111111L, "Harry Wang", 24),
                new Customer(222222L, "Gigi Hsieh", 52)
        );
    }
    @Test
    void customerSerializationTest() throws IOException {
        Customer customer = new Customer(752068L, "Rex Wang", 55);
        assertThat(json.write(customer)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(customer)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(customer)).extractingJsonPathNumberValue("@.id").isEqualTo(752068);
        assertThat(json.write(customer)).hasJsonPathStringValue("@.name");
        assertThat(json.write(customer)).extractingJsonPathStringValue("@.name").isEqualTo("Rex Wang");
        assertThat(json.write(customer)).hasJsonPathNumberValue("@.age");
        assertThat(json.write(customer)).extractingJsonPathNumberValue("@.age").isEqualTo(55);
    }

    @Test
    void customerDeserializationTest() throws IOException {
        String expected = """
                {
                 "id": 752068,
                 "name": "Rex Wang",
                 "age": 55
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new Customer(752068L, "Rex Wang", 55));
        assertThat(json.parseObject(expected).id()).isEqualTo(752068);
        assertThat(json.parseObject(expected).name()).isEqualTo("Rex Wang");
        assertThat(json.parseObject(expected).age()).isEqualTo(55);
    }

    @Test
    void customerListSerializationTest() throws IOException{
        assertThat(jsonList.write(customers)).isStrictlyEqualToJson("list.json");
    }

    @Test
    void customerListDeserializationTest() throws IOException {
        String expected="""
         [
             { "id": 752068, "name": "Rex Wang", "age": 55 },
             { "id": 111111, "name": "Harry Wang", "age": 24 },
             { "id": 222222, "name": "Gigi Hsieh", "age": 52 } 
         ]
         """;
        assertThat(jsonList.parse(expected)).isEqualTo(customers);
    }
}
