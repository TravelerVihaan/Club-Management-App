package com.github.travelervihaan.clubmanagement.model.workdiagram;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "work_day")
public class WorkDay implements Serializable {

    private static final long serialVersionUID = 7021150458271420834L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_workday")
    private Long id;
    private int dayOfMonth;
    private int month;
    private int year;
    private int workingTime;

    public WorkDay(){}

    public WorkDay(int dayOfMonth, int month, int year, int workingTime) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
        this.workingTime = workingTime;
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

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "id=" + id +
                ", dayOfMonth=" + dayOfMonth +
                ", month=" + month +
                ", year=" + year +
                ", workingTime=" + workingTime +
                '}';
    }
}
