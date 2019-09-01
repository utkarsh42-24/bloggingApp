package com.blog.service;

import com.blog.models.User;

import java.util.List;

public interface UserService {

    String addUser(User user);

    User getUser(long userID);
    List<User> getAllUsers();

    String updateUser(long userID, User user);

    boolean deleteUser(long userID);
    void deleteAllUsers();


}
