package com.kjaniszewski.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TechnicalReview {

    public enum TechnicalReviewResultEnum {FAILED, PASSED};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextReviewDate;
    @Enumerated(EnumType.STRING)
    private TechnicalReviewResultEnum result;

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Date getNextReviewDate() {
        return nextReviewDate;
    }

    public void setNextReviewDate(Date nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }

    public TechnicalReviewResultEnum getResult() {
        return result;
    }

    public void setResult(TechnicalReviewResultEnum result) {
        this.result = result;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static TechnicalReview createTechnicalReview(TechnicalReview.TechnicalReviewResultEnum reviewResult, Date reviewDate, Date nextReviewDate) {
        TechnicalReview technicalReview = new TechnicalReview();
        technicalReview.setResult(reviewResult);
        technicalReview.setReviewDate(reviewDate);
        technicalReview.setNextReviewDate(nextReviewDate);
        return technicalReview;
    }

    @Override
    public String toString() {
        return "\n\t\tTechnicalReview{" +
                "id=" + id +
                ", reviewDate=" + reviewDate +
                ", nextReviewDate=" + nextReviewDate +
                ", result=" + result +
                ", CAR_ID=" + ((car!=null)?car.getId():null) +
                "}";
    }
}
