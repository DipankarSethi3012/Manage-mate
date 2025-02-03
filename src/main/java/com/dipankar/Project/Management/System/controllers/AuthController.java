package com.dipankar.Project.Management.System.controllers;

import com.dipankar.Project.Management.System.config.JwtProvider;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.repo.UserRepo;
import com.dipankar.Project.Management.System.request.LoginRequest;
import com.dipankar.Project.Management.System.response.AuthResponse;
import com.dipankar.Project.Management.System.service.CustomUserDetailsImpl;
import com.dipankar.Project.Management.System.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsImpl customUserDetails;

    @Autowired
    private SubscriptionService subscriptionService;
    @PostMapping("/signup")
    public ResponseEntity<User> createUserHandler(@Valid @RequestBody User user, BindingResult bindingResult) throws Exception{

        if(bindingResult.hasErrors()){
            System.out.println("Validation Failed");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User isUSerExist = userRepo.findByEmail(user.getEmail());
        if(isUSerExist != null) {
            throw new Exception("email already exist with another account");
        }
//        System.out
        User createdUser = new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        User savedUser = userRepo.save(createdUser);

        subscriptionService.createSubscription(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("signup success");
        authResponse.setJwt(jwt);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> signing(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        if(result.hasErrors()) {
            System.out.println("format is invalid while log in");
        }
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage("login success");
        authResponse.setJwt(jwt);

        return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if(userDetails == null) {
            throw new BadCredentialsException("invalid Username");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


}
