package com.github.travelervihaan.clubmanagement.service.employees;

import com.github.travelervihaan.clubmanagement.model.employees.Role;
import com.github.travelervihaan.clubmanagement.repository.employees.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role getRole(String role){
        return roleRepository.findByRole(role);
    }

    public void addNewRole(Role role){
        if(Optional.ofNullable(getRole(role.getRole())).isEmpty()){
            roleRepository.save(role);
    }
    }
}
