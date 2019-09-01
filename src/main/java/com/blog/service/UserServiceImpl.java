package com.blog.service;

import com.blog.models.User;
import com.blog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String addUser(User user) {
        logger.info("[UserService]=> Add User");
        if(user==null) {
            logger.info("[UserService]=> User field is NULL");
            //throw Bad Request exception
            return null;
        }
        if(userRepository.existsById(user.getUserID())){
            logger.info("[UserService]=> User Already exists");
            //throw already exists
            return null;
        }
        User user1;
        user1 = userRepository.save(user);
        if(user1==null){
            logger.info("[UserService]=> Internal Server Error Occurred");
            // throw Internal Server Error
            return null;
        }
        return user1.toString();
    }

    @Override
    public User getUser(long userID) {

        logger.info("[UserService]=> Get User");
        // if ID exist, return it.
        // else return null (userID not found)
        try {
            Optional<User> user = userRepository.findById(userID);   // returns User or Empty-User
            if(!isEmpty(user)) {
                logger.info("[UserService]=> User Found");
                return user.get();
            }
            else
                return null;
        }catch(IllegalArgumentException ex){
            logger.info("[UserService]=> IllegalArgumentException occurred");
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("[UserService]=> Get All Users");
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public String updateUser(long userID, User user) {
        logger.info("[UserService]=> Update Users");
        if( userID!=user.getUserID() ){
            // ID mis-match exception
            logger.info("[UserService]=> ID MisMatch occurred");
            return  null;
        }

        Optional<User> user1 = userRepository.findById(userID);
        if( isEmpty(user1) ){
            // User not found exception
            logger.info("[UserService]=> User Not Found");
            return null;
        }
        User user2 = user1.get();
        user2.setName(user.getName());
        User user3 = userRepository.save(user2);
        return user3.toString();
    }

    @Override
    public boolean deleteUser(long userID) {
        logger.info("[UserService]=> Delete User");
        Optional<User> user = userRepository.findById(userID);
        if(isEmpty(user)){
            // User not found
            logger.info("[UserService]=> User Not Found");
            return false;
        }
        userRepository.delete(user.get());
        return true;
    }

    @Override
    public void deleteAllUsers() {
        logger.info("[UserService]=> Delete All Users");
        userRepository.deleteAll();
    }
}
