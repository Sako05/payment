package se.nackademin.java20.pgw.persistance;

import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMessageDtoTest {

    @Test
    void canSerialize() throws JsonProcessingException {
        final PaymentMessageDto paymentMessageDto = new PaymentMessageDto("your-reference-eg-the-orderId", "123", "CREATED");
        final String s = new ObjectMapper().writeValueAsString(paymentMessageDto);

        System.out.println(s);

        assertTrue(s.contains("CREATED"));
    }
}