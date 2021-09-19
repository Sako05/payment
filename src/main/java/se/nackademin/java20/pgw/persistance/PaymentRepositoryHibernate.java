package se.nackademin.java20.pgw.persistance;

import se.nackademin.java20.pgw.domain.Payment;
import se.nackademin.java20.pgw.domain.PaymentRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PaymentRepositoryHibernate implements PaymentRepository {
    private final EntityManager em;

    public PaymentRepositoryHibernate(EntityManager em) {
        this.em = em;
    }

    public Payment save(Payment payment) {
        em.persist(payment);
        return payment;
    }

    public Optional<Payment> findByReference(String reference) {
        TypedQuery<Payment> q = em.createQuery("SELECT a FROM Payment a WHERE a.reference = :reference", Payment.class);
        q.setParameter("reference", reference);
        return Optional.ofNullable(q.getSingleResult());
    }

    @Override
    public List<Payment> finalAllUnpaid() {
        TypedQuery<Payment> q = em.createQuery("SELECT a FROM Payment a WHERE a.status = :status", Payment.class);
        q.setParameter("status", "CREATED");

        return q.getResultList();
    }
}
