package com.github.travelervihaan.clubmanagement.service.employees;

import com.github.travelervihaan.clubmanagement.model.absences.Absence;
import com.github.travelervihaan.clubmanagement.model.employees.ContractType;
import com.github.travelervihaan.clubmanagement.model.employees.Employee;
import com.github.travelervihaan.clubmanagement.model.employees.JobTitle;
import com.github.travelervihaan.clubmanagement.model.employees.Role;
import com.github.travelervihaan.clubmanagement.repository.absences.AbsenceRepository;
import com.github.travelervihaan.clubmanagement.repository.employees.EmployeeRepository;
import com.github.travelervihaan.clubmanagement.repository.employees.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private AbsenceRepository absenceRepository;
    private RoleRepository roleRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,AbsenceRepository absenceRepository, RoleRepository roleRepository){
        this.employeeRepository = employeeRepository;
        this.absenceRepository = absenceRepository;
        this.roleRepository = roleRepository;
    }

    public List<Employee> getEmployersByJobTitle(String jobTitle){
        return employeeRepository.findAllByEmployeeDetails_JobTitle_JobTitle(jobTitle);
    }

    public List<Employee> getEmployersWithTerminatedContract(){
        return employeeRepository.findAllByEmployeeDetails_DayOfHireTerminateBefore(LocalDate.now());
    }

    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    public Optional<Employee> getEmployeeByUsername(String username){
        return employeeRepository.findByUsername(username);
    }

    public void changeEmployeeSalary(Long employeeId, double newSalary){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employee.getEmployeeDetails().setSalary(newSalary);
        employeeRepository.save(employee);
    }

    public void changeEmployeeContract(Long employeeId, ContractType newContract){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employee.getEmployeeDetails().setContractType(newContract);
        employeeRepository.save(employee);
    }

    public void changeEmployeeJobPosition(Long employeeId, JobTitle jobTitle){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        employee.setRoles(changeEmployeeRoles(employee.getRoles(),jobTitle.getJobTitle()));
        employee.getEmployeeDetails().setJobTitle(jobTitle);
        employeeRepository.save(employee);
    }

    private Set<Role> changeEmployeeRoles(Set<Role> roles, String jobTitle){
        if(jobTitle.equalsIgnoreCase("employee"))
            roles.removeIf(role -> !role.getRole().equalsIgnoreCase("USER"));

        if(jobTitle.equalsIgnoreCase("manager"))
            roles = roleRepository
                    .findAll()
                    .stream()
                    .filter(role -> !role.getRole().equalsIgnoreCase("ADMIN"))
                    .collect(Collectors.toSet());

        if(jobTitle.equalsIgnoreCase("director"))
            roles = new HashSet<>(roleRepository.findAll());

        return roles;
    }

    public List<Employee> getEmployersBySearchPattern(String searchPattern){
        return employeeRepository.findAllByUsernameOrNameOrSurname(searchPattern,searchPattern,searchPattern);
    }

    public void saveUpdatedEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void changeEmployeeVacationDays(Absence absence){
        Employee employee = absence.getEmployee();
        long vacationDays = ChronoUnit
                .DAYS
                .between(absence.getAbsenceFromDay(),absence.getAbsenceToDay());
        employee.getEmployeeDetails().setAvailableVacationDays(
                employee.getEmployeeDetails().getAvailableVacationDays()- (int) vacationDays);
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployers(){
        return employeeRepository.findAll();
    }

    public List<Employee> getAvailableEmployers(List<Employee> workDayEmployers){
        List<String> employersUsernames = workDayEmployers
                .stream()
                .map(Employee::getUsername)
                .collect(Collectors.toList());
        List<Employee> availableEmployers = getEmployersNotWorkingYet(employersUsernames);
        availableEmployers = getEmployersNotOnVacations(availableEmployers);
        return availableEmployers;
    }

    private List<Employee> getEmployersNotOnVacations(List<Employee> employers){
        List<String> employersOnVacation = absenceRepository
                .findAllByAbsenceFromDayIsBeforeAndAbsenceToDayIsAfter(LocalDate.now(),LocalDate.now())
                .stream()
                .map(Absence::getEmployee)
                .map(Employee::getUsername)
                .collect(Collectors.toList());
        for(String username: employersOnVacation){
            employers = employers
                    .stream()
                    .filter(employee -> !employee.getUsername().equalsIgnoreCase(username))
                    .collect(Collectors.toList());
        }
        return employers;
    }

    private List<Employee> getEmployersNotWorkingYet(List<String> usernames){
        List<Employee> allEmployers = this.getAllEmployers();
        for(String username: usernames){
            allEmployers = allEmployers
                    .stream()
                    .filter(employee -> !employee.getUsername()
                            .equalsIgnoreCase(username))
                    .collect(Collectors.toList());
        }
        return allEmployers;
    }
}
