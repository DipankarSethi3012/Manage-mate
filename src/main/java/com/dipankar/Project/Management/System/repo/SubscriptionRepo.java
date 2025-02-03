package com.dipankar.Project.Management.System.repo;

import com.dipankar.Project.Management.System.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    Subscription findByUserId(Long userId);

}
