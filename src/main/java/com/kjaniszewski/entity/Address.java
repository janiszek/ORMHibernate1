package com.kjaniszewski.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
    @Column(name = "ADDRESS_COUNTRY")
    private String country;
    @Column(name = "ADDRESS_CITY")
    private String city;
    @Column(name = "ADDRESS_STREET")
    private String street;
    @Column(name = "ADDRESS_NUMBER")
    private String number;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public static Address createAddress(String city, String country, String street, String no) {
        Address address = new Address();
        address.setCity(city);
        address.setCountry(country);
        address.setStreet(street);
        address.setNumber(no);
        return address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
