package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.PlanType;
import com.dipankar.Project.Management.System.entity.Subscription;
import com.dipankar.Project.Management.System.entity.User;

public interface SubscriptionService {
    Subscription createSubscription(User user);
    Subscription getUserSubscription(Long userId) throws Exception;
    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
