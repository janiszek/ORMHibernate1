package com.kjaniszewski.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "CAR")
public class Car {

    public enum CarColorEnum {white, yellow, red, black};
    public enum CarType {Sedan, SUV, Kombi};

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="NAME",
            unique = false,
            nullable = false,
            insertable = true,
            updatable = true)
    private String name;

    @Column(name="PLATES")
    private String plates;

    @Column(name="AVAILABLE")
    private Boolean available;

    @Column(name="NUMBEROFSEATS")
    private Long numberOfSeats;

    @Enumerated(EnumType.STRING)
    @Column(name="COLOR")
    private CarColorEnum carColor;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="TYPE")
    private CarType carType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATION_DATE",
            unique = false,
            nullable = false,
            insertable = true,
            updatable = false)
    private Date creationDate;

    @ElementCollection
    @CollectionTable(name="RENTAL_DATES", joinColumns = @JoinColumn(name="CAR_ID"), foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @Column(name = "RENTAL_DATE")
    private Collection<Date> rentalDates = new ArrayList<Date>();

    @ElementCollection
    @CollectionTable(name="RENTAL_ADDRESS", joinColumns = @JoinColumn(name="CAR_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)))
    private List<Address> rentalAddresses = new ArrayList<Address>();

    //one car have multiple technical reviews
    @OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "CAR_ID")
    @JoinColumn(name = "CAR_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Set<TechnicalReview> technicalReviewSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public Boolean getAvailable() { return available; }

    public void setAvailable(Boolean available) { this.available = available; }

    public Long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public CarColorEnum getCarColor() {
        return carColor;
    }

    public void setCarColor(CarColorEnum carColor) {
        this.carColor = carColor;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Collection<Date> getRentalDates() {
        return rentalDates;
    }

    public void setRentalDates(Collection<Date> rentalDates) {
        this.rentalDates = rentalDates;
    }

    public List<Address> getRentalAddress() {
        return rentalAddresses;
    }

    public void setRentalAddress(List<Address> rentalAddresses) {
        this.rentalAddresses = rentalAddresses;
    }

    public Set<TechnicalReview> getTechnicalReview() {
        return technicalReviewSet;
    }

    public void setTechnicalReview(Set<TechnicalReview> technicalReviewSet) {
        this.technicalReviewSet = technicalReviewSet;
    }

    public Car(){
        super();
    }

    public Car(String name, String plates, Boolean available, Long numberOfSeats, CarColorEnum carColor, CarType carType,
               Date creationDate){
        super();
        setName(name);
        setPlates(plates);
        setAvailable(available);
        setNumberOfSeats(numberOfSeats);
        setCarColor(carColor);
        setCarType(carType);
        setCreationDate(creationDate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plates='" + plates + '\'' +
                ", available=" + available +
                ", numberOfSeats=" + numberOfSeats +
                ", carColor=" + carColor +
                ", carType=" + carType +
                ", creationDate=" + creationDate +
                ", \n\trentalDates=" + rentalDates +
                ", \n\trentalAddresses=" + rentalAddresses +
                ", \n\ttechnicalReviewSet=" + technicalReviewSet +
                '}';
    }
}
