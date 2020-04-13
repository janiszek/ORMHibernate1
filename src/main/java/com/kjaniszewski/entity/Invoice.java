package com.kjaniszewski.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="INVOICE_NUMBER")
    private String invoiceNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATION_DATE",
            unique = false,
            nullable = false,
            insertable = true,
            updatable = false)
    private Date issueDate;

    /*@Column(name="RENTAL_ID")
    private Long rentalId;*/

    @OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true) //pole encji rental.invoice
    private Rental rental;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Long getRentalId() {
        return rental.getId();
    }

    public void setRentalId(Long rentalId) {
        this.rental.setId(rentalId);
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", issueDate=" + issueDate +
                ", rentalId=" + getRentalId() +
                '}';
    }
}
