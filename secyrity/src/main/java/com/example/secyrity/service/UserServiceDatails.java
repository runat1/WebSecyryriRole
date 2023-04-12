package com.example.secyrity.service;

import com.example.secyrity.dao.UserDao;
import com.example.secyrity.model.Role;
import com.example.secyrity.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceDatails implements UserDetailsService {
    private UserDao userDao;
    public UserServiceDatails(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.getUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException(String.format("User '%s' nur found", username ));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getRoleList()));
    }
   @Transient
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r->new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
