package com.dipankar.Project.Management.System.controllers;

import com.dipankar.Project.Management.System.entity.PlanType;
import com.dipankar.Project.Management.System.entity.Subscription;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.service.SubscriptionService;
import com.dipankar.Project.Management.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> getUserSubscription(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.getUserSubscription(user.getId());

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PatchMapping("/upgrade")
    public ResponseEntity<?> updateUserSubscription(@RequestHeader("Authorization") String jwt, @RequestParam PlanType planType) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), planType);
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }


}
