package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.config.JwtProvider;
import com.dipankar.Project.Management.System.entity.User;
import com.dipankar.Project.Management.System.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmail(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception{
        User user = userRepo.findByEmail(email);
        if(user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        Optional<User> user = userRepo.findById(userId);
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        return user.get();
    }

    @Override
    public User updateUsersProjectSize(User user, int number) {
        user.setProjectSize(user.getProjectSize() + number);
//        if(usegetProjectSize() == -1)
        return userRepo.save(user);
    }
}
