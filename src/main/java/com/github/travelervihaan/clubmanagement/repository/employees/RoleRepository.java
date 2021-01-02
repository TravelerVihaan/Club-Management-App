package com.github.travelervihaan.clubmanagement.repository.employees;

import com.github.travelervihaan.clubmanagement.model.employees.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
