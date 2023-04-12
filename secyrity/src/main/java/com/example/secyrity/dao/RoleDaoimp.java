package com.example.secyrity.dao;

import com.example.secyrity.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDaoimp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoleByName(String name) {

        return entityManager.createQuery("select role from Role role where name='"+name+"'", Role.class).getResultList();
    }/*    @Override
    public Role getRoleByName(String name) {
        Query query = entityManager.createQuery("select role from Role role where Role .name= :name", Role.class);
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }*/

@Override
public List<Role> getListOfRoles() {
    return entityManager.createQuery("select role from Role role", Role.class).getResultList();
}
public void setRoleUser(Long id, Long idRolle){
        List<Role>roles=getListOfRoles();
        entityManager.createQuery("update User where id = '"+id+"' set roleList= :'"+roles+"'",Role.class);
}
}
