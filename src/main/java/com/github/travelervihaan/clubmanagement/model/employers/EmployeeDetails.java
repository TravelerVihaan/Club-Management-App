package com.github.travelervihaan.clubmanagement.model.employers;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name= "employers_details")
public class EmployeeDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee_details")
    private Long id;

    // HIRE INFO
    @NotNull
    @Column(name = "hire_day", nullable = false)
    private LocalDate hireDay;
    @Column(name = "date_terminate_contract")
    private LocalDate dayOfHireTerminate;

    @NotNull
    @Column(nullable = false)
    private Double salary;

    //URLOP
    @NotNull
    @Column(name = "total_vacation_days", nullable = false)
    private Integer totalVacationDays;
    @NotNull
    @Column(name = "available_vacation_days", nullable = false)
    private Integer availableVacationDays;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="title_id")
    private JobTitle jobTitle;
    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="contract_id")
    private ContractType contractType;

    @OneToOne(mappedBy = "employeeDetails")
    private Employee employee;

    public EmployeeDetails(){}
    public EmployeeDetails(@NotNull LocalDate hireDay, LocalDate dayOfHireTerminate, @NotNull Double salary, @NotNull Integer totalVacationDays, @NotNull Integer availableVacationDays, @NotNull JobTitle jobTitle, @NotNull ContractType contractType) {
        this.hireDay = hireDay;
        this.dayOfHireTerminate = dayOfHireTerminate;
        this.salary = salary;
        this.totalVacationDays = totalVacationDays;
        this.availableVacationDays = availableVacationDays;
        this.jobTitle = jobTitle;
        this.contractType = contractType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary() { return salary; }

    public void setSalary(Double salary) { this.salary = salary; }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    public LocalDate getDayOfHireTerminate() {
        return dayOfHireTerminate;
    }

    public void setDayOfHireTerminate(LocalDate dayOfHireTerminate) {
        this.dayOfHireTerminate = dayOfHireTerminate;
    }

    public Integer getTotalVacationDays() {
        return totalVacationDays;
    }

    public void setTotalVacationDays(Integer totalVacationDays) {
        this.totalVacationDays = totalVacationDays;
    }

    public Integer getAvailableVacationDays() {
        return availableVacationDays;
    }

    public void setAvailableVacationDays(Integer avaliableVacationDays) {
        this.availableVacationDays = avaliableVacationDays;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "id=" + id +
                ", hireDay=" + hireDay +
                ", dayOfHireTerminate=" + dayOfHireTerminate +
                ", salary=" + salary +
                ", totalVacationDays=" + totalVacationDays +
                ", avaliableVacationDays=" + availableVacationDays +
                ", jobTitle=" + jobTitle +
                ", contractType=" + contractType +
                '}';
    }
}
