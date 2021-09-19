package se.nackademin.java20.pgw.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "status")
    private String status;

    @Column(name = "created")
    @CreationTimestamp
    private Instant created;

    @Column(name = "updated")
    @UpdateTimestamp
    private Instant updated;

    public Payment() {
        //For hibernate
    }

    public Payment(String reference, String status) {
        this.reference = reference;
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public Instant getCreated() {
        return created;
    }

    public void markAsPaid() {
        status = "PAID";
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
