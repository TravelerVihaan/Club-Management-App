package com.github.travelervihaan.clubmanagement.model.payrolls;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="payrolls")
public class Payroll implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer workedDays;
    private Double workedHours;
    private Double salary;

    Payroll(){}

    public Payroll(int workedDays, double workedHours, double salary) {
        this.workedDays = workedDays;
        this.workedHours = workedHours;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Payroll{" +
                "id=" + id +
                ", workedDays=" + workedDays +
                ", workedHours=" + workedHours +
                ", salary=" + salary +
                '}';
    }
}
