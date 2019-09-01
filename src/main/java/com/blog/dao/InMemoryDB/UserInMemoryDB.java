package com.blog.dao.InMemoryDB;

import com.blog.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserInMemoryDB {
    
    Map<Long, User> userMap;

    public UserInMemoryDB() {
        userMap = new HashMap<Long, User>();
    }
    
    /** Create */
    public void addUser(User user){
        userMap.putIfAbsent(user.getUserID(), user);
    }
    
    /** Read */   
    public User getUser(Long ID){    // if not found, it returns null
        // if not found, return null
        return userMap.get(ID);
    } 
    
    /** Update */
    public Long updateUser(Long ID, User user){
        if(userMap.replace(ID, user) == null)
            return null;
        else
            return user.getUserID();
    }
    
    /** Delete */
    public User deleteUser(String ID){  // returns previous-user-info or, null   
        return userMap.remove(ID);    
    }
    
    public void deleteAll(){
        Set<Long> keys = userMap.keySet();
        for ( Long k: keys ) {
            userMap.remove(k);
        }
    }
    
}
