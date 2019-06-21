package com.github.travelervihaan.clubmanagement.model.payrolls;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="payrolls")
public class Payroll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private Integer workedDays;
    @NotNull
    @Column(nullable = false)
    private Double workedHours;
    @NotNull
    @Column(nullable = false)
    private Double salary;
    @NotNull
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    Payroll(){}

    public Payroll(int workedDays, double workedHours, double salary, Employee employee) {
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
                ", workedDays=" + workedDays +
                ", workedHours=" + workedHours +
                ", salary=" + salary +
                ", employee=" + employee +
                '}';
    }
}
