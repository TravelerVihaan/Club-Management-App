package com.github.travelervihaan.clubmanagement.service.employees;

import com.github.travelervihaan.clubmanagement.model.employees.Employee;
import com.github.travelervihaan.clubmanagement.model.employees.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private EmployeeService employeeService;

    @Autowired
    public CustomUserDetailsService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Employee employee = employeeService.getEmployeeByUsername(s).orElseThrow();
        return new User(
                employee.getUsername(),
                employee.getPassword(),
                convertAuthorities(employee.getRoles()));
    }

    private Set<GrantedAuthority> convertAuthorities(Set<Role> userRoles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for(Role role: userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}
