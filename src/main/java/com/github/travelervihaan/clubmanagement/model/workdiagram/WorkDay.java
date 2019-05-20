package com.github.travelervihaan.clubmanagement.model.workdiagram;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "work_day")
public class WorkDay implements Serializable {

    private static final long serialVersionUID = 7021150458271420834L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workday_id")
    private Long id;
    private int dayOfMonth;
    private int month;
    private int year;

    public WorkDay(){}

    public WorkDay(int dayOfMonth, int month, int year) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
