package com.kjaniszewski.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    @SequenceGenerator(name = "customer_seq_gen", sequenceName = "CUSTOMER_SEQ", initialValue = 1000, allocationSize = 1)
    private Long id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Embedded
    private Address address;

    @Column(name="CREATION_DATE",
            unique = false,
            nullable = false,
            insertable = true,
            updatable = false)
    private LocalDateTime creationDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Customer(){
        super();
    }

    public Customer(String firstName, String lastName, LocalDateTime creationDate){
        super();
        setFirstName(firstName);
        setLastName(lastName);
        setCreationDate(creationDate);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", creationDate=" + creationDate +
                '}';
    }
}
