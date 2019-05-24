package com.github.travelervihaan.clubmanagement.model.payrolls;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="payrolls")
public class Payroll implements Serializable {

    private static final long serialVersionUID = 70L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int workedDays;
    private double workedHours;
    private double salary;

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

    public int getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(int workedDays) {
        this.workedDays = workedDays;
    }

    public double getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(double workedHours) {
        this.workedHours = workedHours;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
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
