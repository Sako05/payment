package se.nackademin.java20.pgw.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import se.nackademin.java20.pgw.application.PaymentService;

@Controller
@CrossOrigin
public class PaymentResource {

    private final PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<Void> createPayment(@RequestParam PaymentDto paymentDto) {
        paymentService.createPayment(paymentDto.getReference());
        return ResponseEntity.noContent().build();
    }


}
