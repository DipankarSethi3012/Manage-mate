package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.PlanType;
import com.dipankar.Project.Management.System.entity.Subscription;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.repo.SubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{
    @Autowired
    private UserService userService;
    @Autowired
    private SubscriptionRepo subscriptionRepo;

    @Override
    public Subscription createSubscription(User user) {
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        subscription.setValid(true);
        subscription.setPlanType(PlanType.FREE);

        return subscriptionRepo.save(subscription);
    }

    @Override
    public Subscription getUserSubscription(Long userId) throws Exception {
        Subscription subscription = subscriptionRepo.findByUserId(userId);
        if(!isValid(subscription)) {
            subscription.setPlanType(PlanType.FREE);
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
            subscription.setSubscriptionStartDate(LocalDate.now());
        }
        return subscriptionRepo.save(subscription);
    }

    @Override
    public Subscription upgradeSubscription(Long userId, PlanType planType) {
        Subscription subscription = subscriptionRepo.findByUserId(userId);
        subscription.setPlanType(planType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        int months = 0;
        if(planType.equals(PlanType.ANNUALLY)) months = 12;
        else if(planType.equals(PlanType.MONTHLY)) months = 1;

        subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(months));
        return subscriptionRepo.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if(subscription.getPlanType().equals(PlanType.FREE)) {
            return true;
        }

        LocalDate subscriptionEndDate = subscription.getSubscriptionEndDate();
        LocalDate currentDate = LocalDate.now();

        return subscriptionEndDate.isAfter(currentDate) || subscriptionEndDate.isEqual(currentDate);
    }
}
