package se.nackademin.java20.pgw.presentation;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public class PaymentDto {
    private String reference;
    private long amount;

    public PaymentDto() {
    }

    public String getReference() {
        return reference;
    }

    public long getAmount() {
        return amount;
    }
}
