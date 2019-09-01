package com.blog.controller;

import com.blog.common.exceptionHandling.UserNotFoundException;
import com.blog.models.User;
import com.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    UserService userServiceImpl;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    /** Create APIs*/
    @PostMapping("/add")
    public String addUser(@RequestBody User user){

        logger.info("[UserController]=> Add User API is called");
        String response = userServiceImpl.addUser(user);
        if(response == null){
            // throw particular error
            logger.info("[UserController]=> Error ocurred in Add User API");
        }
        logger.info("[UserController]=> Successfully Added");
        return response;

    }



    /** Read */
    @GetMapping("/get/{id}")
    public User getUser(@PathVariable Long id){

        logger.info("[UserController]=> Get User API is called");
        if(id == null){
            // Bad Request
            logger.info("[UserController]=> Bad Request in Get User API");
            return null;
        }
        User user = userServiceImpl.getUser(id);
        if(user==null){
            // Not Found
            logger.info("[UserController]=> User Not Found in Get User API");
            throw new UserNotFoundException("User Not Found", new Throwable("User with id : " + id + " doesn't exist."));
        }
        logger.info("[UserController]=> Successfully Get");
        return user;
    }

    @GetMapping("/get/all")
    public List<User> getAllUsers(){

        logger.info("[UserController]=> Get All Users API is called");
        List<User> users = userServiceImpl.getAllUsers();
        if(users==null || isEmpty(users)){
            // Not Found
            logger.info("[UserController]=> User Not Found in Get All Users API");
            throw new UserNotFoundException("Users Not Found", new Throwable("Users don't exist."));
        }
        logger.info("[UserController]=> Successfully Get All");
        return users;
    }



    /** Update */
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user){

        logger.info("[UserController]=> Update User API is called");
        // if updated successfully, return url
        // else, nil
        String response = userServiceImpl.updateUser(id, user);
        if(response == null){
            // throw particular error
            logger.info("[UserController]=> Error in Update User API");
            return null;
        }
        logger.info("[UserController]=> Successfully Updated");
        return response;
    }




    /** Delete */
    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable Long id){

        logger.info("[UserController]=> Delete User API is called");
        boolean response = userServiceImpl.deleteUser(id);
        if(!response){
            // throw error
            logger.info("[UserController]=> User Not Found in Delete User API");
            throw new UserNotFoundException("User Not Found", new Throwable("User with id : " + id + " doesn't exist."));
        }
        logger.info("[UserController]=> Successfully Deleted");
        return true;
    }

    @DeleteMapping("/delete/all")
    public void deleteAllUsers(){
        logger.info("[UserController]=> Delete All Users API is called");
        userServiceImpl.deleteAllUsers();
    }


}
