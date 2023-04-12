
package com.example.secyrity.dao;


import com.example.secyrity.model.Role;
import com.example.secyrity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public void addUser(User user) {
      Long id= user.getId();
      if (id==null) {
         entityManager.persist(user);
      } else {
         edditUser(id,user);
      }
   }
   @Override
   public List<User> getListOfUsers() {
      return entityManager.createQuery("select user from User user", User.class).getResultList();
   }
   @Override

   public void edditUser(Long id, User user){
      entityManager.merge(user);
   }
   @Override

   public User getUserId(Long id){
      return entityManager.find(User.class, id);
   }

   @Override
   public void deleteUserById(Long id) {
      entityManager.createQuery("delete from User where id='"+id+"'").executeUpdate();
   }
   @Override
   public User getUserByUsername(String username) {
      Query query = entityManager.createQuery("select user from User user where user.username = :username", User.class);
      query.setParameter("username", username);
      return (User) query.getSingleResult();
   }
}
