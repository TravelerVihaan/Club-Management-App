package com.github.travelervihaan.clubmanagement.model.payrolls;

import com.github.travelervihaan.clubmanagement.model.employees.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="payrolls")
public class Payroll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payroll")
    private Long id;
    @NotEmpty
    @Column(name="month", nullable = false)
    private String month;
    @NotNull
    @Column(name = "worked_days", nullable = false)
    private Integer workedDays;
    @NotNull
    @Column(name = "worked_hours", nullable = false)
    private Double workedHours;
    @NotNull
    @Column(nullable = false)
    private Double salary;
    @NotNull
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    public Payroll(){}

    public Payroll(@NotEmpty String month, @NotNull Integer workedDays, @NotNull Double workedHours, @NotNull Double salary, @NotNull Employee employee) {
        this.month = month;
        this.workedDays = workedDays;
        this.workedHours = workedHours;
        this.salary = salary;
        this.employee = employee;
    }

    public Payroll(@NotNull Integer workedDays, @NotNull Double workedHours, @NotNull Double salary, @NotNull Employee employee) {
        this.workedDays = workedDays;
        this.workedHours = workedHours;
        this.salary = salary;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(Integer workedDays) {
        this.workedDays = workedDays;
    }

    public Double getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(Double workedHours) {
        this.workedHours = workedHours;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", workedDays=" + workedDays +
                ", workedHours=" + workedHours +
                ", salary=" + salary +
                ", employee=" + employee +
                '}';
    }
}
