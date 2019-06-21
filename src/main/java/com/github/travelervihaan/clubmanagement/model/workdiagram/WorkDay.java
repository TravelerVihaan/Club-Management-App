package com.github.travelervihaan.clubmanagement.model.workdiagram;

import com.github.travelervihaan.clubmanagement.model.employers.Employee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "work_day")
public class WorkDay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_workday")
    private Long id;
    @NotNull
    @Column(nullable = false, unique = true)
    private LocalDate date;
    @NotNull
    @Column(nullable = false)
    private Integer workingTime;

    private String bookedArtist;

    @ManyToOne
    @JoinColumn(name="contract_id")
    private WorkDayImportance workDayImportance;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(name="workday_employers",
                joinColumns = {@JoinColumn(name = "workday_id", referencedColumnName = "id_workday")},
                inverseJoinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id_employee")})
    private List<Employee> employers;

    public WorkDay(){}

    public WorkDay(LocalDate date, int workingTime) {
        this.date = date;
        this.workingTime = workingTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setWorkingTime(Integer workingTime) {
        this.workingTime = workingTime;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public List<Employee> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employee> employers) {
        this.employers = employers;
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "id=" + id +
                ", date=" + date +
                ", workingTime=" + workingTime +
                ", employers=" + employers +
                '}';
    }
}
