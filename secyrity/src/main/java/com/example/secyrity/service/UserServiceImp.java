package com.example.secyrity.service;


import com.example.secyrity.dao.UserDao;
import com.example.secyrity.model.Role;
import com.example.secyrity.model.User;
import com.example.secyrity.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

   private final UserDao userDao;
   private final UserRepository userRepository;
   private final RoleServiselmp roleServiselmp;

   public UserServiceImp(UserDao userDao, UserRepository userRepository, RoleServiselmp roleServiselmp) {
      this.userDao = userDao;
      this.userRepository = userRepository;
      this.roleServiselmp = roleServiselmp;
   }

   @Override
   @Transactional
   public void addUser(User user) {
      user.setRoleList(roleServiselmp.getRoleByName("ROLE_USER"));
      userDao.addUser(user);
   }
   @Override
   @Transactional
   public void addAdminUser(User user) {
      user.setRoleList(roleServiselmp.getAllRoles());
      userDao.addUser(user);
   }

   @Override
   @Transactional
   public List<User> getListOfUsers() {
      return userDao.getListOfUsers();
   }

   @Override
   @Transactional
   public void edditUser(Long id, User user) {
      userDao.edditUser(id, user);
   }

   @Override
   @Transactional
   public User getUserId(Long id) {
      return userDao.getUserId(id);
   }

   @Override
   public void deleteUserById(Long id) {
      userDao.deleteUserById(id);
   }

   @Override
   public User getUserByUsername(String name) {
      return userDao.getUserByUsername(name);
   }

   public void addUS(User user){
      userDao.addUser(user);
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
