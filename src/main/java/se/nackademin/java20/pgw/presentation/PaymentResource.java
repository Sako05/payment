package se.nackademin.java20.pgw.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se.nackademin.java20.pgw.application.PaymentService;

@Controller
public class PaymentResource {

    private final PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<Void> createPayment(@RequestBody PaymentDto paymentDto) {
        paymentService.createPayment(paymentDto.getReference());
        return ResponseEntity.noContent().build();
    }


}
