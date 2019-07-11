package com.github.travelervihaan.clubmanagement.repository.employers;

import com.github.travelervihaan.clubmanagement.model.employers.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
