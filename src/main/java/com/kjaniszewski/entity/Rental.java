package com.kjaniszewski.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_FROM")
    private Date dateFrom;

    @Temporal(TemporalType.DATE)
    @Column(name="DATE_TO")
    private Date dateTo;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="INVOICE_ID")
    private Invoice invoice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        //set the reference back
        //invoice.setRentalId(this.id);
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", invoice=" + invoice +
                '}';
    }
}
