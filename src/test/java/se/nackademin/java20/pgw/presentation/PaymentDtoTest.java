package se.nackademin.java20.pgw.presentation;

import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDtoTest {

    @Test
    void canDeserialize() throws IOException {
        String json = "{\"reference\": \"dan\", \"amount\": 100}";
        final ObjectMapper objectMapper = new ObjectMapper();
        final PaymentDto paymentDto = objectMapper.readValue(json, PaymentDto.class);
        assertEquals(paymentDto.getReference(), "dan");
        assertEquals(paymentDto.getAmount(), 100L);
    }
}