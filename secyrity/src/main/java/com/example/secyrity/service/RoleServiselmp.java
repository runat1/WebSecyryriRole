package com.example.secyrity.service;

import com.example.secyrity.dao.RoleDao;
import com.example.secyrity.model.Role;
import com.example.secyrity.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class RoleServiselmp implements RoleService{
    private final RoleDao roleDao;
    private final RoleRepository roleRepository;

    public RoleServiselmp(RoleDao roleDao, RoleRepository roleRepository) {
        this.roleDao = roleDao;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public List<Role> getListOfRoles() {
        return roleDao.getListOfRoles();
    }
    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void setRoleUser(Long id, Long idRolle) {
        roleDao.setRoleUser(id,idRolle);
    }
}
