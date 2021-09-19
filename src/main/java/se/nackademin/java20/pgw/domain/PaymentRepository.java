package se.nackademin.java20.pgw.domain;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);

    Optional<Payment> findByReference(String reference);

     List<Payment> finalAllUnpaid();
}
