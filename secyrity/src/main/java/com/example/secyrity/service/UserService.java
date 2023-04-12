
package com.example.secyrity.service;


import com.example.secyrity.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public interface UserService extends UserDetailsService {
    @Transactional
    void addUser(User user);
    @Transactional(readOnly=true)

    List<User> getListOfUsers();

    @Transactional
    void edditUser(Long id, User user);


    @Transactional(readOnly=true)
    User getUserId(Long id);

    @Transactional
    void deleteUserById(Long id);
    @Query("Select u from User u left join fetch u.roleList where u.username=:username")
    User getUserByUsername(String name);

    public void addAdminUser(User user);

}
