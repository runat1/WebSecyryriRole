package com.example.secyrity.dao;



import com.example.secyrity.model.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> getRoleByName(String name);
    List<Role> getListOfRoles();
    void setRoleUser(Long id, Long idRolle);
}
