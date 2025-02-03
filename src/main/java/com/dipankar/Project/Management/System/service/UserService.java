package com.dipankar.Project.Management.System.service;

import com.dipankar.Project.Management.System.entity.User;

public interface UserService {

    User findUserProfileByJwt(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
    User findUserById(Long userId) throws Exception;
    User updateUsersProjectSize(User user, int number);

}
