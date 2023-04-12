package com.example.secyrity.service;

import com.example.secyrity.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface RoleService  {


    List<Role> getRoleByName(String name);


    List<Role> getListOfRoles();
    List<Role> getAllRoles();
    void setRoleUser(Long id, Long idRolle);
}
