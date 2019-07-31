package com.github.travelervihaan.clubmanagement.dto;

import java.time.LocalDate;

public class EmployeeDto {

    //Employee Entity
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    //EmployeeDetails Entity
    private LocalDate dayOfHireTerminate;
    private Double salary;
    //JobTitle Entity
    private String jobTitle;
    //ContractType Entity
    private String contractType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDayOfHireTerminate() {
        return dayOfHireTerminate;
    }

    public void setDayOfHireTerminate(LocalDate dayOfHireTerminate) {
        this.dayOfHireTerminate = dayOfHireTerminate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }
}
