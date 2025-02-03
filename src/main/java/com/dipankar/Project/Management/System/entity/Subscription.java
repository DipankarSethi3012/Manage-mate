package com.dipankar.Project.Management.System.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate subscriptionStartDate;
    private LocalDate SubscriptionEndDate;
    private PlanType planType;

    private boolean isValid;

    @OneToOne
    private User user;

    public Subscription(){}

    public Subscription(Long id, LocalDate subscriptionStartDate, LocalDate subscriptionEndDate, PlanType planType, boolean isValid, User user) {
        this.id = id;
        this.subscriptionStartDate = subscriptionStartDate;
        SubscriptionEndDate = subscriptionEndDate;
        this.planType = planType;
        this.isValid = isValid;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(LocalDate subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public LocalDate getSubscriptionEndDate() {
        return SubscriptionEndDate;
    }

    public void setSubscriptionEndDate(LocalDate subscriptionEndDate) {
        SubscriptionEndDate = subscriptionEndDate;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", subscriptionStartDate=" + subscriptionStartDate +
                ", SubscriptionEndDate=" + SubscriptionEndDate +
                ", planType=" + planType +
                ", isValid=" + isValid +
                ", user=" + user +
                '}';
    }
}
