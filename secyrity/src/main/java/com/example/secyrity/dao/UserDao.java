
package com.example.secyrity.dao;


import com.example.secyrity.model.User;
import org.springframework.data.jpa.repository.Query;

import java.beans.Transient;
import java.util.List;

public interface UserDao {
   @Transient
   void addUser(User user);
   List<User> getListOfUsers();



    void edditUser(Long id, User user);



    User getUserId(Long id);

    void deleteUserById(Long id);

    User getUserByUsername(String name);

}